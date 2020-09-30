package com.silenceb.nordea.exporter;

import com.silenceb.nordea.model.Document;
import org.springframework.stereotype.Component;

import static com.silenceb.nordea.exporter.ExportType.XML;

@Component
class XMLExporter implements DocumentExporter {
    @Override
    public String export(Document document) {
        return "XML document representation";
    }

    @Override
    public ExportType type() {
        return XML;
    }
}
