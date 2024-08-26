package sootup.java.bytecode.inputlocation;

import categories.TestCategories;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sootup.java.core.views.JavaView;

@Tag(TestCategories.JAVA_8_CATEGORY)
public class FixJars extends BaseFixJarsTest {

@Test
public void executedisciplinecore(){
	String jarName = "discipline-core_0.27-1.1.2.jar";
    String methodSignature = "<org.typelevel.discipline.Laws$RuleSet: org.typelevel.discipline.Laws org$typelevel$discipline$Laws$RuleSet$$$outer()>";
    JavaView javaView = supplyJavaView(jarName);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

@Test
public void executehadoopaliyun(){
	String jarName = "hadoop-aliyun-2.7.2.jar";
    String methodSignature = "<com.aliyun.oss.model.PartSummary: java.lang.String getETag()>";
    JavaView javaView = supplyJavaView(jarName);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

@Test
public void executemysemacommonscore(){
	String jarName = "mysema-commons-core-0.3.8.jar";
    String methodSignature = "<com.mysema.commons.core.ReflectionUtils: void <init>()>";
    JavaView javaView = supplyJavaView(jarName);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

@Test
public void executeoceanenginespringbootstarterbeta(){
	String jarName = "oceanengine-spring-boot-starter-1.0.0-beta.3.jar";
    String methodSignature = "<com.hyq0719.spring.starter.mktapi.oceanengine.config.HttpAutoConfiguration: com.hyq0719.mktapi.common.executor.http.OkhttpHttpHandler okhttpRequestHandler(okhttp3.OkHttpClient)>";
    JavaView javaView = supplyJavaView(jarName);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

@Test
public void executesigvalpdf(){
	String jarName = "sigval-pdf-1.0.1.jar";
    String methodSignature = "<se.idsec.sigval.pdf.pdfstruct.AcroForm: se.idsec.sigval.pdf.pdfstruct.Dictionary getDictionary()>";
    JavaView javaView = supplyJavaView(jarName);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

}