package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.services.HeapInterface;

import java.util.Comparator;

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
    public void shouldCreateSingleElementHeap(){

        //given
        double item = 5;

        //when
        heap.put(item);

        //then
        assertEquals(heap.getElements().get(0), item, 0.0);
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
    }

    @Test
    public void shouldPopBiggestValueFirst(){

        //given
        double[] nums = {2,1,4,6,3,9,12};

        //when
        for (double num : nums) {
            heap.put(num);
        }

        //then
        double poppedItem = heap.pop();
        assertEquals(poppedItem, 12, 0);
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
        double[] expected = {12, 9, 6, 4, 3, 2, 1};
        assertArrayEquals(poppedArray, expected, 0);


    }




    @Test
    public void shouldHeapUpCorrectly(){

        //given
        double[] nums = {9, 7, 4, 3, 6, 5, 1, 2, 8, 11};

        //when
        for (double num : nums) {
            heap.put(num);
        }

        //then
        double[] heapedUp = new double[heap.getElements().size()];
        for(int i = 0; i < heap.getElements().size(); i++){
            heapedUp[i] = heap.getElements().get(i);
        }

        double[] expected = {11,9,5,7,8,4,1,2,3,6};
        assertArrayEquals(heapedUp, expected, 0);

    }












}
