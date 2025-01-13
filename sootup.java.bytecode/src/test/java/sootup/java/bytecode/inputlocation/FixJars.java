package sootup.java.bytecode.inputlocation;

import categories.TestCategories;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sootup.java.core.views.JavaView;

@Tag(TestCategories.JAVA_8_CATEGORY)
public class FixJars extends BaseFixJarsTest {

@Test
public void executescalastewardmillpluginjar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/org/scala-steward/scala-steward-mill-plugin_2.13/0.16.1/scala-steward-mill-plugin_2.13-0.16.1.jar";
    String methodSignature = "<org.scalasteward.mill.plugin.StewardPlugin$$anonfun$findModules$1: void <init>()>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

@Test
public void executersahelperjar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/com/github/120011676/rsa-helper/0.0.7/rsa-helper-0.0.7.jar";
    String methodSignature = "<com.github.qq120011676.rsa.AesHelper: java.lang.String decryptByBase64(java.lang.String,java.lang.String,java.lang.String)>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

}