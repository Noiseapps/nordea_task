package com.silenceb.nordea.model;

import com.silenceb.nordea.util.Abbreviations;
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
        this.isLastWordInSentence = checkLastWordInSentence(word);
        this.word = cleanupWord(word);
    }

    private String cleanupWord(String word) {
        String trimmed = word.trim();
        trimmed = trimmed.replaceAll("[?!;:()]", "");
        if (!Abbreviations.isCommonAbbreviation(word)) {
            trimmed = trimmed.replaceAll("\\.", "");
        }
        return trimmed.trim();
    }

    private boolean checkLastWordInSentence(String word) {
        String trimmed = word.trim();
        boolean properEnding = trimmed.matches("^.*[.?!]$");
        boolean isCommonAbbreviation = Abbreviations.isCommonAbbreviation(word);
        return properEnding && !isCommonAbbreviation;
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
