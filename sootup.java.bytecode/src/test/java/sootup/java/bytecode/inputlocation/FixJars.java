package sootup.java.bytecode.inputlocation;

import categories.TestCategories;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sootup.java.core.views.JavaView;

@Tag(TestCategories.JAVA_8_CATEGORY)
public class FixJars extends BaseFixJarsTest {

@Test
public void executestatisticsjar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/com/genesys/statistics/9.0.70/statistics-9.0.70.jar";
    String methodSignature = "<com.genesys.internal.common.ProgressRequestBody: com.squareup.okhttp.MediaType contentType()>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

@Test
public void executevuedevtoolsapijar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/org/webjars/npm/vue__devtools-api/6.6.4/vue__devtools-api-6.6.4.jar";
    String methodSignature = "";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

}