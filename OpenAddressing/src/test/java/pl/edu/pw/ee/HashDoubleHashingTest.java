package pl.edu.pw.ee;

import org.junit.Test;

import static org.junit.Assert.*;


public class HashDoubleHashingTest extends HashTest {

    private HashDoubleHashing<String> hash;

    public HashDoubleHashingTest() {
        super(new HashDoubleHashing<>());
    }


    @Test
    public void shouldInheritSizeProperly() {
        //given
        int size = 10;

        //when
        hash = new HashDoubleHashing<>(size);

        //then
        assertEquals(hash.getSize(), size);
    }


    @Test
    public void shouldPlaceElementsCorrectly() {
        //given
        int size = 10;
        HashDoubleHashing<Integer> hash = new HashDoubleHashing<>(size);
        int[] values = {18, 41, 22, 32, 44, 59, 79};

        //when
        for (int value : values) {
            hash.put(value);
        }

        //then
        assertEquals(hash.getHashId(values[0]), 8);
        assertEquals(hash.getHashId(values[1]), 1);
        assertEquals(hash.getHashId(values[2]), 2);
        assertEquals(hash.getHashId(values[3]), 7);
        assertEquals(hash.getHashId(values[4]), 4);
        assertEquals(hash.getHashId(values[5]), 9);
        assertEquals(hash.getHashId(values[6]), 5);


    }

    /**
     * The following case results with infinite loop. In order to prevent it,
     * hash size is automatically doubled when infinite loop is detected.
     */
    @Test
    public void shouldResizeOnInfiniteLoop() {
        //given
        int size = 10;
        HashDoubleHashing<Integer> hash = new HashDoubleHashing<>(size);
        int[] elems = {11, 33, 55, 77, 99, 1};

        //when
        for (int elem : elems) {
            hash.put(elem);
        }

        //then
        assertEquals(hash.getSize(), size * 2);

    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenInitialSizeIsLowerThanOne() {
        //given
        int initialSize = 0;

        //when
        hash = new HashDoubleHashing<>(initialSize);

        //then
        assert false;
    }


}
