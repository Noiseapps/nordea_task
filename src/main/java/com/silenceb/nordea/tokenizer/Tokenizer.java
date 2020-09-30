package com.silenceb.nordea.tokenizer;

import java.util.List;

public interface Tokenizer {

    List<String> tokenizeLine(String line);
}
