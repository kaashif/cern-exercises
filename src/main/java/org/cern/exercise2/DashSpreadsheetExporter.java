package org.cern.exercise2;

/**
 * Exports a spreadsheet using '-' as the filler string.
 */
public class DashSpreadsheetExporter {
    final SpreadsheetImpl sheet;

    public DashSpreadsheetExporter(SpreadsheetImpl sheet) {
        this.sheet = sheet;
    }

    public String export() {
        return SpreadsheetExporter.export(sheet, "-");
    }
}
