package pl.edu.pw.ee;


import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class HeapTest {

    private Heap<Double> heap;

    @Before
    public void setUp(){ heap = new Heap<>();}

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void shouldThrowExceptionOnEmptyHeapPop(){
        //when
        heap.pop();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNullItemPut(){
        //when
        heap.put(null);
    }


    @Test
    public void shouldPopSingleElement(){

        //given
        double item = 5;

        //when
        heap.put(item);
        double poppedItem = heap.pop();

        //then
        assertEquals(poppedItem, item, 0);
        assertEquals(heap.size(), 0);
    }



    @Test
    public void shouldPopLowestValueFirst(){

        //given
        double[] nums = {2,1,4,6,3,9,12};

        //when
        for (double num : nums) {
            heap.put(num);
        }

        //then
        double poppedItem = heap.pop();
        assertEquals(poppedItem, 1, 0);
    }


    @Test
    public void shouldPopValuesInTheRightOrder(){

        //given
        double[] nums = {2,1,4,6,3,9,12};

        //when
        for (double num : nums) {
            heap.put(num);
        }
        double[] poppedArray = new double[nums.length];
        for (int i = 0; i < poppedArray.length; i++) {
            poppedArray[i] = heap.pop();
        }

        //then
        double[] expected = {1, 2, 3, 4, 6, 9, 12};
        assertArrayEquals(poppedArray, expected, 0);


    }













}
