package pl.edu.pw.ee;

import static org.junit.Assert.assertArrayEquals;


import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.services.Sorting;

/**
 * QuickSort algorithm testing class
 */
public class QuickSortTest {
    private Sorting sorting;

    @Before
    public void setUp() {
        sorting = new QuickSort();
    }



    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNullArray(){

        //when
        sorting.sort(null);
    }

    @Test
    public void shouldSortBigRandomData(){

        // given
        Random random = new Random(40);
        double[] nums = new double[1000000];
        for(int i = 0; i < nums.length; i++ ){
            nums[i] = random.nextDouble();
        }

        // when
        sorting.sort(nums);

        // then
        double[] expected = Arrays.copyOf(nums, nums.length);
        Arrays.sort(expected);
        assertArrayEquals(nums, expected, 0);

    }

    /**
     * Optimistic case - pivot is always found in the middle.
     * Complexity in this case is O(nlogn)
     */
    @Test
    public void shouldSortOnPivotInTheMiddle() {

        // given
        double[] nums = { 30, 5, 13, 8, 22, 21, 17 };

        // when
        sorting.sort(nums);

        // then
        double[] expected = {5, 8, 13, 17, 21, 22, 30};
        System.out.println(Arrays.toString(nums));
        assertArrayEquals(nums, expected, 0);

    }

    /**
     * Pesimistic case - pivot is always found on the edge of an array -
     * it occurs when array is already sorted or inverse sorted
     * Complexity in this case is O(n^2).
     */
    @Test
    public void shouldSortOnExtremePivot() {

        // given
        double[] nums = { 1, 2, 3, 4, 5, 6, 7, 8 , 9, 10 };

        // when
        sorting.sort(nums);

        // then
        double[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(Arrays.toString(nums));
        assertArrayEquals(nums, expected, 0);

    }


    @Test
    public void shouldSortUnorderedData() {

        // given
        double[] nums = {2,5,1,3,4,0,6,2,5 };

        // when
        sorting.sort(nums);

        // then
        double[] expected = {0,1,2,2,3,4,5,5,6};
        System.out.println(Arrays.toString(nums));
        assertArrayEquals(nums, expected, 0);

    }


}
