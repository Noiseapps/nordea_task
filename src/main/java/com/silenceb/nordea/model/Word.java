package com.silenceb.nordea.model;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Word {
    private final String word;
    private final boolean isLastWordInSentence;

    public static Word fromString(String word) {
        try {
            return new Word(word);
        } catch (IllegalArgumentException e) {
            // invalid (null or empty word), omit.
            return null;
        }
    }

    private Word(String word) throws IllegalArgumentException {
        validateNullWord(word);
        validateEmptyWord(word);
        this.isLastWordInSentence = word.trim().endsWith(".");
        this.word = word.trim().split("\\.")[0].trim();
    }

    private void validateNullWord(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Word cannot be null");
        }
    }

    private void validateEmptyWord(String word) {
        if (word.trim().isEmpty()) {
            throw new IllegalArgumentException("Word cannot be empty");
        }
    }

    public boolean isLastWordInSentence() {
        return isLastWordInSentence;
    }

    @Override
    public String toString() {
        return word;
    }


}
