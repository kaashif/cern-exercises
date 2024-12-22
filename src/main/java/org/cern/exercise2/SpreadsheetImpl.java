package org.cern.exercise2;

public class SpreadsheetImpl {
    private String[][] data;

    public SpreadsheetImpl(int numRows, int numColumns) {
        data = new String[numRows][numColumns];
    }

    String get(int row, int column) {
        var content = data[row][column];
        return content == null ? "" : content;
    }

    void put(int row, int column, String content) {
        data[row][column] = content;
    }

    ValueType getValueType(int row, int column) {
        return null;
    }
}