package sootup.java.bytecode.inputlocation;

import categories.TestCategories;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sootup.java.core.views.JavaView;

@Tag(TestCategories.JAVA_8_CATEGORY)
public class FixJars extends BaseFixJarsTest {

@Test
public void executejarbconstraints(){
	String jarName = "jarb-constraints-2.4.3.jar";
    String methodSignature = "<org.jarbframework.constraint.violation.resolver.vendor.HsqlViolationResolver$NotNullPattern: void <init>()>";
    JavaView javaView = supplyJavaView(jarName);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

@Test
public void executeorgeclipseepsilonhutnunparser(){
	String jarName = "org.eclipse.epsilon.hutn.unparser-2.5.0.jar";
    String methodSignature = "<org.eclipse.epsilon.hutn.unparser.internal.PackageObjectUnparser: void unparseClassObjects()>";
    JavaView javaView = supplyJavaView(jarName);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

@Test
public void executevraptorpluginhibernate(){
	String jarName = "vraptor-plugin-hibernate4-1.5.0.jar";
    String methodSignature = "<br.com.caelum.vraptor.plugin.hibernate4.HibernateTransactionInterceptor: void <init>(org.hibernate.Session,br.com.caelum.vraptor.Validator)>";
    JavaView javaView = supplyJavaView(jarName);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

@Test
public void executexoaicommon(){
	String jarName = "xoai-common-4.1.0.jar";
    String methodSignature = "<com.lyncode.xoai.model.oaipmh.Set: java.util.List getDescriptions()>";
    JavaView javaView = supplyJavaView(jarName);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

@Test
public void executezioawsoutposts(){
	String jarName = "zio-aws-outposts_2.13-7.21.15.15.jar";
    String methodSignature = "<zio.aws.outposts.model.Outpost$: zio.prelude.data.Optional apply$default$2()>";
    JavaView javaView = supplyJavaView(jarName);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

}