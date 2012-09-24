package com.the6hours.hamlclosures;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Igor Artamonov (http://igorartamonov.com)
 * @since 15.07.12
 */
public class HsoyParser {

    private static final int MODE_UNKNOWN = 0;
    private static final int MODE_DOC = 1;
    private static final int MODE_BODY = 2;


    private static final Pattern namespace = Pattern.compile("^!!! namespace\\s+(.+?)\\s*$");

    public HsoyDocument parse(String soy) throws HsoyFormatException {
        HsoyDocument doc = new HsoyDocument();
        String[] lines = soy.trim().split("\n");
        Matcher m = namespace.matcher(lines[0]);
        if (!m.matches()) {
            throw new HsoyFormatException("Can't find templates namespace");
        }
        doc.setNamespace(m.group(1));

        List<String> templateBody = new ArrayList<String>();
        List<String> templateDoc = new ArrayList<String>();
        HsoyTemplate currentTemplate = null;

        int mode = MODE_UNKNOWN;

        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            if (line.startsWith("#")) {
                //start template body
                String name = line.substring(1).trim();
                if (currentTemplate == null) {
                    throw new HsoyFormatException(
                            String.format("Found start of template %1s (line %2s) w/o doc",
                                    name, i));
                }
                if (name.length() == 0) {
                    throw new HsoyFormatException(
                            String.format("Empty template name at line %1s",
                                    i));
                }
                currentTemplate.setName(name);
                mode = MODE_BODY;
            } else if (line.startsWith("/")) {
                //start new template
                if (currentTemplate != null) {
                    doc.getTemplates().add(currentTemplate);
                }
                currentTemplate = new HsoyTemplate();
                line = line.trim();
                if (line.length() > 1) {
                    line = line.substring(1).trim();
                    currentTemplate.addDoc(line);
                }
                mode = MODE_DOC;
            } else if (line.startsWith("  ")) {
                //content of doc or body
                if (mode == MODE_DOC) {
                    currentTemplate.addDoc(line);
                } else if (mode == MODE_BODY) {
                    currentTemplate.addBody(line);
                }
            }
        }
        if (currentTemplate != null) {
            doc.getTemplates().add(currentTemplate);
        }
        return doc;
    }
}
