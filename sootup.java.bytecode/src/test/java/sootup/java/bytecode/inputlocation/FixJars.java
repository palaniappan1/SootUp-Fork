package sootup.java.bytecode.inputlocation;

import categories.TestCategories;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sootup.java.core.views.JavaView;

@Tag(TestCategories.JAVA_8_CATEGORY)
public class FixJars extends BaseFixJarsTest {

@Test
public void executeelementtemplatescjar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/io/miragon/miranum/connect/element-templates-c8/0.7.3/element-templates-c8-0.7.3.jar";
    String methodSignature = "<io.miragon.miranum.connect.elementtemplate.c8.schema.Equals: int hashCode()>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

@Test
public void executevlschemaMILESTONEjar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/io/vacco/volach/vl-schema/1.2.0-MILESTONE-202201250934/vl-schema-1.2.0-MILESTONE-202201250934.jar";
    String methodSignature = "<io.vacco.volach.schema.VlMatchPeak: io.vacco.volach.schema.VlMatchPeak from(io.vacco.volach.schema.VlFftPeak,io.vacco.volach.schema.VlFftPeak)>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

}