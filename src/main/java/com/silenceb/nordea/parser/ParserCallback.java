package com.silenceb.nordea.parser;

@FunctionalInterface
public interface ParserCallback {

    void processLine(String line);
}
