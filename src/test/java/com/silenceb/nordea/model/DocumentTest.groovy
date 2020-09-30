package com.silenceb.nordea.model

import spock.lang.Specification

class DocumentTest extends Specification {


    def "Document should create new sentence when last word is added"() {
        given:
            def document = new Document()
        when:
            document.addWord(Word.fromString("Quick"))
            document.addWord(Word.fromString("brown"))
            document.addWord(Word.fromString("fox."))
            document.addWord(Word.fromString("Jumps"))
            document.addWord(Word.fromString("over"))
            document.addWord(Word.fromString("a"))
            document.addWord(Word.fromString("lazy"))
            document.addWord(Word.fromString("dog."))
            def sentences = document.getSentences()
        then:
            sentences.size() == 2
            sentences[0].words.size() == 3
            sentences[1].words.size() == 5
    }
}
