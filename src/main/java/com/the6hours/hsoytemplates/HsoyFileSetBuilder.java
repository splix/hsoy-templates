package com.the6hours.hsoytemplates;

import com.google.template.soy.SoyFileSet;

import java.io.*;
import java.net.URL;

/**
 * @author Igor Artamonov (http://igorartamonov.com)
 * @since 04.12.12
 */
public class HsoyFileSetBuilder {
    
    private SoyFileSet.Builder builder;
    private HsoyParser parser = new HsoyParser();

    public HsoyFileSetBuilder() {
        builder = new SoyFileSet.Builder();
    }

    public HsoyFileSetBuilder add(File file) throws IOException, HsoyFormatException {
        BufferedReader in = new BufferedReader(new FileReader(file));
        StringBuilder buf = new StringBuilder();
        try {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                buf.append(inputLine).append('\n');
            }
        } finally {
            in.close();
        }
        return add(buf, file.getName());
    }
    
    public HsoyFileSetBuilder add(URL url, String encoding) throws IOException, HsoyFormatException {
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), encoding));
        StringBuilder buf = new StringBuilder();
        try {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                buf.append(inputLine).append('\n');
            }
        } finally {
            in.close();
        }
        return add(buf, url.toString());
    }
    
    public HsoyFileSetBuilder add(URL url) throws IOException, HsoyFormatException {
        return add(url, "UTF-8");
    }
    
    public HsoyFileSetBuilder add(CharSequence input, String fileName) throws HsoyFormatException {
        HsoyDocument document = parser.parse(input.toString());
        builder.add(document.getSoy(), fileName);
        return this;
    }

    public SoyFileSet build() {
        return builder.build();
    }
}
