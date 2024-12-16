package sootup.java.bytecode.inputlocation;

import categories.TestCategories;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sootup.java.core.views.JavaView;

@Tag(TestCategories.JAVA_8_CATEGORY)
public class FixJars extends BaseFixJarsTest {

@Test
public void executesolropentelemetryjar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/org/apache/solr/solr-opentelemetry/9.7.0/solr-opentelemetry-9.7.0.jar";
    String methodSignature = "<org.apache.solr.opentelemetry.ClosableTracerShim: void close()>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

@Test
public void executespringbootstarterartemisjar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/org/mydotey/artemis/spring-boot-starter-artemis/0.1.2/spring-boot-starter-artemis-0.1.2.jar";
    String methodSignature = "<org.mydotey.artemis.spring.boot.ArtemisServerInitializerConfiguration: void start()>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

}