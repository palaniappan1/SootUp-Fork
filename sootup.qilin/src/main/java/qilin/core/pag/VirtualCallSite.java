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

package qilin.core.pag;

import java.util.Objects;
import qilin.core.builder.callgraph.Kind;
import sootup.core.jimple.common.expr.AbstractInstanceInvokeExpr;
import sootup.core.jimple.common.stmt.InvokableStmt;
import sootup.core.signatures.MethodSubSignature;

/**
 * Holds relevant information about a particular virtual call site.
 *
 * @author Ondrej Lhotak
 */
public class VirtualCallSite extends CallSite {
  private final VarNode recNode;
  private final ContextMethod container;
  private final AbstractInstanceInvokeExpr iie;
  private final MethodSubSignature subSig;
  private final Kind kind;

  public VirtualCallSite(
      VarNode recNode,
      InvokableStmt stmt,
      ContextMethod container,
      AbstractInstanceInvokeExpr iie,
      MethodSubSignature subSig,
      Kind kind) {
    super(stmt);
    this.recNode = recNode;
    this.container = container;
    this.iie = iie;
    this.subSig = subSig;
    this.kind = kind;
  }

  public VarNode recNode() {
    return recNode;
  }

  public ContextMethod container() {
    return container;
  }

  public AbstractInstanceInvokeExpr iie() {
    return iie;
  }

  public MethodSubSignature subSig() {
    return subSig;
  }

  public Kind kind() {
    return kind;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    VirtualCallSite that = (VirtualCallSite) o;
    return recNode.equals(that.recNode)
        && container.equals(that.container)
        && iie.equals(that.iie)
        && subSig.equals(that.subSig)
        && kind.equals(that.kind);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), recNode, container, iie, subSig, kind);
  }
}
