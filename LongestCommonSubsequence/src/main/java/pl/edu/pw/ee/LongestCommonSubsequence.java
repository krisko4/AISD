package pl.edu.pw.ee;

public class LongestCommonSubsequence {

    private final String topStr;
    private final String leftStr;
    private TableCell[][] tableCellArray;

    public TableCell[][] getTableCellArray() {
        return tableCellArray;
    }

    public LongestCommonSubsequence(String topStr, String leftStr) {
        if (topStr == null || leftStr == null) {
            throw new IllegalArgumentException("Provided constructor params cannot be null");
        }
        this.topStr = topStr;
        this.leftStr = leftStr;
        generateArray();
    }

    private void generateArray() {
        tableCellArray = new TableCell[leftStr.length() + 1][topStr.length() + 1];
        for (int i = 0; i < leftStr.length() + 1; i++) {
            for (int j = 0; j < topStr.length() + 1; j++) {
                if (i == 0 || j == 0) {
                    tableCellArray[i][j] = new TableCell(0);
                    continue;
                }
                if (leftStr.charAt(i - 1) == topStr.charAt(j - 1)) {
                    int value = tableCellArray[i - 1][j - 1].getValue();
                    tableCellArray[i][j] = new TableCell(value + 1, ArrowType.DIAGONAL);
                    continue;
                }
                int topValue = tableCellArray[i - 1][j].getValue();
                int leftValue = tableCellArray[i][j - 1].getValue();
                tableCellArray[i][j] = new TableCell(Math.max(topValue, leftValue),
                        topValue >= leftValue ? ArrowType.TOP : ArrowType.LEFT);
            }
        }
    }

    private void setLCSPath() {
        int i = leftStr.length();
        int j = topStr.length();
        while (i > 0 && j > 0) {
            tableCellArray[i][j].setLCSPath(true);
            switch (tableCellArray[i][j].getArrowType()) {
                case DIAGONAL:
                    i--;
                    j--;
                    break;
                case TOP:
                    i--;
                    break;
                case LEFT:
                    j--;
                    break;
            }

        }
    }

    public String findLCS() {
        StringBuilder lcs = new StringBuilder();
        int i = leftStr.length();
        int j = topStr.length();
        while (i > 0 && j > 0) {
            TableCell tableCell = tableCellArray[i][j];
            switch (tableCell.getArrowType()) {
                case DIAGONAL:
                    lcs.append(leftStr.charAt(i - 1));
                    i--;
                    j--;
                    break;
                case LEFT:
                    j--;
                    break;
                case TOP:
                    i--;
                    break;
            }
        }
        return lcs.reverse().toString();
    }

    public void display() {
        setLCSPath();
        ArrayBuilder arrayBuilder = new ArrayBuilder(topStr, leftStr, tableCellArray);
        arrayBuilder.buildArray();
    }

}
