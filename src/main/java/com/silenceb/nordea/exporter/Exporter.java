package com.silenceb.nordea.exporter;

import com.silenceb.nordea.model.Document;

public interface Exporter {

    void export(Document document, ExportType... exportTypes);
}
