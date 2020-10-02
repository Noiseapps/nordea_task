package com.silenceb.nordea.scanner;

import com.silenceb.nordea.exporter.ExportType;

public interface InputScanner {

    String readFileName();

    ExportType[] readExportTypes();
}
