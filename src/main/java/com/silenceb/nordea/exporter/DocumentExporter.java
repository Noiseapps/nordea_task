package com.silenceb.nordea.exporter;

import com.silenceb.nordea.model.Document;

public interface DocumentExporter {

    String export(Document document);

    ExportType type();
}
