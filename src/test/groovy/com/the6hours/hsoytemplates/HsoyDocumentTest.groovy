package com.the6hours.hsoytemplates

import spock.lang.Specification
import com.the6hours.hsoytemplates.HsoyParser
import com.the6hours.hsoytemplates.HsoyDocument

/**
 * 
 * @author Igor Artamonov (http://igorartamonov.com)
 * @since 17.08.12
 */
class HsoyDocumentTest extends Specification{

    def "file test2.hsoy"() {
        setup:
            String hsoy = this.class.classLoader.getResourceAsStream('hamlsoy/test2.hsoy').text
            String soy = this.class.classLoader.getResourceAsStream('hamlsoy/test2.soy').text
            HsoyParser parser = new HsoyParser()
        when:
            HsoyDocument doc = parser.parse(hsoy)
            String act = doc.soy
        then:
            act == soy
    }

    def "file test-template-params.hsoy"() {
        setup:
            String hsoy = this.class.classLoader.getResourceAsStream('hamlsoy/test-template-params.hsoy').text
            String soy = this.class.classLoader.getResourceAsStream('hamlsoy/test-template-params.soy').text
            HsoyParser parser = new HsoyParser()
        when:
            HsoyDocument doc = parser.parse(hsoy)
            String act = doc.soy
        then:
            act == soy
    }

    def "file test-complex.hsoy"() {
        setup:
            String hsoy = this.class.classLoader.getResourceAsStream('hamlsoy/test-complex.hsoy').text
            String soy = this.class.classLoader.getResourceAsStream('hamlsoy/test-complex.soy').text
            HsoyParser parser = new HsoyParser()
        when:
            HsoyDocument doc = parser.parse(hsoy)
            String act = doc.soy
        then:
            act == soy
    }

    def "file test-brackets.hsoy"() {
        setup:
            String hsoy = this.class.classLoader.getResourceAsStream('hamlsoy/test-brackets.hsoy').text
            String soy = this.class.classLoader.getResourceAsStream('hamlsoy/test-brackets.soy').text
            HsoyParser parser = new HsoyParser()
        when:
            HsoyDocument doc = parser.parse(hsoy)
            String act = doc.soy
        then:
            act == soy
    }
}
