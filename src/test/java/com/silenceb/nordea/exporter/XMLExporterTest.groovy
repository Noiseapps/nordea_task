package com.silenceb.nordea.exporter

import com.silenceb.nordea.model.Sentence
import com.silenceb.nordea.model.Word
import spock.lang.Specification

class XMLExporterTest extends Specification {

    def cleanup() {
        def file = new File("output.xml")
        file.delete()
    }

    def "Exporter start should create file with a header and opening tag"() {
        given:
            def exporter = new XMLExporter()
            exporter.xmlHeader = "<test header>"
            exporter.xmlRootElement = "text"
            exporter.xmlSentence = "sentence"
            exporter.xmlWord = "word"
        when:
            exporter.start()
            def file = new File("output.xml")
        then:
            file.exists()
            file.readLines() == ["<test header>", "<text>"]
    }

    def "Exporter finish should create file with a closing tag"() {
        given:
            def exporter = new XMLExporter()
            exporter.xmlHeader = "<test header>"
            exporter.xmlRootElement = "text"
            exporter.xmlSentence = "sentence"
            exporter.xmlWord = "word"
        when:
            exporter.finish()
            def file = new File("output.xml")
        then:
            file.exists()
            file.readLines() == ["</text>"]
    }

    def "Sentence should be written as single line with sorted words"() {
        given:
            def exporter = new XMLExporter()
            exporter.xmlHeader = "<test header>"
            exporter.xmlRootElement = "text"
            exporter.xmlSentence = "sentence"
            exporter.xmlWord = "word"

            def sentence = new Sentence()
            sentence = sentence.sentenceByAddingWord(Word.fromString("test"))
            sentence = sentence.sentenceByAddingWord(Word.fromString("sentence"))
        when:
            exporter.export(sentence)
            def file = new File("output.xml")
        then:
            file.exists()
            file.readLines().size() == 1
            file.readLines() == ["<sentence><word>sentence</word><word>test</word></sentence>"]
    }


    def "Each sentence should be written in separate lines"() {

        given:
            def exporter = new XMLExporter()
            exporter.xmlHeader = "<test header>"
            exporter.xmlRootElement = "text"
            exporter.xmlSentence = "sentence"
            exporter.xmlWord = "word"

            def sentence = new Sentence()
            sentence = sentence.sentenceByAddingWord(Word.fromString("test"))
            sentence = sentence.sentenceByAddingWord(Word.fromString("sentence"))
        when:
            exporter.start()
            exporter.export(sentence)
            exporter.export(sentence.sentenceByAddingWord(Word.fromString("sample")))
            exporter.finish()
            def file = new File("output.xml")
        then:
            file.exists()
            file.readLines().size() == 5
            file.readLines() == ["<test header>",
                                 "<text>",
                                 "<sentence><word>sentence</word><word>test</word></sentence>",
                                 "<sentence><word>sample</word><word>sentence</word><word>test</word></sentence>",
                                 "</text>"]
    }
}
