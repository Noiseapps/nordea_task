package com.silenceb.nordea.util;

import java.util.HashSet;
import java.util.Set;

public class Abbreviations {

    private static final Set<String> abbreviations = new HashSet<>();

    static {
        abbreviations.add("mr.");
        abbreviations.add("dr.");
        abbreviations.add("etc.");
        // todo add more when needed
    }

    public static boolean isCommonAbbreviation(String word) {
        return abbreviations.contains(word.toLowerCase());
    }
}
