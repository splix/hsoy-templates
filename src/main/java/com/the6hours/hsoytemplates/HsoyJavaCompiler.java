package com.the6hours.hsoytemplates;

import com.google.template.soy.SoyFileSet;
import com.google.template.soy.base.SoySyntaxException;
import com.google.template.soy.javasrc.SoyJavaSrcOptions;
import com.google.template.soy.jssrc.SoyJsSrcOptions;

import java.util.List;

/**
 * @author Igor Artamonov (http://igorartamonov.com)
 * @since 02.01.13
 */
public class HsoyJavaCompiler extends HsoyBaseCompiler {

    private String targetPackage;
    private String targetClass;
    private String targetExtends;

    public String compileToString(SoyFileSet soyFileSet) throws HsoyFormatException, SoySyntaxException {
        SoyJavaSrcOptions javaSrcOptions = new SoyJavaSrcOptions();
        javaSrcOptions.setCodeStyle(SoyJavaSrcOptions.CodeStyle.STRINGBUILDER);
        String compiledSrcs = soyFileSet.compileToJavaSrc(javaSrcOptions, null);
        StringBuilder buf = new StringBuilder();
        if (targetPackage != null) {
            buf.append("package ").append(targetPackage).append(";\n");
        }
        if (targetClass != null) {
            buf.append("public class ").append(targetClass).append(' ');
            if (targetExtends != null) {
                buf.append("extends ").append(targetExtends);
            }
            buf.append("{\n\n");
        }
        buf.append(compiledSrcs);
        if (targetClass != null) {
            buf.append("\n}");
        }
        return buf.toString();
    }

    public String getTargetPackage() {
        return targetPackage;
    }

    public void setTargetPackage(String targetPackage) {
        this.targetPackage = targetPackage;
    }

    public String getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(String targetClass) {
        this.targetClass = targetClass;
    }

    public String getTargetExtends() {
        return targetExtends;
    }

    public void setTargetExtends(String targetExtends) {
        this.targetExtends = targetExtends;
    }
}
