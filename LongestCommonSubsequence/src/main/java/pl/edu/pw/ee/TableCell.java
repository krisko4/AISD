package pl.edu.pw.ee;

public class TableCell {

    private boolean isLCSPath = false;
    private final int value;

    public int getValue() {
        return value;
    }

    public boolean isLCSPath(){
        return isLCSPath;
    }

    public void setLCSPath(boolean lcsPath) {
        this.isLCSPath = lcsPath;
    }

    public ArrowType getArrowType() {
        return arrowType;
    }

    private ArrowType arrowType;

    public TableCell(int value) {
        this.value = value;
    }

    public TableCell(int value, ArrowType arrowType) {
        this.value = value;
        this.arrowType = arrowType;
    }

}