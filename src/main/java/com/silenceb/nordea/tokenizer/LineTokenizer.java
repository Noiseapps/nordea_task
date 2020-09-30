package com.silenceb.nordea.tokenizer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LineTokenizer implements Tokenizer {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private int counter = 0;
    final double lineCount = 117249.0;

    @Override
    public List<String> tokenizeLine(String line) {
        if (line == null || line.trim().isEmpty()) {
            ++counter;
            return new ArrayList<>();
        }
        logger.info("Processing line {} ({}%): {}", ++counter, Math.floor((counter/lineCount) * 100), line);
        String[] wordsInLine = line.split("[ ,]");
        return Arrays.stream(wordsInLine)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }
}
