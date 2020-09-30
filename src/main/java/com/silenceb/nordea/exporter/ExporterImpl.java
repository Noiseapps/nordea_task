package com.silenceb.nordea.exporter;

import com.silenceb.nordea.model.Sentence;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExporterImpl implements Exporter {

    private interface DocumentExporterAction {
        void handle(DocumentExporter documentExporter);
    }

    private final List<DocumentExporter> exporters;
    private ExportType[] exportTypes = new ExportType[]{};

    public ExporterImpl(List<DocumentExporter> exporters) {
        this.exporters = exporters;
    }


    @Override
    public void setup(ExportType... exportTypes) {
        this.exportTypes = exportTypes;
        processOnAllExporters(DocumentExporter::writeHeader);
        exporters.forEach(documentExporter -> {
            if (documentExporter.handles(exportTypes)) {
                documentExporter.writeHeader();
            }
        });
    }

    private void exportSentence(Sentence sentence) {
        processOnAllExporters(documentExporter -> {
            documentExporter.export(sentence);
        });
    }

    @Override
    public void addSentence(Sentence sentence) {
        exportSentence(sentence);
    }

    @Override
    public void notifyFinished() {
        processOnAllExporters(DocumentExporter::writeFooter);
    }

    private void processOnAllExporters(DocumentExporterAction action) {
        exporters.forEach(documentExporter -> {
            if (documentExporter.handles(exportTypes)) {
                action.handle(documentExporter);
            }
        });
    }
}
