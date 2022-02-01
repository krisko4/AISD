package pl.edu.pw.ee;


import org.junit.Test;


import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class HashTest {

    public HashOpenAdressing<String> hash;


    public HashTest(HashOpenAdressing<String> hash) {
        this.hash = hash;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenPuttingNullElement() {
        //given
        String elem = null;

        //when
        hash.put(elem);

        //then
        assert false;

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnInitialHashSizeLowerThan1() {
        //given
        int size = -1;

        //when
        hash = new HashLinearProbing<>(size);

        //then
        assert false;
    }


    @Test
    public void shouldNotIncreaseNElemsWhenPuttingTheSameElement() {
        //given
        String elem = "elem";

        //when
        hash.put(elem);
        hash.put(elem);

        //then
        assertEquals(hash.getnElems(), 1);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionWhenGettingElementFromEmptyHashTable() {
        //given
        String elem = "elem";

        //when
        hash.get(elem);

        //then
        assert false;

    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionWhenDeletingElementFromEmptyHashTable() {
        //given
        String elem = "elem";

        //when
        hash.delete(elem);

        //then
        assert false;

    }

    @Test
    public void shouldPutDeletedElementBackToTheSameIndex() {
        //given

        String[] elems = {"Marek", "Jarek", "Bolek", "Stefan", "Agnieszka"};

        //when
        for (String elem : elems) {
            hash.put(elem);
        }
        int hashId1 = hash.getHashId(elems[2]);
        hash.delete(elems[2]);
        hash.put(elems[2]);
        int hashId2 = hash.getHashId(elems[2]);

        //then
        assertEquals(hashId1, hashId2);

    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowExceptionIfElementNotFound() {
        //given
        String elem1 = "elem1";
        String elem2 = "elem2";

        //when
        hash.put(elem1);
        hash.get(elem2);

        //then
        assert false;


    }


    @Test(expected = NoSuchElementException.class)
    public void shouldThrowExceptionWhenGettingDeletedElementFromMultiElementHashTable() {
        //given
        String elem1 = "elem1";
        String elem2 = "elem2";

        //when
        hash.put(elem1);
        hash.put(elem2);
        hash.delete(elem1);
        hash.get(elem1);

        //then
        assert false;

    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionWhenGettingDeletedElementFromSingleElementHashTable() {
        //given
        String elem1 = "elem1";

        //when
        hash.put(elem1);
        hash.delete(elem1);
        hash.get(elem1);

        //then
        assert false;

    }

    @Test
    public void shouldDeleteSingleElementCorrectly() {
        //given
        String[] elems = {"Marek", "Jarek", "Bolek", "Stefan", "Agnieszka"};

        //when
        for (String elem : elems) {
            hash.put(elem);
        }
        int hashId = hash.getHashId(elems[0]);
        hash.delete(elems[0]);

        //then
        assertTrue(hash.isDeleted(hashId));
        assertEquals(hash.getnElems(), elems.length - 1);
    }

    @Test
    public void shouldFindElementCorrectly() {
        //given
        String elem1 = "elem1";
        String elem2 = "elem2";

        //when
        hash.put(elem1);
        hash.put(elem2);

        //then
        assertEquals(hash.get(elem1), elem1);
        assertEquals(hash.get(elem2), elem2);

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenGettingNullElement() {
        //given
        String elem = null;

        //when
        hash.get(elem);

        //then
        assert false;

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenDeletingNullElement() {
        //given
        String elem = null;

        //when
        hash.delete(elem);

        //then
        assert false;

    }

    @Test
    public void should_CorrectlyAddNewElems_WhenNotExistInHashTable() {
        //given
        String newElem = "nothing special";

        //when
        int nElemsBeforePut = hash.getnElems();
        hash.put(newElem);
        int nElemsAfterPut = hash.getnElems();

        //then
        assertEquals(0, nElemsBeforePut);
        assertEquals(1, nElemsAfterPut);

    }


}
