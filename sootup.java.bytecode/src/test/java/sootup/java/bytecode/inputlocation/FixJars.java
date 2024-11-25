package sootup.java.bytecode.inputlocation;

import categories.TestCategories;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sootup.java.core.views.JavaView;

@Tag(TestCategories.JAVA_8_CATEGORY)
public class FixJars extends BaseFixJarsTest {

@Test
public void executeiocunitjtajpajar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/net/oneandone/ioc-unit/ioc-unit-jta-jpa/4.0.3/ioc-unit-jta-jpa-4.0.3.jar";
    String methodSignature = "<com.oneandone.iocunit.jtajpa.narayana.cdi.CDITransactionProducers: void <init>()>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

@Test
public void executeprotonhawtdispatchjar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/org/apache/qpid/proton-hawtdispatch/0.12.2/proton-hawtdispatch-0.12.2.jar";
    String methodSignature = "<org.apache.qpid.proton.hawtdispatch.api.ChainedCallback: void onFailure(java.lang.Throwable)>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

}