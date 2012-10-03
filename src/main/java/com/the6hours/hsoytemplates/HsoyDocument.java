package com.the6hours.hsoytemplates;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Igor Artamonov (http://igorartamonov.com)
 * @since 15.07.12
 */
public class HsoyDocument {

    private String namespace;
    private List<HsoyTemplate> templates = new ArrayList<HsoyTemplate>();

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public List<HsoyTemplate> getTemplates() {
        return templates;
    }

    public void setTemplates(List<HsoyTemplate> templates) {
        this.templates = templates;
    }

    public String getSoy() {
        StringBuilder buf = new StringBuilder();
        buf.append("{namespace ").append(namespace).append("}\n\n");
        if (!templates.isEmpty()) {
            for (HsoyTemplate tpl: templates) {
                buf.append(tpl.getSoyDocs()).append('\n');
                buf.append("{template .")
                        .append(tpl.getName());
                String templateAttributes = tpl.getSoyAttributes();
                if (templateAttributes.length() > 0) {
                    buf.append(' ').append(templateAttributes);
                }
                buf.append("}\n");
                buf.append(tpl.getSoyBody()).append('\n');
                buf.append("{/template}\n\n");
            }
            buf.delete(buf.length()-2, buf.length());
        }
        return buf.toString();
    }
}
