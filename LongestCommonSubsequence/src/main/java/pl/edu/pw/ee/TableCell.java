package pl.edu.pw.ee;

public class TableCell{

    public int getValue() {
        return value;
    }

    private final int value;

    public ArrowType getArrowType() {
        return arrowType;
    }

    private ArrowType arrowType;

    public TableCell(int value){
        this.value = value;
    }

    public TableCell(int value, ArrowType arrowType){
        this.value = value;
        this.arrowType = arrowType;
    }

}