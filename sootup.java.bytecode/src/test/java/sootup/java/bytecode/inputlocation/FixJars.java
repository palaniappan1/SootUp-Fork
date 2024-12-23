package sootup.java.bytecode.inputlocation;

import categories.TestCategories;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sootup.java.core.views.JavaView;

@Tag(TestCategories.JAVA_8_CATEGORY)
public class FixJars extends BaseFixJarsTest {

@Test
public void executeandroidprogresspanelwidgetjar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/com/marvinlabs/android-progresspanel-widget/1.1.0/android-progresspanel-widget-1.1.0.jar";
    String methodSignature = "<com.marvinlabs.widget.progresspanel.BuildConfig: void <init>()>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

@Test
public void executepowsyblactionialdslspijar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/com/powsybl/powsybl-action-ial-dsl-spi/6.6.0/powsybl-action-ial-dsl-spi-6.6.0.jar";
    String methodSignature = "";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

}