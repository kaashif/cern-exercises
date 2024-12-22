package org.cern.exercise2;

public class SpreadsheetExporter {
    public static String export(SpreadsheetImpl sheet, String filler) {
        var output = new StringBuilder();

        output.append(sheet.getNumRows());
        output.append(",");
        output.append(sheet.getNumColumns());
        output.append("#");

        for (int row = 0; row < sheet.getNumRows(); row++) {
            for (int column = 0; column < sheet.getNumColumns(); column++) {
                output.append(sheet.get(row, column));
                output.append(filler);
            }
        }

        return output.toString();
    }
}
