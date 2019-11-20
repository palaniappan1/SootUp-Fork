package de.upb.swt.soot.test.java.sourcecode.typehierarchy;

import de.upb.swt.soot.core.inputlocation.AnalysisInputLocation;
import de.upb.swt.soot.java.core.JavaIdentifierFactory;
import de.upb.swt.soot.java.core.JavaProject;
import de.upb.swt.soot.java.core.language.JavaLanguage;
import de.upb.swt.soot.java.core.types.JavaClassType;
import de.upb.swt.soot.java.core.views.JavaView;
import de.upb.swt.soot.java.sourcecode.inputlocation.JavaSourcePathAnalysisInputLocation;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.util.Collections;

/** @author: Hasitha Rajapakse **/


public class JavaTypeHierarchyBase {

    static final String baseDir = "src/test/resources/javatypehierarchy/";
    protected JavaIdentifierFactory identifierFactory = JavaIdentifierFactory.getInstance();


    @ClassRule public static CustomTestWatcher customTestWatcher = new CustomTestWatcher();

    public static class CustomTestWatcher extends TestWatcher {
        private String className = JavaTypeHierarchyBase.class.getSimpleName();
        private AnalysisInputLocation srcCode;
        private JavaView view;
        private JavaProject project;

        /** Load WalaClassLoader once for each test directory */
        @Override
        protected void starting(Description description) {
            String prevClassName = getClassName();
            setClassName(extractClassName(description.getClassName()));
            if (!prevClassName.equals(getClassName())) {
                srcCode = new JavaSourcePathAnalysisInputLocation(Collections.singleton(baseDir+"/"+getClassName()));
                project = JavaProject.builder(new JavaLanguage(8)).addClassPath(this.srcCode).build();
                setView(project.createFullView());
            }
        }

        public String getClassName() {
            return className;
        }

        private void setClassName(String className) {
            this.className = className;
        }

        private void setView(JavaView view) {
            this.view = view;
        }

        public JavaView getView() {
            return view;
        }
    }

    public JavaClassType getClassType(String className){
        return identifierFactory.getClassType(className);
    }

    public static String extractClassName(String classPath) {
        String classPathArray = classPath.substring(classPath.lastIndexOf(".")+1);
        String testDirectoryName = "";
        if (!classPathArray.isEmpty()) {
            testDirectoryName = classPathArray.substring(0,classPathArray.length()-4);
        }
        return testDirectoryName;
    }

    @Test
    public void method(){
    }
}
