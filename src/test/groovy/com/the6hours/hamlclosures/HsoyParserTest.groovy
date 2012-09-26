package com.the6hours.hamlclosures

import spock.lang.Specification

/**
 * 
 * @author Igor Artamonov (http://igorartamonov.com)
 * @since 17.08.12
 */
class HsoyParserTest extends Specification {

    def "file test1.hsoy"() {
        setup:
            String hsoy = this.class.classLoader.getResourceAsStream('hamlsoy/test1.hsoy').text
            HsoyParser parser = new HsoyParser()
        when:
            HsoyDocument doc = parser.parse(hsoy)
        then:
            doc
            doc.namespace == 'tests.test1'
            doc.templates.size() == 4
            doc.templates[0].name == 'hello'
            doc.templates[1].name == 'helloWithTags'
            doc.templates[2].name == 'helloWithTagsAndParam'
            doc.templates[3].name == 'helloWithTagsAndLogic'
            doc.templates[0].soyBody == 'Hello world!'
            doc.templates[1].soyBody == "<div class='span5'>\n  Hello world!\n</div>"
            doc.templates[2].soyBody == "<div class='span5'>\n  Hello {\$name}!\n</div>"
            doc.templates[3].soyBody == "{if not \$greetingWord}\n" +
                    "  Hello {\$name}!\n" +
                    "{else}\n" +
                    "  {\$greetingWord} {\$name}!\n" +
                    "{/if}"
            doc.templates[0].soyDocs == "/**\n* Says hello to the world.\n*/"
            doc.templates[1].soyDocs == "/**\n* Say hello wrapped into <div class=\"span5\">\n*/"
            doc.templates[2].soyDocs == "/**\n" +
                    "* Say hello wrapped into <div class=\"span5\">\n" +
                    "* @param name The name of the person.\n" +
                    "*/"
            doc.templates[3].soyDocs == "/**\n" +
                    "* Greets a person using \"Hello\" by default.\n" +
                    "* @param name The name of the person.\n" +
                    "* @param? greetingWord Optional greeting word to use instead of \"Hello\".\n" +
                    "*/"
    }

    def "file test2.hsoy"() {
        setup:
            String hsoy = this.class.classLoader.getResourceAsStream('hamlsoy/test2.hsoy').text
            HsoyParser parser = new HsoyParser()
        when:
            HsoyDocument doc = parser.parse(hsoy)
        then:
            doc
            doc.namespace == 'tests.test2'
            doc.templates.size() == 1
            doc.templates[0].name == 'x1'
            doc.templates[0].soyBody == "<h1>\n" +
                    "  A Greeting\n" +
                    "</h1>\n" +
                    "{if not \$greetingWord}\n" +
                    "  <div class='default'>\n" +
                    "    Hello {\$name}!\n" +
                    "  </div>\n" +
                    "{else}\n" +
                    "  <h2>{\$greetingWord} {\$name}!</h2>\n" +
                    "{/if}"
    }

    def "file test-template-params.hsoy"() {
        setup:
            String hsoy = this.class.classLoader.getResourceAsStream('hamlsoy/test-template-params.hsoy').text
            HsoyParser parser = new HsoyParser()
        when:
            HsoyDocument doc = parser.parse(hsoy)
        then:
            doc
            doc.namespace == 'tests.test1'
            doc.templates.size() == 1
            doc.templates[0].name == 'hello'
            doc.templates[0].attributes == ['autoescape': 'false']
    }
}
