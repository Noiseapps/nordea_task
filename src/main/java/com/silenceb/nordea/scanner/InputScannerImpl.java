package com.silenceb.nordea.scanner;

import com.silenceb.nordea.exporter.ExportType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class InputScannerImpl implements InputScanner {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String readFileName() {

        Scanner scanner = new Scanner(System.in);
        String output = null;
        while (output == null) {
            try {
                System.out.println("Enter file name:");
                output = scanner.nextLine();
                if (!new File(output).exists()) {
                    System.out.println("Could not open file.");
                    output = null;
                }
            } catch (Exception ex) {
                System.out.println("Invalid input");
                output = null;
            }
        }
        return output;
    }

    @Override
    public ExportType[] readExportTypes() {
        Scanner scanner = new Scanner(System.in);
        List<ExportType> exportTypes = new ArrayList<>(2);
        while (exportTypes.isEmpty()) {
            try {
                System.out.println("Enter comma separated export formats (CSV, XML)");
                String input = scanner.nextLine();
                String[] components = input.split(",");
                if (components.length == 0) {
                    System.out.println("Enter at least one output format.");
                    continue;
                }
                for (String component : components) {
                    ExportType exportType = ExportType.fromString(component);
                    if (exportType == null) {
                        exportTypes.clear();
                        System.out.printf("Cannot export to %s\n", component);
                        continue;
                    }
                    exportTypes.add(exportType);
                }
            } catch (Exception ex) {
                System.out.println("Invalid input");
            }
        }
        ExportType[] types = new ExportType[exportTypes.size()];
        exportTypes.toArray(types);
        return types;
    }
}
