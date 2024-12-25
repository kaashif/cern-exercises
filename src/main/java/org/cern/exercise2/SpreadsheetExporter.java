package org.cern.exercise2;

public class SpreadsheetExporter {
    /**
     * @param sheet Spreadsheet to export
     * @param filler String to append after every value, including blank values.
     * @return String containing the exported spreadsheet.
     */
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
