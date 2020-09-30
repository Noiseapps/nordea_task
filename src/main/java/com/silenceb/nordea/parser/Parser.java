package com.silenceb.nordea.parser;

public interface Parser {

    void parse(String fileName, ParserCallback callback);
}
