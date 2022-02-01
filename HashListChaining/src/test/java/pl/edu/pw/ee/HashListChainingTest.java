package pl.edu.pw.ee;

import org.junit.Test;


import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * HashTable implementation tests
 */
public class HashListChainingTest {

    private HashListChaining<String> hashTable;


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNullElementDelete() {
        //given
        hashTable = new HashListChaining<>(1);

        //when
        hashTable.delete(null);

        //then
        assert false;
    }

    @Test
    public void shouldAddNewElementCorrectly() {

        //given
        hashTable = new HashListChaining<>(1);
        String item = "ADRIAN";

        //when
        hashTable.add(item);
        String foundValue = hashTable.get(item);

        //then
        assertEquals(foundValue, item);

    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNullItemAddition() {
        //given
        hashTable = new HashListChaining<>(1);

        //when
        hashTable.add(null);

        //then
        assert false;

    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnEmptyHashTable() {
        //given
        int size = 0;

        //when
        hashTable = new HashListChaining<>(size);

        //then
        assert false;


    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNegativeHashTableSize() {
        //given
        int size = -1;

        //when
        hashTable = new HashListChaining<>(size);


    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNullItemGet() {
        //given
        hashTable = new HashListChaining<>(1);
        String item = null;

        //when
        hashTable.get(item);

        //then
        assert false;

    }


    @Test(expected = NoSuchElementException.class)
    public void shouldThrowExceptionOnNotFoundItemDelete() {
        //given
        hashTable = new HashListChaining<>(1);
        String item = "ADRIAN";

        //when
        hashTable.delete(item);

        //then
        assert false;
    }

    @Test
    public void shouldReturnNullIfValueNotFound() {
        //given
        hashTable = new HashListChaining<>(1);
        String item = "Hello";

        //when
        String foundValue = hashTable.get(item);

        //then
        assertNull(foundValue);

    }

    /**
     * When adding new item to HashTable,
     * if there are already some items occupying calculated index,
     * new item should be added to the front of a list.
     */
    @Test
    public void shouldAddSameIndexElementsToListCorrectly() {

        //given
        hashTable = new HashListChaining<>(1);
        String item1 = "ADRIAN";
        String item2 = "ADAM";
        String item3 = "ALAN";

        //when
        hashTable.add(item1);
        //then
        assertEquals(hashTable.getValue(0), item1);
        //when
        hashTable.add(item2);
        //then
        assertEquals(hashTable.getValue(0), item2);
        //when
        hashTable.add(item3);
        //then
        assertEquals(hashTable.getValue(0), item3);

    }

    /**
     * If HashTable size is big enough, all the items should have different indexes <=> the list on each index should have only one element
     */
    @Test
    public void shouldPlaceItemsToDifferentIndexesOnProperSize() {
        //given
        hashTable = new HashListChaining<>(1000);
        String item1 = "ARAN";
        String item2 = "ADAM";
        String item3 = "ALAN";

        //when
        hashTable.add(item1);
        hashTable.add(item2);
        hashTable.add(item3);

        //then
        assertTrue(hashTable.isNextElementNull(item1));
        assertTrue(hashTable.isNextElementNull(item2));
        assertTrue(hashTable.isNextElementNull(item3));

    }

    /**
     * When adding an item with the same value, a previous one should be replaced.
     * As a result, number of elements stays the same as before.
     */
    @Test
    public void shouldReplaceItemWithSameValue() {
        //given
        hashTable = new HashListChaining<>(1);
        String item1 = "ADRIAN";
        String item2 = "ADRIAN";

        //when
        hashTable.add(item1);
        hashTable.add(item2);

        //then
        assertTrue(hashTable.isNextElementNull(item1));
        assertEquals(hashTable.getnElems(), 1);
    }


    @Test
    public void shouldDeleteSingleElement() {
        //given
        hashTable = new HashListChaining<>(1);
        String item1 = "ADRIAN";

        //when
        hashTable.add(item1);
        hashTable.delete(item1);
        String foundItem = hashTable.get(item1);

        //then
        assertNull(foundItem);
        assertNull(hashTable.getValue(0));
        assertEquals(hashTable.getnElems(), 0);

    }

    /**
     * Having provided items in such order:
     * "ADRIAN", "ADAM", "ALAN" - the following list will be created: "ALAN"->"ADAM"->"ADRIAN"
     * If "ALAN" is deleted, the list will look as follows : "ADAM" -> "ADRIAN"
     */
    @Test
    public void shouldDeleteFirstListItemProperly() {

        //given
        hashTable = new HashListChaining<>(1);
        String item1 = "ADRIAN";
        String item2 = "ADAM";
        String item3 = "ALAN";

        //when
        hashTable.add(item1);
        hashTable.add(item2);
        hashTable.add(item3);

        hashTable.delete(item3);
        String foundValue = hashTable.get(item3);

        //then
        assertNull(foundValue);
        assertEquals(hashTable.getnElems(), 2);
        assertEquals(hashTable.getValue(0), item2);
        assertEquals(hashTable.getNextElementValue(item2), item1);
        assertTrue(hashTable.isNextElementNull(item1));

    }

    /**
     * Having provided items in such order:
     * "ADRIAN", "ADAM", "ALAN" - the following list will be created: "ALAN"->"ADAM"->"ADRIAN"
     * If "ADAM" is deleted, the list will look as follows : "ALAN" -> "ADRIAN"
     */
    @Test
    public void shouldDeleteMiddleListItemProperly() {

        //given
        hashTable = new HashListChaining<>(1);
        String item1 = "ADRIAN";
        String item2 = "ADAM";
        String item3 = "ALAN";

        //when
        hashTable.add(item1);
        hashTable.add(item2);
        hashTable.add(item3);

        hashTable.delete(item2);
        String foundValue = hashTable.get(item2);


        //then
        assertNull(foundValue);
        assertEquals(hashTable.getnElems(), 2);
        assertEquals(hashTable.getValue(0), item3);
        assertEquals(hashTable.getNextElementValue(item3), item1);
        assertTrue(hashTable.isNextElementNull(item1));

    }

    /**
     * Having provided items in such order:
     * "ADRIAN", "ADAM", "ALAN" - the following list will be created: "ALAN"->"ADAM"->"ADRIAN"
     * If "ADRIAN" is deleted, the list will look as follows : "ALAN" -> "ADAM"
     */
    @Test
    public void shouldDeleteLastListItemProperly() {

        //given
        hashTable = new HashListChaining<>(1);
        String item1 = "ADRIAN";
        String item2 = "ADAM";
        String item3 = "ALAN";

        //when
        hashTable.add(item1);
        hashTable.add(item2);
        hashTable.add(item3);

        hashTable.delete(item1);
        String foundValue = hashTable.get(item1);


        //then
        assertNull(foundValue);
        assertEquals(hashTable.getnElems(), 2);
        assertEquals(hashTable.getValue(0), item3);
        assertEquals(hashTable.getNextElementValue(item3), item2);
        assertTrue(hashTable.isNextElementNull(item2));

    }

}
