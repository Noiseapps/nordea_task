package com.silenceb.nordea.analyzer;

import com.silenceb.nordea.model.Document;
import com.silenceb.nordea.model.Word;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class LineAnalyzer implements Analyzer {


    @Override
    public void analyze(List<String> line, Document document) {
        line.stream()
                .map(Word::fromString)
                .filter(Objects::nonNull)
                .forEach(document::addWord);
    }
}
