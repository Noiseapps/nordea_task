package com.silenceb.nordea.exporter;

public enum ExportType {

    CSV, XML;

    public static ExportType fromString(String name) {
        for (ExportType value : values()) {
            if (value.name().equalsIgnoreCase(name.trim())) {
                return value;
            }
        }
        return null;
    }

}
