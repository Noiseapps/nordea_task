package com.silenceb.nordea.exporter;

import com.silenceb.nordea.model.Document;
import org.springframework.stereotype.Component;

import static com.silenceb.nordea.exporter.ExportType.CSV;

@Component
class CSVExporter implements DocumentExporter {
    @Override
    public String export(Document document) {
        return "CSV document representation";
    }

    @Override
    public ExportType type() {
        return CSV;
    }
}
