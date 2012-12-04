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
public class HsoyJsCompiler {

    public String compileToString(String input) throws HsoyFormatException, SoySyntaxException {
        return compileToString(input, "unknown.hsoy");
    }

    public String compileToString(String input, String name) throws HsoyFormatException, SoySyntaxException {
        HsoyParser parser = new HsoyParser();
        HsoyDocument document = parser.parse(input);
        String soy = document.getSoy();
        SoyFileSet.Builder sfs = new SoyFileSet.Builder();
        sfs.add(soy, name);

        SoyFileSet soyFileSet = sfs.build();

        SoyJsSrcOptions jsSrcOptions = new SoyJsSrcOptions();
        jsSrcOptions.setShouldProvideRequireSoyNamespaces(false);
        jsSrcOptions.setShouldGenerateJsdoc(false);
        List<String> compiledSrcs = soyFileSet.compileToJsSrc(jsSrcOptions, null);
        assert compiledSrcs.size() == 1;
        return compiledSrcs.get(0);
    }

    public SoyFileSet build(List<File> files) throws HsoyFormatException, IOException {
        HsoyParser parser = new HsoyParser();

        SoyFileSet.Builder sfs = new SoyFileSet.Builder();
        for (File f: files) {
            try {
                HsoyDocument document = parser.parse(read(f));
                sfs.add(document.getSoy(), f.getName());
            } catch (HsoyFormatException e) {
                throw new HsoyFormatException(String.format("Invalid format for file %s", f.getName()), e);
            }
        }

        return sfs.build();
    }

    public SoyFileSet build(String input, String filename) throws HsoyFormatException {
        HsoyParser parser = new HsoyParser();
        HsoyDocument document = parser.parse(input);

        SoyFileSet.Builder sfs = new SoyFileSet.Builder();
        sfs.add(document.getSoy(), filename);
        return sfs.build();
    }

    private String read(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line;
        StringBuilder buf = new StringBuilder((int)file.length());
        while ((line = reader.readLine()) != null) {
            buf.append(line).append('\n');
        }
        return buf.toString();
    }

}
