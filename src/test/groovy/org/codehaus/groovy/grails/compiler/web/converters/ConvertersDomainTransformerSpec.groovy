package org.codehaus.groovy.grails.compiler.web.converters

import grails.converters.XML
import org.codehaus.groovy.grails.compiler.injection.ClassInjector
import org.codehaus.groovy.grails.compiler.injection.GrailsAwareClassLoader
import spock.lang.Specification

/**
 * Created by IntelliJ IDEA.
 * User: graemerocher
 * Date: 28/03/2011
 * Time: 10:16
 * To change this template use File | Settings | File Templates.
 */
class ConvertersDomainTransformerSpec extends Specification{


    void "Test domain type conversion methods added at compile time"() {
       given:
            def gcl = new GrailsAwareClassLoader()
            def transformer = new ConvertersDomainTransformer() {
                @Override
                boolean shouldInject(URL url) {
                    return true;
                }

            }
            gcl.classInjectors = [transformer] as ClassInjector[]

        when:
            def cls = gcl.parseClass('''

class ConvertMe {
    String name
}

''')

            def xml = cls.newInstance(name:"Bob") as XML

        then:
            xml != null
            xml instanceof XML

    }
}
