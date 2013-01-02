package com.the6hours.hsoytemplates;

import com.google.template.soy.SoyFileSet;
import com.google.template.soy.base.SoySyntaxException;
import com.google.template.soy.jssrc.SoyJsSrcOptions;

import java.io.*;
import java.util.List;

/**
 * @author Igor Artamonov (http://igorartamonov.com)
 * @since 23.09.12
 */
public class HsoyJsCompiler extends HsoyBaseCompiler {

    private boolean shouldProvideRequireSoyNamespaces = false;
    private boolean shouldGenerateJsdoc = false;

    @Override
    public String compileToString(SoyFileSet fileSet) throws HsoyFormatException, SoySyntaxException {
        SoyJsSrcOptions jsSrcOptions = new SoyJsSrcOptions();
        jsSrcOptions.setShouldProvideRequireSoyNamespaces(shouldProvideRequireSoyNamespaces);
        jsSrcOptions.setShouldGenerateJsdoc(shouldGenerateJsdoc);
        List<String> compiledSrcs = fileSet.compileToJsSrc(jsSrcOptions, null);
        if (compiledSrcs.size() == 1) {
            return compiledSrcs.get(0);
        }
        StringBuilder buf = new StringBuilder();
        for (String js: compiledSrcs) {
            buf.append(js).append('\n');
        }
        return buf.toString();
    }

    public boolean isShouldProvideRequireSoyNamespaces() {
        return shouldProvideRequireSoyNamespaces;
    }

    public void setShouldProvideRequireSoyNamespaces(boolean shouldProvideRequireSoyNamespaces) {
        this.shouldProvideRequireSoyNamespaces = shouldProvideRequireSoyNamespaces;
    }

    public boolean isShouldGenerateJsdoc() {
        return shouldGenerateJsdoc;
    }

    public void setShouldGenerateJsdoc(boolean shouldGenerateJsdoc) {
        this.shouldGenerateJsdoc = shouldGenerateJsdoc;
    }
}
