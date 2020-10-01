package com.silenceb.nordea.exporter;

import com.silenceb.nordea.model.Sentence;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.silenceb.nordea.exporter.ExportType.CSV;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.io.FileUtils.writeStringToFile;

@Component
class CSVExporter implements DocumentExporter {
    Logger logger = LoggerFactory.getLogger(getClass());

    int longestSequence = 0;
    int sequenceCount = 1;

    private final File tempFile = new File("temp.csv");
    private final File outputFile = new File("output.csv");

    @Override
    public void export(Sentence sentence) {
        List<String> sortedWords = sentence.sortedWords();
        logger.info("Sorted sentence: {}", sortedWords);
        updateLongestSentenceSize(sortedWords);
        try {
            writeStringToFile(tempFile, line(sortedWords), UTF_8, true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String line(List<String> sortedWords) {
        StringBuilder sb = new StringBuilder("\nSentence " + sequenceCount++ + ",");
        for (String word : sortedWords) {
            sb.append(word).append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }

    private void updateLongestSentenceSize(List<String> sortedWords) {
        if (sortedWords.size() > longestSequence) {
            longestSequence = sortedWords.size();
        }
    }

    @Override
    public boolean handles(ExportType... exportType) {
        return Arrays.asList(exportType).contains(CSV);
    }

    @Override
    public void finish() {
        try (LineIterator iterator = FileUtils.lineIterator(tempFile, UTF_8.name())) {
            writeHeader(outputFile);
            while (iterator.hasNext()) {
                String line = iterator.nextLine();
                if(!line.isEmpty()) {
                    writeStringToFile(outputFile, "\n", UTF_8, true);
                    writeStringToFile(outputFile, line, UTF_8, true);
                }
            }
        } catch (IOException ex) {
            logger.error("Error processing file", ex);
        } finally {
            FileUtils.deleteQuietly(tempFile);
        }
    }

    private void writeHeader(File outputFile) {
        StringBuilder sb = new StringBuilder(",");
        for (int i = 0; i < longestSequence; i++) {
            sb.append("Word ").append(i + 1).append(", ");
        }
        try {
            writeStringToFile(outputFile, sb.toString(), UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void start() {
        FileUtils.deleteQuietly(tempFile);
        FileUtils.deleteQuietly(outputFile);
        // Do nothing. We need to find the longest sequence first to write header.
    }
}
