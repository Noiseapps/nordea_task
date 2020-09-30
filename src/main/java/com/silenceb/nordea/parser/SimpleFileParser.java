package com.silenceb.nordea.parser;


import com.silenceb.nordea.exporter.Exporter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class SimpleFileParser implements Parser {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Exporter exporter;

    public SimpleFileParser(Exporter exporter) {
        this.exporter = exporter;
    }

    @Override
    public void parse(String fileName, ParserCallback callback) {
        File inputFile = new File(fileName);
        try (LineIterator iterator = FileUtils.lineIterator(inputFile, StandardCharsets.UTF_8.name())) {
            while (iterator.hasNext()) {
                String line = iterator.nextLine();
                callback.processLine(line);
            }
        } catch (IOException ex) {
            logger.error("Error processing file", ex);
        } finally {
            exporter.notifyFinished();
        }
    }
}
