package com.the6hours.hsoytemplates

import spock.lang.Specification

/**
 *
 * @author Igor Artamonov (http://igorartamonov.com)
 * @since 02.01.13
 */
class HsoyJavaCompilerTest extends Specification {

    def "Can compile" () {
        setup:
            String hsoy = this.class.classLoader.getResourceAsStream('hamlsoy/test-complex.hsoy').text
            HsoyJavaCompiler compiler = new HsoyJavaCompiler()
            compiler.targetClass = 'CompiledHsoy'
            compiler.targetPackage = 'com.the6hours.tests'
        when:
            String act = compiler.compileToString(hsoy)
            println act
        then:
            act
            act.length() > 0
            act.startsWith("package")
    }
}
