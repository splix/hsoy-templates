package com.the6hours.hsoytemplates;

import com.google.template.soy.SoyFileSet;
import com.google.template.soy.base.SoySyntaxException;

import java.io.*;
import java.util.List;

/**
 * @author Igor Artamonov (http://igorartamonov.com)
 * @since 02.01.13
 */
public abstract class HsoyBaseCompiler {

    private String sourceEncoding = "UTF-8";

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
        return compileToString(soyFileSet);
    }

    public abstract String compileToString(SoyFileSet fileSet) throws HsoyFormatException, SoySyntaxException;

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


    public String read(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), sourceEncoding));
        String line;
        StringBuilder buf = new StringBuilder((int)file.length());
        while ((line = reader.readLine()) != null) {
            buf.append(line).append('\n');
        }
        return buf.toString();
    }

    public String getSourceEncoding() {
        return sourceEncoding;
    }

    public void setSourceEncoding(String sourceEncoding) {
        this.sourceEncoding = sourceEncoding;
    }
}
