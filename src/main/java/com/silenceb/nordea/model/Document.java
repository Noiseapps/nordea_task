package com.silenceb.nordea.model;

import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
public class Document {

    private final List<Sentence> sentences;
    private Sentence currentSentence;

    public Document() {
        this.sentences = new ArrayList<>();
        this.currentSentence = new Sentence();
    }

    public void newSentence() {
        sentences.add(currentSentence);
        currentSentence = new Sentence();
    }

    public void addWord(Word word) {
        currentSentence = currentSentence.sentenceByAddingWord(word);
        if (word.isLastWordInSentence()) {
            newSentence();
        }
    }

    public List<Sentence> getSentences() {
        return new ArrayList<>(sentences);
    }

    @Override
    public String toString() {
        return "Document{" +
                "sentences=" + sentences +
                '}';
    }
}
