package com.silenceb.nordea.exporter;

import com.silenceb.nordea.model.Sentence;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.silenceb.nordea.exporter.ExportType.XML;

@Component
class XMLExporter implements DocumentExporter {
    @Override
    public void export(Sentence document) {
//        return "XML document representation";
    }

    @Override
    public boolean handles(ExportType... exportType) {
        return Arrays.asList(exportType).contains(XML);
    }

    @Override
    public void writeFooter() {

    }

    @Override
    public void writeHeader() {

    }
}
