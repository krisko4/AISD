package pl.edu.pw.ee;

import org.junit.Test;


import static org.junit.Assert.*;

public class HashLinearProbingTest extends HashTest  {



    public HashLinearProbingTest() {
        super(new HashLinearProbing<>());
    }


    @Test
    public void shouldInheritSizeProperly() {
        //given
        int size = 10;

        //when
        hash = new HashLinearProbing<>(size);

        //then
        assertEquals(hash.getSize(), size);
    }


    @Test
    public void shouldPlaceElementsCorrectly() {
        //given
        int size = 10;
        HashLinearProbing<Integer> hash = new HashLinearProbing<>(size);
        int[] values = {18, 41, 22, 32, 44, 59, 79};

        //when
        for (int value : values) {
            hash.put(value);
        }

        //then
        assertEquals(hash.getHashId(values[0]), 8);
        assertEquals(hash.getHashId(values[1]), 1);
        assertEquals(hash.getHashId(values[2]), 2);
        assertEquals(hash.getHashId(values[3]), 3);
        assertEquals(hash.getHashId(values[4]), 4);
        assertEquals(hash.getHashId(values[5]), 9);
        assertEquals(hash.getHashId(values[6]), 0);

    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenInitialSizeIsLowerThanOne() {
        //given
        int initialSize = 0;

        //when
        hash = new HashLinearProbing<>(initialSize);

        //then
        assert false;
    }


}
