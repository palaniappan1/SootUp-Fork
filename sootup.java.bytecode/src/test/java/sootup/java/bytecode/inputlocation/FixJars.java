package sootup.java.bytecode.inputlocation;

import categories.TestCategories;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sootup.java.core.views.JavaView;

@Tag(TestCategories.JAVA_8_CATEGORY)
public class FixJars extends BaseFixJarsTest {

@Test
public void executemetaconfigpprintsjsjar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/org/scalameta/metaconfig-pprint_sjs1_2.12/0.13.0/metaconfig-pprint_sjs1_2.12-0.13.0.jar";
    String methodSignature = "<metaconfig.pprint.TPrint$$anon$1: fansi.Str render(metaconfig.pprint.TPrintColors)>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

@Test
public void executeftrgwtcalendaruijar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/eu/future-earth/gwt/ftr-gwt-calendar-ui/3.3/ftr-gwt-calendar-ui-3.3.jar";
    String methodSignature = "<eu.future.earth.gwt.client.date.horizontal.BaseHorizontalDateRenderer: eu.future.earth.gwt.client.date.horizontal.HorizontalRemoveCallBackHandler createRemoveHandler(eu.future.earth.gwt.client.date.horizontal.HorizontalViewPanelNoDays,java.lang.Object)>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

}