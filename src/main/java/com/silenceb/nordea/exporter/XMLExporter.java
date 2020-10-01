package com.silenceb.nordea.exporter;

import com.silenceb.nordea.model.Sentence;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import static com.silenceb.nordea.exporter.ExportType.XML;

@Component
class XMLExporter implements DocumentExporter {

    public static final String FILE_NAME = "output.xml";
    private Document document;
    private Element root;

    @Value("${xml.header}")
    String xmlHeader;
    @Value("${xml.root}")
    String xmlRootElement;
    @Value("${xml.sentence}")
    String xmlSentence;
    @Value("${xml.word}")
    String xmlWord;

    @Override
    public void export(Sentence sentence) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(opening(xmlSentence));
            for (String sortedWord : sentence.sortedWords()) {
                writer.write(String.format("%s%s%s", opening(xmlWord), sortedWord, closing(xmlWord)));
            }
            writer.write(closing(xmlSentence));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String opening(String tag) {
        return String.format("<%s>", tag);
    }

    private String closing(String tag) {
        return String.format("</%s>", tag);
    }

    @Override
    public boolean handles(ExportType... exportType) {
        return Arrays.asList(exportType).contains(XML);
    }

    @Override
    public void finish() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(closing(xmlRootElement));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        FileUtils.deleteQuietly(new File(FILE_NAME));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(xmlHeader);
            writer.newLine();
            writer.write(opening(xmlRootElement));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
