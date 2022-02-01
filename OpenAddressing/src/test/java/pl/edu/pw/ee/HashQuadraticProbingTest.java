package pl.edu.pw.ee;


import org.junit.Test;

import static org.junit.Assert.*;


public class HashQuadraticProbingTest extends HashTest {

    private HashQuadraticProbing<String> hash;

    public HashQuadraticProbingTest() {
        super(new HashQuadraticProbing<>());
    }


    @Test
    public void shouldInheritSizeProperly() {
        //given
        int size = 10;
        double a = 1;
        double b = 1;

        //when
        hash = new HashQuadraticProbing<>(size, a, b);

        //then
        assertEquals(hash.getSize(), size);
        assertEquals(hash.getA(), a, 0);
        assertEquals(hash.getB(), b, 0);
    }


    @Test
    public void shouldPlaceElementsCorrectly() {
        //given
        HashQuadraticProbing<Integer> hash = new HashQuadraticProbing<>(10, 1, 1);

        int[] elems = {18, 41, 22, 32, 44, 59, 79};

        //when
        for (int elem : elems) {
            hash.put(elem);
        }

        //then
        assertEquals(hash.getHashId(elems[0]), 8);
        assertEquals(hash.getHashId(elems[1]), 1);
        assertEquals(hash.getHashId(elems[2]), 2);
        assertEquals(hash.getHashId(elems[3]), 4);
        assertEquals(hash.getHashId(elems[4]), 6);
        assertEquals(hash.getHashId(elems[5]), 9);
        assertEquals(hash.getHashId(elems[6]), 5);


    }

    /**
     * The following case results with infinite loop. In order to prevent it,
     * hash size is automatically doubled when infinite loop is detected.
     */
    @Test
    public void shouldResizeOnInfiniteLoop() {
        //given
        HashQuadraticProbing<Integer> hash = new HashQuadraticProbing<>(10, 1, 1);
        int[] elems = {11, 21, 31, 41, 55};

        //when
        for (int elem : elems) {
            hash.put(elem);
        }

        //then
        assertEquals(hash.getSize(), 20);

    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenInitialSizeIsLowerThanOne() {
        // given
        int initialSize = 0;

        // when
        hash = new HashQuadraticProbing<>(initialSize, 1, 1);


        // then
        assert false;
    }


}
