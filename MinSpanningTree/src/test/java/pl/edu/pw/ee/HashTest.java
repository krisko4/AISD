package pl.edu.pw.ee;


import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


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


    @Test
    public void shouldNotIncreaseNElemsWhenPuttingTheSameElement() {
        //given
        String elem = "elem";

        //when
        hash.put(elem);
        hash.put(elem);

        //then
        assertEquals(hash.size(), 1);
    }

    @Test
    public void shouldReturnNullWhenGettingElementFromEmptyHashTable() {
        //given
        String elem = "elem";

        //when
        String result = hash.get(elem);

        //then
        assertNull(result);

    }


    @Test
    public void shouldReturnNullIfElementNotFound() {
        //given
        String elem1 = "elem1";
        String elem2 = "elem2";

        //when
        hash.put(elem1);
        String result = hash.get(elem2);

        //then
        assertNull(result);


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


    @Test
    public void should_CorrectlyAddNewElems_WhenNotExistInHashTable() {
        //given
        String newElem = "nothing special";

        //when
        int nElemsBeforePut = hash.size();
        hash.put(newElem);
        int nElemsAfterPut = hash.size();

        //then
        assertEquals(0, nElemsBeforePut);
        assertEquals(1, nElemsAfterPut);

    }


}
