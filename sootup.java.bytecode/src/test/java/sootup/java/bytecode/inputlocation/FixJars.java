package sootup.java.bytecode.inputlocation;

import categories.TestCategories;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sootup.java.core.views.JavaView;

@Tag(TestCategories.JAVA_8_CATEGORY)
public class FixJars extends BaseFixJarsTest {

@Test
public void executeJDoubleDispatchjar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/su/izotov/JDoubleDispatch/0.4/JDoubleDispatch-0.4.jar";
    String methodSignature = "<su.izotov.java.ddispatch.ByParameterClass: void <init>(su.izotov.java.ddispatch.types.MasterClass,su.izotov.java.ddispatch.types.GuestClass,java.lang.String,su.izotov.java.ddispatch.types.ReturnClass,su.izotov.java.ddispatch.MethodsSource)>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

@Test
public void executegraviteegatewayservicesheartbeatjar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/io/gravitee/gateway/services/gravitee-gateway-services-heartbeat/3.13.1/gravitee-gateway-services-heartbeat-3.13.1.jar";
    String methodSignature = "<io.gravitee.gateway.services.heartbeat.event.InstanceEventPayload: void setTags(java.util.List)>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

}