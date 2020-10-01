package com.silenceb.nordea.exporter;

import com.silenceb.nordea.model.Sentence;

public interface DocumentExporter {

    boolean handles(ExportType... exportType);

    void finish();

    void start();

    void export(Sentence sentence);
}
