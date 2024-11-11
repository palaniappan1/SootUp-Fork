package sootup.java.bytecode.inputlocation;

import categories.TestCategories;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sootup.java.core.views.JavaView;

@Tag(TestCategories.JAVA_8_CATEGORY)
public class FixJars extends BaseFixJarsTest {

@Test
public void executenacosRELEASEOTjar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/io/gitee/laoshirenggo/nacos/1.0-RELEASEOT/nacos-1.0-RELEASEOT.jar";
    String methodSignature = "<com.jl.JLNacosConfig$Center$Config: java.lang.String toString()>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

@Test
public void executeorgcarewebframeworkapisecuritymockRCjar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/org/carewebframework/org.carewebframework.api.security.mock/6.1.0-RC4/org.carewebframework.api.security.mock-6.1.0-RC4.jar";
    String methodSignature = "<org.carewebframework.api.security.mock.MockSecurityDomain: java.lang.String getLogicalId()>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

}