package org.cern.exercise2;

public class Office {
    /**
     * @param numRows Number of rows in the new spreadsheet.
     * @param numColumns Number of columns in the new spreadsheet.
     * @return A freshly created spreadsheet of size numRows x numColumns.
     */
    public static SpreadsheetImpl newSpreadsheet(int numRows, int numColumns) {
        return new SpreadsheetImpl(numRows, numColumns);
    }
}
