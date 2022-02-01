package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RbtMapTest {

    public RbtMap<String, Integer> rbtMap;


    @Before
    public void setUp() {
        rbtMap = new RbtMap<>();

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNullValueSet() {
        //given
        String key = "key";
        Integer value = null;

        //when
        rbtMap.setValue(key, value);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNullKeySet() {
        //given
        String key = null;
        Integer value = 5;

        //when
        rbtMap.setValue(key, value);

        //then
        assert false;
    }


    @Test
    public void shouldReturnNullOnNotFoundValueGet() {
        //given
        String key1 = "key";
        Integer value1 = 5;
        String key2 = "key2";

        //when
        rbtMap.setValue(key1, value1);
        Integer value = rbtMap.getValue(key2);

        //then
        assertNull(value);
    }

    @Test
    public void shouldGetPreviouslySetValueCorrectly() {
        //given
        String key = "key";
        Integer value = 5;

        //when
        rbtMap.setValue(key, value);
        Integer result = rbtMap.getValue(key);

        //then
        assertEquals(result, value);
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNullKeyGet() {
        //given
        String key = null;

        //when
        rbtMap.getValue(key);

        //then
        assert false;
    }

}
