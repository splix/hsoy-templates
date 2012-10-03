package com.the6hours.hsoytemplates;

import com.cadrlife.jhaml.JHaml;
import com.cadrlife.jhaml.JHamlParseException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Igor Artamonov (http://igorartamonov.com)
 * @since 15.07.12
 */
class HsoyTemplate {

    private String name;
    private Map<String, String> attributes = new HashMap<String, String>();
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
        List<String> soyCommands = new ArrayList<String>();
        for(String line: body) {
            Matcher m = SOY_COMMAND_LINE.matcher(line);
            if (m.matches()) {
                soyCommands.add(m.group(1));
                line = line.replaceAll("\\{(.+?)\\}", "%soy-idx" + (soyCommands.size() -1));
            }
            line = line.replaceAll("\\{\\$(\\w+)\\}", "HSOY-PASTE-$1");
            buf.append(line.substring(2)).append('\n');
        }
        String html;
        try {
            html = jhaml.parse(buf.toString());
            //System.out.println("HTML: " + html);
        } catch (JHamlParseException e) {
            System.out.println(buf);
            throw e;
        }
        for (int idx = 0; idx < soyCommands.size(); idx++) {
            String shouldBe = soyCommands.get(idx).replaceAll("\\$", "\\\\\\$");
            //System.out.println(shouldBe);
            String tagName = "soy-idx"+idx;
            html = html.replaceAll("(?sm)(\\s*)<" + tagName + ">(.*?)\\n\\1</"+ tagName +">",
                    "$1{"+shouldBe+"}$2");
            html = html.replaceAll("(?sm)<" + tagName+ "></"+tagName+">",
                    "{"+shouldBe+"}");
        }
        html = html.replaceAll("HSOY-PASTE-(\\w+)", "{\\$$1}");
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

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public String getSoyAttributes() {
        if (attributes.isEmpty()) {
            return "";
        }
        StringBuilder buf = new StringBuilder();
        for (Map.Entry<String, String> attr: attributes.entrySet()) {
            buf.append(attr.getKey())
                    .append("=\"")
                    .append(attr.getValue())
                    .append("\" ");
        }
        buf.deleteCharAt(buf.length() - 1);
        return buf.toString();
    }
}
