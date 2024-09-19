package sootup.java.bytecode.inputlocation;

import sootup.core.model.SootMethod;
import sootup.java.core.interceptors.BytecodeBodyInterceptors;
import sootup.java.core.views.JavaView;

import java.util.Collections;

public abstract class BaseFixJarsTest {

  String failedMethodSignature = "";


  public JavaView supplyJavaView(String jarDownloadUrl) {
    DownloadJarAnalysisInputLocation inputLocation =
            new DownloadJarAnalysisInputLocation(jarDownloadUrl, BytecodeBodyInterceptors.Default.getBodyInterceptors(), Collections.emptyList());
    return new JavaView(inputLocation);
  }

  public void assertMethodConversion(JavaView javaView, String methodSignature) {
    try {
      javaView
              .getMethod(javaView.getIdentifierFactory().parseMethodSignature(methodSignature))
              .get()
              .getBody();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void assertJar(JavaView javaView) {
    try {
      javaView.getClasses()
              .flatMap(clazz -> clazz.getMethods().stream())
              .filter(SootMethod::hasBody)
              .forEach(javaSootMethod -> {
                try{
                  javaSootMethod.getBody();
                  throw new RuntimeException();
                }
                catch (Exception exception){
                  failedMethodSignature = javaSootMethod.getSignature().toString();
                }
              });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
