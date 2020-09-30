package com.silenceb.nordea.exporter;

import com.silenceb.nordea.model.Sentence;

public interface Exporter {

    void setup(ExportType... exportTypes);

    void addSentence(Sentence sentence);

    void notifyFinished();
}
