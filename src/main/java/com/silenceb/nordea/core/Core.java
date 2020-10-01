package com.silenceb.nordea.core;

import com.silenceb.nordea.analyzer.Analyzer;
import com.silenceb.nordea.exporter.ExportType;
import com.silenceb.nordea.exporter.Exporter;
import com.silenceb.nordea.parser.Parser;
import com.silenceb.nordea.tokenizer.Tokenizer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Core implements CommandLineRunner {

    private final Parser parser;
    private final Tokenizer tokenizer;
    private final Analyzer analyzer;
    private final Exporter exporter;

    public Core(Parser parser, Tokenizer tokenizer, Analyzer analyzer, Exporter exporter) {
        this.parser = parser;
        this.tokenizer = tokenizer;
        this.analyzer = analyzer;
        this.exporter = exporter;
    }

    @Override
    public void run(String... args) throws Exception {
        exporter.setup(ExportType.CSV);
        String fileName = "small.in";
//        String fileName = "large.in";
        parser.parse(fileName, line -> {
            List<String> tokenizedLine = tokenizer.tokenizeLine(line);
            analyzer.analyze(tokenizedLine);
        });
    }
}
