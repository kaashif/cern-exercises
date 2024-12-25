package org.cern.exercise2;

import java.util.Arrays;

public class SpreadsheetImpl {
    private String[][] data;
    private int numRows;
    private int numColumns;

    /**
     * @return Whether s is an integer cell i.e. only contains leading/trailing spaces and digits.
     */
    private static boolean isIntegerCell(String s) {
        return s.trim().chars().allMatch(c -> Character.isDigit(c));
    }

    public SpreadsheetImpl(int numRows, int numColumns) {
        this.numRows = numRows;
        this.numColumns = numColumns;

        data = new String[numRows][numColumns];
        Arrays.stream(data).forEach(arr -> Arrays.fill(arr, ""));
    }

    // Note: In a real project I would use Lombok to generate getters and setters, but I can't use 3rd party libraries.
    public int getNumRows() {
        return numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }

    /**
     * @return The content of the spreadsheet at (row, column), defaulting to the empty string if nothing has been
     * written yet.
     */
    String get(int row, int column) {
        return data[row][column];
    }

    /**
     * Writes content to the spreadsheet at (row, column), stripping spaces iff the content is an integer with
     * leading/trailing spaces.
     */
    void put(int row, int column, String content) {
        data[row][column] = isIntegerCell(content) ? content.trim() : content;
    }

    /**
     * @return The value type of the cell at (row, column). This could be FORMULA iff the cell starts with =, INTEGER if
     * the cell contains only digits and trailing/leading spaces, and STRING for everything else.
     */
    ValueType getValueType(int row, int column) {
        String content = get(row, column);

        if (content.startsWith("=")) {
            return ValueType.FORMULA;
        }

        if (isIntegerCell(content)) {
            return ValueType.INTEGER;
        }

        return ValueType.STRING;
    }
}