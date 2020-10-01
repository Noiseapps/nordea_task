package com.silenceb.nordea.analyzer

import com.silenceb.nordea.exporter.Exporter
import spock.lang.Specification

class LineAnalyzerTest extends Specification {

    def "Exporter should be notified for each new sentence"() {
        given:
            def mockExporter = Mock(Exporter)
            def analyzer = new LineAnalyzer(mockExporter)
            def line = ["Quick", "brobn", "fox", "jumps.", "Over", "a", "lazy", "dog."]
        when:
            analyzer.analyze(line)
        then:
            2 * mockExporter.addSentence(_)
    }

    def "Creating analyzer with null exporter should fail"() {
        when:
            new LineAnalyzer(null)
        then:
            thrown(IllegalArgumentException)

    }
}
