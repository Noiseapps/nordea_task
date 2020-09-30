package com.silenceb.nordea.core;

import com.silenceb.nordea.analyzer.Analyzer;
import com.silenceb.nordea.model.Document;
import com.silenceb.nordea.parser.Parser;
import com.silenceb.nordea.tokenizer.Tokenizer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class Core implements CommandLineRunner {

    private final Parser parser;
    private final Tokenizer tokenizer;
    private final Analyzer analyzer;

    public Core(Parser parser, Tokenizer tokenizer, Analyzer analyzer) {
        this.parser = parser;
        this.tokenizer = tokenizer;
        this.analyzer = analyzer;
    }

    @Override
    public void run(String... args) throws Exception {
//        String fileName = "small.in";
        String fileName = "large.in";
        Document document = new Document();
        parser.parse(fileName, line -> {
            List<String> tokenizedLine = tokenizer.tokenizeLine(line);
            analyzer.analyze(tokenizedLine, document);
        });
        System.out.println(document.toString());
    }
}
