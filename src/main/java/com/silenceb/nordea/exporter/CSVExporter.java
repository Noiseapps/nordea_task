package com.silenceb.nordea.exporter;

import com.silenceb.nordea.model.Sentence;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

import static com.silenceb.nordea.exporter.ExportType.CSV;

@Component
class CSVExporter implements DocumentExporter {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void export(Sentence sentence) {
        List<String> sortedWords = sentence.sortedWords();
        logger.info("Sorted sentence: {}", sortedWords);
        try (BufferedWriter output = Files.newBufferedWriter(Paths.get("output.csv"), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
             CSVPrinter csvPrinter = new CSVPrinter(output, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord(sortedWords);
        } catch (IOException ex) {

        }
    }

    @Override
    public boolean handles(ExportType... exportType) {
        return Arrays.asList(exportType).contains(CSV);
    }

    @Override
    public void writeFooter() {
        // noop for csv
    }

    @Override
    public void writeHeader() {
        try (FileWriter output = new FileWriter("output.csv");
             CSVPrinter csvPrinter = new CSVPrinter(output, CSVFormat.DEFAULT.withHeader("", "Words"))) {
        } catch (IOException ex) {

        }
    }
}
