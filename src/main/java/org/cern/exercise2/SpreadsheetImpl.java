package org.cern.exercise2;

public interface SpreadsheetImpl {
    String get(int row, int column);
    void put(int row, int column, String content);
    ValueType getValueType(int row, int column);
}