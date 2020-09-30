package com.silenceb.nordea.exporter;

import com.silenceb.nordea.model.Sentence;

public interface DocumentExporter {

    void export(Sentence sentence);

    boolean handles(ExportType... exportType);

    void writeFooter();

    void writeHeader();
}
