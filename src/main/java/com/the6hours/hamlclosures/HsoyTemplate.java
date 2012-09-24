package com.the6hours.hamlclosures;

import com.cadrlife.jhaml.JHaml;
import com.cadrlife.jhaml.JHamlParseException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Igor Artamonov (http://igorartamonov.com)
 * @since 15.07.12
 */
class HsoyTemplate {

    private String name;
    private List<String> doc = new ArrayList<String>();
    private List<String> body = new ArrayList<String>();
    private Pattern SOY_COMMAND_LINE = Pattern.compile("\\s*\\{([^\\}]+)\\}\\s*$");

    public HsoyTemplate() {
    }

    public void addBody(String line) {
        body.add(line);
    }

    public void addDoc(String line) {
        doc.add(line);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSoyBody() {
        JHaml jhaml = new JHaml();
        StringBuilder buf = new StringBuilder();
        for(String line: body) {
            Matcher m = SOY_COMMAND_LINE.matcher(line);
            if (m.matches()) {
                line = line.replaceAll("\\{(.+?)\\}", "%soy{:src=>\"$1\"}");
                //line = line.replaceAll("^(\\s+)\\{", "$1-{");
                //line = m.replaceFirst("%soy{:src=>\"$1\"}");
            }
            buf.append(line.substring(2)).append('\n');
        }
        String html;
        try {
            html = jhaml.parse(buf.toString());
        } catch (JHamlParseException e) {
            System.out.println(buf);
            throw e;
        }
        html = html.replaceAll("(?sm)(\\s*)<soy src='(.+?)'>(.*?)\\n\\1</soy>", "{$2}$3");
        html = html.replaceAll("(?sm)<soy src='(.+?)'></soy>", "{$1}");
        return html;
    }

    public String getSoyDocs() {
        StringBuilder buf = new StringBuilder();
        buf.append("/**\n");
        for (String line: doc) {
            buf.append("* ").append(line.trim()).append('\n');
        }
        buf.append("*/");
        return buf.toString();
    }
}
