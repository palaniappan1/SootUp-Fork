package sootup.java.bytecode.inputlocation;

import categories.TestCategories;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sootup.java.core.views.JavaView;

@Tag(TestCategories.JAVA_8_CATEGORY)
public class FixJars extends BaseFixJarsTest {

@Test
public void executevtmapandroidservicesdirectionsrefreshjar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/vn/viettelmaps/vtmsdk/vtmap-android-services-directions-refresh/1.0.1/vtmap-android-services-directions-refresh-1.0.1.jar";
    String methodSignature = "<com.mapbox.api.directionsrefresh.v1.AutoValue_MapboxDirectionsRefresh$Builder: void <init>(com.mapbox.api.directionsrefresh.v1.MapboxDirectionsRefresh,com.mapbox.api.directionsrefresh.v1.AutoValue_MapboxDirectionsRefresh$1)>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

@Test
public void executeantlauncherjar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/ant/ant-launcher/1.6.5/ant-launcher-1.6.5.jar";
    String methodSignature = "<org.apache.tools.ant.launch.LaunchException: void <init>(java.lang.String)>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

}