/* Qilin - a Java Pointer Analysis Framework
 * Copyright (C) 2021-2030 Qilin developers
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3.0 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <https://www.gnu.org/licenses/lgpl-3.0.en.html>.
 */

package qilin.core.builder;

import java.util.*;
import qilin.CoreConfig;
import qilin.core.PTA;
import qilin.core.PTAScene;
import qilin.core.VirtualCalls;
import qilin.core.builder.callgraph.Edge;
import qilin.core.builder.callgraph.Kind;
import qilin.core.builder.callgraph.OnFlyCallGraph;
import qilin.core.context.Context;
import qilin.core.pag.*;
import qilin.core.sets.P2SetVisitor;
import qilin.core.sets.PointsToSetInternal;
import qilin.util.DataFactory;
import qilin.util.PTAUtils;
import qilin.util.queue.ChunkedQueue;
import qilin.util.queue.QueueReader;
import sootup.core.jimple.basic.Local;
import sootup.core.jimple.basic.StmtPositionInfo;
import sootup.core.jimple.basic.Value;
import sootup.core.jimple.common.constant.NullConstant;
import sootup.core.jimple.common.expr.AbstractInvokeExpr;
import sootup.core.jimple.common.expr.JSpecialInvokeExpr;
import sootup.core.jimple.common.expr.JStaticInvokeExpr;
import sootup.core.jimple.common.stmt.InvokableStmt;
import sootup.core.jimple.common.stmt.JAssignStmt;
import sootup.core.jimple.common.stmt.JInvokeStmt;
import sootup.core.jimple.common.stmt.Stmt;
import sootup.core.model.SootMethod;
import sootup.core.signatures.MethodSubSignature;
import sootup.core.types.ClassType;
import sootup.core.types.ReferenceType;
import sootup.core.types.Type;
import sootup.core.types.UnknownType;

public class CallGraphBuilder {
  private static final ClassType clRunnable = PTAUtils.getClassType("java.lang.Runnable");
  protected final Map<VarNode, Collection<VirtualCallSite>> receiverToSites;
  protected final Map<SootMethod, Map<Object, InvokableStmt>> methodToInvokeStmt;
  protected final Set<ContextMethod> reachMethods;
  private ChunkedQueue<ContextMethod> rmQueue;

  protected final Set<Edge> calledges;
  protected final PTA pta;
  protected final PAG pag;
  protected final PTAScene ptaScene;
  protected final VirtualCalls virtualCalls;
  protected OnFlyCallGraph cicg;

  public CallGraphBuilder(PTA pta) {
    this.pta = pta;
    this.pag = pta.getPag();
    this.ptaScene = pta.getScene();
    ptaScene.setCallGraph(new OnFlyCallGraph());
    this.virtualCalls = new VirtualCalls(ptaScene.getView());
    receiverToSites = DataFactory.createMap((int) ptaScene.getView().getClasses().count());
    methodToInvokeStmt = DataFactory.createMap();
    reachMethods = DataFactory.createSet();
    calledges = DataFactory.createSet();
  }

  public void setRMQueue(ChunkedQueue<ContextMethod> rmQueue) {
    this.rmQueue = rmQueue;
  }

  public Collection<ContextMethod> getReachableMethods() {
    return reachMethods;
  }

  // initialize the receiver to sites map with the number of locals * an
  // estimate for the number of contexts per methods
  public Map<VarNode, Collection<VirtualCallSite>> getReceiverToSitesMap() {
    return receiverToSites;
  }

  public Collection<VirtualCallSite> callSitesLookUp(VarNode receiver) {
    return receiverToSites.getOrDefault(receiver, Collections.emptySet());
  }

  public SootMethod resolveNonSpecial(ClassType t, MethodSubSignature subSig) {
    return virtualCalls.resolveNonSpecial(t, subSig);
  }

  public OnFlyCallGraph getCallGraph() {
    if (cicg == null) {
      constructCallGraph();
    }
    return ptaScene.getCallGraph();
  }

  public OnFlyCallGraph getCICallGraph() {
    if (cicg == null) {
      constructCallGraph();
    }
    return cicg;
  }

  private void constructCallGraph() {
    cicg = new OnFlyCallGraph();
    Map<Stmt, Map<SootMethod, Set<SootMethod>>> map = DataFactory.createMap();
    calledges.forEach(
        e -> {
          ptaScene.getCallGraph().addEdge(e);
          SootMethod src = e.src();
          SootMethod tgt = e.tgt();
          Stmt unit = e.srcUnit();
          Map<SootMethod, Set<SootMethod>> submap =
              map.computeIfAbsent(unit, k -> DataFactory.createMap());
          Set<SootMethod> set = submap.computeIfAbsent(src, k -> DataFactory.createSet());
          if (set.add(tgt)) {
            cicg.addEdge(
                new Edge(
                    new ContextMethod(src, pta.emptyContext()),
                    e.srcUnit(),
                    new ContextMethod(tgt, pta.emptyContext()),
                    e.kind()));
          }
        });
  }

  public List<ContextMethod> getEntryPoints() {
    Node thisRef = pag.getMethodPAG(ptaScene.getFakeMainMethod()).nodeFactory().caseThis();
    thisRef = pta.parameterize(thisRef, pta.emptyContext());
    pag.addEdge(pta.getRootNode(), thisRef);
    return Collections.singletonList(
        pta.parameterize(ptaScene.getFakeMainMethod(), pta.emptyContext()));
  }

  public void initReachableMethods() {
    for (ContextMethod momc : getEntryPoints()) {
      if (reachMethods.add(momc)) {
        rmQueue.add(momc);
      }
    }
  }

  public VarNode getReceiverVarNode(Local receiver, ContextMethod m) {
    if (receiver.getType() == UnknownType.getInstance()) {
      System.out.println("why unknown??" + m.method() + ";;" + receiver);
      throw new RuntimeException();
    }
    LocalVarNode base = pag.makeLocalVarNode(receiver, receiver.getType(), m.method());
    return (VarNode) pta.parameterize(base, m.context());
  }

  protected void dispatch(AllocNode receiverNode, VirtualCallSite site) {
    Type type = receiverNode.getType();
    final QueueReader<SootMethod> targets = dispatch(type, site);
    while (targets.hasNext()) {
      SootMethod target = targets.next();
      if (site.iie() instanceof JSpecialInvokeExpr) {
        Type calleeDeclType = target.getDeclaringClassType();
        if (!PTAUtils.canStoreType(pta.getView(), type, calleeDeclType)) {
          continue;
        }
      }
      addVirtualEdge(site.container(), site.getUnit(), target, site.kind(), receiverNode);
    }
  }

  private void addVirtualEdge(
      ContextMethod caller,
      InvokableStmt callStmt,
      SootMethod callee,
      Kind kind,
      AllocNode receiverNode) {
    Context tgtContext = pta.createCalleeCtx(caller, receiverNode, new CallSite(callStmt), callee);
    ContextMethod cstarget = pta.parameterize(callee, tgtContext);
    handleCallEdge(new Edge(caller, callStmt, cstarget, kind));
    Node thisRef = pag.getMethodPAG(callee).nodeFactory().caseThis();
    thisRef = pta.parameterize(thisRef, cstarget.context());
    pag.addEdge(receiverNode, thisRef);
  }

  public void injectCallEdge(Object heapOrType, ContextMethod callee, Kind kind) {
    Map<Object, InvokableStmt> stmtMap =
        methodToInvokeStmt.computeIfAbsent(callee.method(), k -> DataFactory.createMap());
    if (!stmtMap.containsKey(heapOrType)) {
      AbstractInvokeExpr ie =
          new JStaticInvokeExpr(callee.method().getSignature(), Collections.emptyList());
      JInvokeStmt stmt = new JInvokeStmt(ie, StmtPositionInfo.getNoStmtPositionInfo());
      stmtMap.put(heapOrType, stmt);
      handleCallEdge(
          new Edge(
              pta.parameterize(ptaScene.getFakeMainMethod(), pta.emptyContext()),
              stmtMap.get(heapOrType),
              callee,
              kind));
    }
  }

  public void addStaticEdge(
      ContextMethod caller, InvokableStmt callStmt, SootMethod calleem, Kind kind) {
    Context typeContext = pta.createCalleeCtx(caller, null, new CallSite(callStmt), calleem);
    ContextMethod callee = pta.parameterize(calleem, typeContext);
    handleCallEdge(new Edge(caller, callStmt, callee, kind));
  }

  protected void handleCallEdge(Edge edge) {
    if (calledges.add(edge)) {
      ContextMethod callee = edge.getTgt();
      if (reachMethods.add(callee)) {
        rmQueue.add(callee);
      }
      processCallAssign(edge);
    }
  }

  public boolean recordVirtualCallSite(VarNode receiver, VirtualCallSite site) {
    Collection<VirtualCallSite> sites =
        receiverToSites.computeIfAbsent(receiver, k -> DataFactory.createSet());
    return sites.add(site);
  }

  public void virtualCallDispatch(PointsToSetInternal p2set, VirtualCallSite site) {
    p2set.forall(
        new P2SetVisitor(pta) {
          public void visit(Node n) {
            dispatch((AllocNode) n, site);
          }
        });
  }

  /**
   * Adds method target as a possible target of the invoke expression in s. If target is null, only
   * creates the nodes for the call site, without actually connecting them to any target method.
   */
  private void processCallAssign(Edge e) {
    MethodPAG srcmpag = pag.getMethodPAG(e.src());
    MethodPAG tgtmpag = pag.getMethodPAG(e.tgt());
    Stmt s = e.srcUnit();
    Context srcContext = e.srcCtxt();
    Context tgtContext = e.tgtCtxt();
    MethodNodeFactory srcnf = srcmpag.nodeFactory();
    MethodNodeFactory tgtnf = tgtmpag.nodeFactory();
    SootMethod tgtmtd = tgtmpag.getMethod();
    AbstractInvokeExpr ie = s.asInvokableStmt().getInvokeExpr().get();
    // add arg --> param edges.
    int numArgs = ie.getArgCount();
    for (int i = 0; i < numArgs; i++) {
      Value arg = ie.getArg(i);
      if (!(arg.getType() instanceof ReferenceType) || arg instanceof NullConstant) {
        continue;
      }
      Type tgtType = tgtmtd.getParameterType(i);
      if (!(tgtType instanceof ReferenceType)) {
        continue;
      }
      Node argNode = srcnf.getNode(arg);
      argNode = pta.parameterize(argNode, srcContext);
      Node parm = tgtnf.caseParm(i);
      parm = pta.parameterize(parm, tgtContext);
      pag.addEdge(argNode, parm);
    }
    // add normal return edge
    if (s instanceof JAssignStmt) {
      Value dest = ((JAssignStmt) s).getLeftOp();

      if (dest.getType() instanceof ReferenceType) {
        Node destNode = srcnf.getNode(dest);
        destNode = pta.parameterize(destNode, srcContext);
        if (tgtmtd.getReturnType() instanceof ReferenceType) {
          Node retNode = tgtnf.caseRet();
          retNode = pta.parameterize(retNode, tgtContext);
          pag.addEdge(retNode, destNode);
        }
      }
    }
    // add throw return edge
    if (CoreConfig.v().getPtaConfig().preciseExceptions) {
      Node throwNode = tgtnf.caseMethodThrow();
      /*
       * If an invocation statement may throw exceptions, we create a special local variables
       * to receive the exception objects.
       * a_ret = x.foo(); here, a_ret is a variable to receive values from return variables of foo();
       * a_throw = x.foo(); here, a_throw is a variable to receive exception values thrown by foo();
       * */
      throwNode = pta.parameterize(throwNode, tgtContext);
      MethodNodeFactory mnf = srcmpag.nodeFactory();
      Node dst = mnf.makeInvokeStmtThrowVarNode(s, srcmpag.getMethod());
      dst = pta.parameterize(dst, srcContext);
      pag.addEdge(throwNode, dst);
    }
  }

  public QueueReader<SootMethod> dispatch(Type type, VirtualCallSite site) {
    final ChunkedQueue<SootMethod> targetsQueue = new ChunkedQueue<>();
    final QueueReader<SootMethod> targets = targetsQueue.reader();
    if (site.kind() == Kind.THREAD
        && !PTAUtils.canStoreType(ptaScene.getView(), type, clRunnable)) {
      return targets;
    }
    ContextMethod container = site.container();
    if (site.iie() instanceof JSpecialInvokeExpr && site.kind() != Kind.THREAD) {
      SootMethod target =
          virtualCalls.resolveSpecial(
              (JSpecialInvokeExpr) site.iie(), site.subSig(), container.method());
      // if the call target resides in a phantom class then
      // "target" will be null, simply do not add the target in that case
      if (target != null) {
        targetsQueue.add(target);
      }
    } else {
      Type mType = site.recNode().getType();
      virtualCalls.resolve(type, mType, site.subSig(), container.method(), targetsQueue);
    }
    return targets;
  }
}
