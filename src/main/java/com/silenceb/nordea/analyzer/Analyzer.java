package com.silenceb.nordea.analyzer;

import com.silenceb.nordea.model.Document;

import java.util.List;

public interface Analyzer {


    void analyze(List<String> line, Document document);
}
