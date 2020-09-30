package com.silenceb.nordea.tokenizer;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LineTokenizer implements Tokenizer {
    @Override
    public List<String> tokenizeLine(String line) {
        if (line == null || line.trim().isEmpty()) {
            return new ArrayList<>();
        }
        String[] wordsInLine = line.split("[\\s,-]");
        return Arrays.stream(wordsInLine)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }
}
