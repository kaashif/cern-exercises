package org.cern.exercise2;

public class SpreadsheetImpl {
    private String[][] data;

    private static boolean isIntegerCell(String s) {
        for (int i = 0; i < s.length(); i++) {
            var c = s.charAt(i);
            if (!Character.isDigit(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }

    public SpreadsheetImpl(int numRows, int numColumns) {
        data = new String[numRows][numColumns];
    }

    String get(int row, int column) {
        var content = data[row][column];
        return content == null ? "" : content;
    }

    void put(int row, int column, String content) {
        if (isIntegerCell(content)) {
            data[row][column] = content.trim();
        } else {
            data[row][column] = content;
        }
    }

    ValueType getValueType(int row, int column) {
        var content = get(row, column);

        if (content.startsWith("=")) {
            return ValueType.FORMULA;
        }

        for (int i = 0; i < content.length(); i++) {
            if (!Character.isDigit(content.charAt(i))) {
                return ValueType.STRING;
            }
        }

        return ValueType.INTEGER;
    }
}