package sootup.java.bytecode.inputlocation;

import categories.TestCategories;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sootup.java.core.views.JavaView;

@Tag(TestCategories.JAVA_8_CATEGORY)
public class FixJars extends BaseFixJarsTest {

@Test
public void executeboostedyamlspigotjar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/dev/dejvokep/boosted-yaml-spigot/1.5/boosted-yaml-spigot-1.5.jar";
    String methodSignature = "<dev.dejvokep.boostedyaml.spigot.SpigotSerializer$1: void <init>()>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

@Test
public void executemonixgrpcruntimefbfcbjar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/me/vican/jorge/monix-grpc-runtime_2.13/0.0.0+48-f17bfc3b/monix-grpc-runtime_2.13-0.0.0+48-f17bfc3b.jar";
    String methodSignature = "<monix.grpc.client.package$: io.grpc.ManagedChannel $anonfun$makeResource$1(io.grpc.ManagedChannelBuilder)>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

}