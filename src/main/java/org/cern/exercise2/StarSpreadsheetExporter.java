package org.cern.exercise2;

/**
 * Exports a spreadsheet using '*' as the filler string.
 */
public class StarSpreadsheetExporter {
    final SpreadsheetImpl sheet;

    public StarSpreadsheetExporter(SpreadsheetImpl sheet) {
        this.sheet = sheet;
    }

    public String export() {
        return SpreadsheetExporter.export(sheet, "*");
    }
}
