package sootup.java.bytecode.inputlocation;

import categories.TestCategories;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sootup.java.core.views.JavaView;

@Tag(TestCategories.JAVA_8_CATEGORY)
public class FixJars extends BaseFixJarsTest {

@Test
public void executeopenejbapijar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/org/apache/geronimo/ext/openejb/openejb-api/3.1.3.0/openejb-api-3.1.3.0.jar";
    String methodSignature = "";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

@Test
public void executekottiedesktopalphajar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/io/github/ismai117/kottie-desktop/1.5.3-alpha02/kottie-desktop-1.5.3-alpha02.jar";
    String methodSignature = "<AnimateKottieCompositionAsState_desktopKt$animateKottieCompositionAsState$1$1: kotlin.coroutines.Continuation create(java.lang.Object,kotlin.coroutines.Continuation)>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

}