package sootup.java.bytecode.inputlocation;

import categories.TestCategories;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sootup.java.core.views.JavaView;

@Tag(TestCategories.JAVA_8_CATEGORY)
public class FixJars extends BaseFixJarsTest {

@Test
public void executebrokerjar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/com/baerbak/maven/broker/4.0/broker-4.0.jar";
    String methodSignature = "<frds.broker.marshall.json.StandardJSONRequestor: void close()>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

@Test
public void executezioawsgrafanajar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/io/github/vigoo/zio-aws-grafana_2.13/4.17.280.4/zio-aws-grafana_2.13-4.17.280.4.jar";
    String methodSignature = "<io.github.vigoo.zioaws.grafana.model.AccountAccessType$: void <init>()>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

}