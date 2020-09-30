package com.silenceb.nordea.analyzer;

import com.silenceb.nordea.exporter.Exporter;
import com.silenceb.nordea.model.Sentence;
import com.silenceb.nordea.model.Word;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class LineAnalyzer implements Analyzer {

    private Sentence currentSentence = new Sentence();

    private final Exporter exporter;

    public LineAnalyzer(Exporter exporter) {
        this.exporter = exporter;
    }

    @Override
    public void analyze(List<String> line) {
        line.stream()
                .map(Word::fromString)
                .filter(Objects::nonNull)
                .forEach(this::processWord);
    }

    public void newSentence() {
        exporter.addSentence(currentSentence);
        currentSentence = new Sentence();
    }

    void processWord(Word word) {
        currentSentence = currentSentence.sentenceByAddingWord(word);
        if (word.isLastWordInSentence()) {
            newSentence();
        }
    }
}
