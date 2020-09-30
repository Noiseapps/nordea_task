package com.silenceb.nordea.model

import spock.lang.Specification
import spock.lang.Unroll


@Unroll
class WordTest extends Specification {


    def "Valid word (#input) should be converted to (#output)"() {
        when:
            def word = Word.fromString("Word")
        then:
            word != null
            word.toString() == "Word"
        where:
            input         | output
            "Word"        | "Word"
            "Word     "   | "Word"
            "   Word"     | "Word"
            "   WORD "    | "WORD"
            "你这肮脏的掠夺者   " | "你这肮脏的掠夺者"
    }

    def "Invalid word (#input) should return #output"() {
        when:
            def word = Word.fromString(input)
        then:
            word == output
        where:
            input | output
            ""    | null
            null  | null
    }

    def "Word (#input) should return whether they are (#output) last in the sentence"() {
        when:
            def word = Word.fromString(input)
        then:
            word.isLastWordInSentence() == output
        where:
            input       | output
            "Quick"     | false
            "Quick    " | false
            "Quick   . " | true
            "Quick.    " | true
            "Quick." | true


    }
}
