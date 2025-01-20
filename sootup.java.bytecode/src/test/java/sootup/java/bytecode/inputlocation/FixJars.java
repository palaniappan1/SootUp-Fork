package sootup.java.bytecode.inputlocation;

import categories.TestCategories;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sootup.java.core.views.JavaView;

@Tag(TestCategories.JAVA_8_CATEGORY)
public class FixJars extends BaseFixJarsTest {

@Test
public void executetestautomationjdbcjar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/com/nortal/test/test-automation-jdbc/0.2.18/test-automation-jdbc-0.2.18.jar";
    String methodSignature = "<com.nortal.test.jdbc.configuration.JdbcDataSourceProperties: java.lang.String toString()>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

@Test
public void executetinylogbackjar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/net/morimekta/tiny/tiny-logback/0.7.1/tiny-logback-0.7.1.jar";
    String methodSignature = "<net.morimekta.tiny.logback.JsonLayout: void setStackTraceFilter(java.lang.String)>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

}