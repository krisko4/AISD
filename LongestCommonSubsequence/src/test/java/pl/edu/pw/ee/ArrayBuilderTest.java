package pl.edu.pw.ee;

import org.junit.Test;

public class ArrayBuilderTest {

    public ArrayBuilder arrayBuilder;

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNullTopStr() {
        //given
        String topStr = null;
        String leftStr = "test";
        TableCell[][] tableCellArray = new TableCell[10][10];

        //when
        arrayBuilder = new ArrayBuilder(topStr, leftStr, tableCellArray);

        //then
        assert false;

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNullLeftStr() {
        //given
        String topStr = "test";
        String leftStr = null;
        TableCell[][] tableCellArray = new TableCell[10][10];

        //when
        arrayBuilder = new ArrayBuilder(topStr, leftStr, tableCellArray);

        //then
        assert false;

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnEmptyTopStr() {
        //given
        String topStr = "";
        String leftStr = "test";
        TableCell[][] tableCellArray = new TableCell[10][10];

        //when
        arrayBuilder = new ArrayBuilder(topStr, leftStr, tableCellArray);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnEmptyLeftStr() {
        //given
        String topStr = "test";
        String leftStr = "";
        TableCell[][] tableCellArray = new TableCell[10][10];

        //when
        arrayBuilder = new ArrayBuilder(topStr, leftStr, tableCellArray);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNullTableCellArray() {
        //given
        String topStr = "test";
        String leftStr = "test";
        TableCell[][] tableCellArray = null;

        //when
        arrayBuilder = new ArrayBuilder(topStr, leftStr, tableCellArray);

        //then
        assert false;

    }
}
