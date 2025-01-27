package sootup.java.bytecode.inputlocation;

import categories.TestCategories;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sootup.java.core.views.JavaView;

@Tag(TestCategories.JAVA_8_CATEGORY)
public class FixJars extends BaseFixJarsTest {

@Test
public void executerdfvalidatedatatypejar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/org/webjars/npm/rdf-validate-datatype/0.2.1/rdf-validate-datatype-0.2.1.jar";
    String methodSignature = "";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

@Test
public void executefifecommonjar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/com/fifesoft/rtext/fife.common/6.0.3/fife.common-6.0.3.jar";
    String methodSignature = "<org.fife.ctags.CTagReader: boolean findNext(org.fife.ctags.TagEntry)>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

}