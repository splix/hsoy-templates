package com.the6hours.hamlclosures;

import com.google.template.soy.SoyFileSet;

import java.io.*;
import java.util.List;

/**
 * @author Igor Artamonov (http://igorartamonov.com)
 * @since 23.09.12
 */
public class HsoyJsCompiler {

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
