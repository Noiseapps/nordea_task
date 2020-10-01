package com.silenceb.nordea.tokenizer


import spock.lang.Specification

class LineTokenizerTest extends Specification {


    def "Simple line should be tokenized"() {
        given:
        def tokenizer = new LineTokenizer()
        def line = "Quick brown fox jumps over a lazy dog."
        when:
        def tokenizedLine = tokenizer.tokenizeLine(line)
        then:
        tokenizedLine.size() == 8
        tokenizedLine == ["Quick", "brown", "fox", "jumps", "over", "a", "lazy", "dog."]
    }

    def "Line with commas should be tokenized"() {
        given:
        def tokenizer = new LineTokenizer()
        def line = "Quick, brown fox, jumps over, a lazy dog."
        when:
        def tokenizedLine = tokenizer.tokenizeLine(line)
        then:
        tokenizedLine.size() == 8
        tokenizedLine == ["Quick", "brown", "fox", "jumps", "over", "a", "lazy", "dog."]
    }

    def "Line with dashes should be tokenized"() {
        given:
            def tokenizer = new LineTokenizer()
            def line = "Quick-brown fox-, jumps over, a lazy dog."
        when:
            def tokenizedLine = tokenizer.tokenizeLine(line)
        then:
            tokenizedLine.size() == 7
            tokenizedLine == ["Quick-brown", "fox", "jumps", "over", "a", "lazy", "dog."]
    }

    def "Line with excessive white space should be tokenized"() {
        given:
        def tokenizer = new LineTokenizer()
        def line = "Quick       brown fox      jumps     over a lazy   dog."
        when:
        def tokenizedLine = tokenizer.tokenizeLine(line)
        then:
        tokenizedLine.size() == 8
        tokenizedLine == ["Quick", "brown", "fox", "jumps", "over", "a", "lazy", "dog."]
    }

    def "Line with multiple sentences should be tokenized"() {
        given:
        def tokenizer = new LineTokenizer()
        def line = "Quick brown fox. Jumps over a lazy dog."
        when:
        def tokenizedLine = tokenizer.tokenizeLine(line)
        then:
        tokenizedLine.size() == 8
        tokenizedLine == ["Quick", "brown", "fox.", "Jumps", "over", "a", "lazy", "dog."]
    }

    def "Empty words should be filtered out"() {
        given:
        def tokenizer = new LineTokenizer()
        def line = "Quick brown fox. Jumps, ,     , over a lazy dog."
        when:
        def tokenizedLine = tokenizer.tokenizeLine(line)
        then:
        tokenizedLine.size() == 8
        tokenizedLine == ["Quick", "brown", "fox.", "Jumps", "over", "a", "lazy", "dog."]
    }
}
