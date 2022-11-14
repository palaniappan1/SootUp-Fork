package de.upb.sse.sootup.test.typehierarchy.methoddispatchtestcase;

import static junit.framework.TestCase.*;

import categories.Java8Test;
import de.upb.sse.sootup.core.signatures.MethodSignature;
import de.upb.sse.sootup.core.typehierarchy.MethodDispatchResolver;
import de.upb.sse.sootup.core.types.ClassType;
import de.upb.sse.sootup.test.typehierarchy.MethodDispatchBase;
import java.util.Collections;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/** @author : Hasitha Rajapakse, Jonas Klauke * */
@Category(Java8Test.class)
public class ConcreteDispatchTest extends MethodDispatchBase {
  @Test
  public void method() {
    // test concrete method in super class
    ClassType sootClassTypeA = getClassType("A");
    ClassType sootClassTypeB = getClassType("B");

    MethodSignature sootMethod1 =
        identifierFactory.getMethodSignature(
            sootClassTypeA, "method", "void", Collections.emptyList());
    MethodSignature sootMethod2 =
        identifierFactory.getMethodSignature(
            sootClassTypeA, "method2", "void", Collections.emptyList());
    MethodSignature sootMethod3 =
        identifierFactory.getMethodSignature(
            sootClassTypeB, "method2", "void", Collections.emptyList());

    MethodSignature candidate1 =
        MethodDispatchResolver.resolveConcreteDispatch(customTestWatcher.getView(), sootMethod1);
    assertEquals(candidate1, sootMethod1);

    MethodSignature candidate2 =
        MethodDispatchResolver.resolveConcreteDispatch(customTestWatcher.getView(), sootMethod2);
    assertEquals(candidate2, sootMethod3);

    // test concrete method in interface
    ClassType sootClassInterfaceI = getClassType("I");
    MethodSignature sootInterfaceMethod =
        identifierFactory.getMethodSignature(
            sootClassInterfaceI, "interfaceMethod", "void", Collections.emptyList());

    MethodSignature sootInterfaceMethodA =
        identifierFactory.getMethodSignature(
            sootClassTypeA, "interfaceMethod", "void", Collections.emptyList());

    MethodSignature candidateInterface =
        MethodDispatchResolver.resolveConcreteDispatch(
            customTestWatcher.getView(), sootInterfaceMethodA);
    assertEquals(candidateInterface, sootInterfaceMethod);

    // test concrete method in super-interface
    ClassType sootClassInterfaceJ = getClassType("J");
    MethodSignature sootSuperInterfaceMethod =
        identifierFactory.getMethodSignature(
            sootClassInterfaceJ, "superInterfaceMethod", "void", Collections.emptyList());

    MethodSignature sootSuperInterfaceMethodA =
        identifierFactory.getMethodSignature(
            sootClassTypeA, "superInterfaceMethod", "void", Collections.emptyList());

    MethodSignature candidateSuperInterface =
        MethodDispatchResolver.resolveConcreteDispatch(
            customTestWatcher.getView(), sootSuperInterfaceMethodA);
    assertEquals(candidateSuperInterface, sootSuperInterfaceMethod);

    // test concrete method with two possible default methods but one is in the sub-interface
    ClassType sootClassTypeC = getClassType("C");
    ClassType sootClassTypeD = getClassType("D");
    MethodSignature subInterfaceMethod =
        identifierFactory.getMethodSignature(
            sootClassInterfaceI, "interfaceMethod", "void", Collections.emptyList());

    MethodSignature sootSuperInterfaceMethodD =
        identifierFactory.getMethodSignature(
            sootClassTypeD, "interfaceMethod", "void", Collections.emptyList());

    MethodSignature candidateSubInterface =
        MethodDispatchResolver.resolveConcreteDispatch(
            customTestWatcher.getView(), sootSuperInterfaceMethodD);
    assertEquals(candidateSubInterface, subInterfaceMethod);
  }
}
