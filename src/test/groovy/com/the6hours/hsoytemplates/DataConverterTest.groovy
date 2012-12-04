package com.the6hours.hsoytemplates

import spock.lang.Specification
import com.google.template.soy.data.SoyListData
import com.google.template.soy.data.SoyMapData

/**
 * 
 * @author Igor Artamonov (http://igorartamonov.com)
 * @since 04.12.12
 */
class DataConverterTest extends Specification {

    def "Basic data"() {
        setup:
            DataConverter converter = new DataConverter()
        when:
            def x = converter.soyValue(1)
        then:
            x == 1
        when:
            x = converter.soyValue("hi")
        then:
            x == "hi"
    }

    def "Collection"() {
        setup:
            DataConverter converter = new DataConverter()
        when:
            def x = converter.toSoy([1, 2])
        then:
            x instanceof SoyListData
            x.get(0).integerValue() == 1
            x.get(1).integerValue() == 2
    }

    def "Map"() {
        setup:
            DataConverter converter = new DataConverter()
        when:
            def x = converter.toSoy(['x': 1, 'y': 2L])
        then:
            x instanceof SoyMapData
            x.get('x').integerValue() == 1
            x.get('y').stringValue() == "2"
    }

    def "Complex"() {
        setup:
            DataConverter converter = new DataConverter()
        when:
            def x = converter.toSoy(['x': 1, 'y': [2, 3, 5], 'z': ['foo': 'bar']])
        then:
            x instanceof SoyMapData
            x.get('x').integerValue() == 1
            x.get('y') instanceof SoyListData
            x.get('y').get(0).integerValue() == 2
            x.get('y').get(1).integerValue() == 3
            x.get('y').get(2).integerValue() == 5
            x.get('z') instanceof SoyMapData
            x.get('z').getAt('foo').stringValue() == 'bar'
    }

    def "Use custom converter"() {
        setup:
            DataConverter converter = new DataConverter(new CustomDataConverter() {
                Object tryConvert(Object input, DataConverter current) {
                    if (input instanceof Throwable) {
                        return input.message
                    }
                    return null
                }
            })
        when:
            def x = converter.soyValue(new RuntimeException("Test"))
        then:
            x == 'Test'
    }

}

