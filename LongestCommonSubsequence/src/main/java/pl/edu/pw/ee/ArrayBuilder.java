package pl.edu.pw.ee;

public class ArrayBuilder extends LCS {

    private String emptyCell = "|       ";
    private String border = "+-------";
    private String topArrowCell = "|   ^   ";
    private String diagonalArrowCell = "| \\     ";
    private int padding;

    private final String topStr;
    private final String leftStr;
    private final TableCell[][] tableCellArray;

    public ArrayBuilder(String topStr, String leftStr, TableCell[][] tableCellArray) {
        super(topStr, leftStr);
        if (tableCellArray == null) {
            throw new IllegalArgumentException("TableCellArray cannot be null");
        }
        this.topStr = topStr;
        this.leftStr = leftStr;
        this.tableCellArray = tableCellArray;
        setAlignment();
    }

    private void setAlignment() {
        padding = String.valueOf(findHighestValue()).length() - 1;
        if (padding > 0) {
            int newEmptyCellLength = emptyCell.length() + padding;
            int newDiagonalArrowCell = diagonalArrowCell.length() + padding;
            int newTopArrowCellLength = topArrowCell.length() + padding;
            for (int i = 0; i < padding; i++) {
                border += "-";
            }
            emptyCell = String.format("%-" + newEmptyCellLength + "s", emptyCell);
            topArrowCell = String.format("%-" + newTopArrowCellLength + "s", topArrowCell);
            diagonalArrowCell = String.format("%-" + newDiagonalArrowCell + "s", diagonalArrowCell);
        }
    }

    private int findHighestValue() {
        TableCell lastCell = tableCellArray[leftStr.length() - 1][topStr.length() - 1];
        return lastCell.getValue();
    }

    private boolean isSpecialCharacter(char character) {
        return character == '\n' || character == '\t';
    }

    private String buildCharacterCell(char character) {
        String charString = "" + character;
        String space = isSpecialCharacter(character) ? "" : " ";
        if (character == '\n') {
            charString = "\\n";
        }
        if (character == '\t') {
            charString = "\\t";
        }
        if (character == '\r') {
            charString = "\\r";
        }
        String characterCell = "|   " + charString + "  " + space;
        int newLength = characterCell.length() + padding;
        return String.format("%-" + newLength + "s", characterCell);
    }

    private String buildValueCell(int value) {
        String valueCell = "|   " + value + "   ";
        if (String.valueOf(value).length() <= padding) {
            int newLength = valueCell.length() + padding;
            return String.format("%-" + newLength + "s", valueCell);
        }
        return valueCell;

    }

    private String buildArrowValueCell(int value) {
        String valueCell = "|<  " + value + "   ";
        int newLength = valueCell.length() + padding;
        return String.format("%-" + newLength + "s", valueCell);
    }

    private String buildEmptyCellLine(int length) {
        return emptyCell.repeat(Math.max(0, length)) + "|\n";
    }

    private String buildZeroValueLine(int size) {
        return buildCharacterCell('0').repeat(Math.max(0, size)) +
                "|\n";
    }

    private String buildTopStrLine() {
        StringBuilder topStrLine = new StringBuilder();
        for (int i = 0; i < topStr.length(); i++) {
            topStrLine.append(buildCharacterCell(topStr.charAt(i)));
        }
        topStrLine.append("|\n");
        return topStrLine.toString();
    }

    private String buildEmptyCellLine() {
        return emptyCell.repeat(Math.max(0, topStr.length() + 2)) + "|\n";
    }

    private String buildBorderLine() {
        return border.repeat(Math.max(0, topStr.length() + 2)) + "+\n";
    }

    private String buildTopStr() {
        return buildBorderLine() +
                buildEmptyCellLine() +
                emptyCell.repeat(2) +
                buildTopStrLine() +
                buildEmptyCellLine() +
                buildBorderLine();
    }

    private String buildZeroValuesRow() {
        int length = topStr.length() + 2;
        StringBuilder zeroValuesRowBuilder = new StringBuilder();
        String emptyCellLine = emptyCell.repeat(Math.max(0, length)) + "|\n";
        String borderLine = border.repeat(Math.max(0, length)) + "+\n";
        zeroValuesRowBuilder.append(buildEmptyCellLine(length));
        zeroValuesRowBuilder.append(emptyCell);
        zeroValuesRowBuilder.append(buildZeroValueLine(length - 1));
        zeroValuesRowBuilder.append(emptyCellLine);
        zeroValuesRowBuilder.append(borderLine);
        return zeroValuesRowBuilder.toString();
    }

    private String buildValueRow(int i) {
        StringBuilder valueRowBuilder = new StringBuilder();
        valueRowBuilder.append(emptyCell.repeat(2));
        for (int j = 1; j < topStr.length() + 1; j++) {
            TableCell cell = tableCellArray[i][j];
            if (cell.isLCSPath()) {
                if (cell.getArrowType() == ArrowType.TOP) {
                    valueRowBuilder.append(topArrowCell);
                    continue;
                }
                if (cell.getArrowType() == ArrowType.DIAGONAL) {
                    valueRowBuilder.append(diagonalArrowCell);
                    continue;
                }
            }
            valueRowBuilder.append(emptyCell);
        }
        valueRowBuilder.append("|\n");
        valueRowBuilder.append(buildCharacterCell(leftStr.charAt(i - 1)));
        valueRowBuilder.append(buildCharacterCell('0'));
        for (int j = 1; j < topStr.length() + 1; j++) {
            TableCell cell = tableCellArray[i][j];
            if (cell.isLCSPath() && cell.getArrowType() == ArrowType.LEFT) {
                valueRowBuilder.append(buildArrowValueCell(cell.getValue()));
                continue;
            }
            valueRowBuilder.append(buildValueCell(cell.getValue()));
        }
        valueRowBuilder.append("|\n");
        valueRowBuilder.append(emptyCell.repeat(Math.max(0, topStr.length() + 2))).append("|\n");
        valueRowBuilder.append(buildBorderLine());
        return valueRowBuilder.toString();
    }

    public void buildArray() {
        StringBuilder arrayBuilder = new StringBuilder(buildTopStr() +
                buildZeroValuesRow());
        for (int i = 1; i < leftStr.length() + 1; i++) {
            arrayBuilder.append(buildValueRow(i));
        }
        System.out.println(arrayBuilder);
    }

}
