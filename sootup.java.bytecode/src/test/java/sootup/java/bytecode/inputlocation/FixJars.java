package sootup.java.bytecode.inputlocation;

import categories.TestCategories;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sootup.java.core.views.JavaView;

@Tag(TestCategories.JAVA_8_CATEGORY)
public class FixJars extends BaseFixJarsTest {

@Test
public void executeexcitecorejar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/com/parisesoftware/excite-core/1.0.2/excite-core-1.0.2.jar";
    String methodSignature = "<com.parisesoftware.excite.core.internal.transformer.operation.GPathResultTransformer: groovy.lang.MetaClass getMetaClass()>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

@Test
public void executeqtjambixextrasjrejar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/io/qtjambi/qtjambi-x11extras-jre8/5.15.5/qtjambi-x11extras-jre8-5.15.5.jar";
    String methodSignature = "<io.qt.x11extras.QX11Info$PeekOption: io.qt.x11extras.QX11Info$PeekOptions combined(io.qt.x11extras.QX11Info$PeekOption)>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

}