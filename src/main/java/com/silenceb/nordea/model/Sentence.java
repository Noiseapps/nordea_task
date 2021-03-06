package com.silenceb.nordea.model;

import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode
public class Sentence {

    private final List<Word> words;

    public Sentence() {
        words = new ArrayList<>();
    }

    private Sentence(Sentence old, Word word) {
        words = new ArrayList<>(old.words);
        words.add(word);
    }

    public List<String> sortedWords() {
        List<String> words = wordsAsStrings();
        words.sort(Comparator.comparing(String::toLowerCase));
        return words;
    }

    public Sentence sentenceByAddingWord(Word word) {
        return new Sentence(this, word);
    }

    public List<String> getWords() {
        return wordsAsStrings();
    }

    public boolean isEmpty() {
        return words.isEmpty();
    }

    @Override
    public String toString() {
        return String.join(" ", wordsAsStrings()) + ".";
    }

    private List<String> wordsAsStrings() {
        return words.stream().map(Word::toString).collect(Collectors.toList());
    }
}
