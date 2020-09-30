package com.silenceb.nordea.model

import spock.lang.Specification

class SentenceTest extends Specification {


    def "sentence toString should return valid sentence based on words"() {
        given:
            def sentence = new Sentence()
            sentence = sentence.sentenceByAddingWord(Word.fromString("Quick"))
            sentence = sentence.sentenceByAddingWord(Word.fromString("brown"))
            sentence = sentence.sentenceByAddingWord(Word.fromString("fox"))
            sentence = sentence.sentenceByAddingWord(Word.fromString("jumps"))
            sentence = sentence.sentenceByAddingWord(Word.fromString("over"))
            sentence = sentence.sentenceByAddingWord(Word.fromString("a"))
            sentence = sentence.sentenceByAddingWord(Word.fromString("lazy"))
            sentence = sentence.sentenceByAddingWord(Word.fromString("dog"))

        when:
            def sentenceString = sentence.toString()
        then:
            sentenceString == "Quick brown fox jumps over a lazy dog."
    }

    def "Sentence should be immutable"() {
        given:
            def sentence = new Sentence()
        when:
            sentence.sentenceByAddingWord(Word.fromString("Quick"))
        then:
            sentence.words.size() == 0

    }
}
