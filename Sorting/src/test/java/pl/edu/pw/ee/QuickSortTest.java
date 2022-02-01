package pl.edu.pw.ee;

import static org.junit.Assert.assertArrayEquals;


import java.util.Arrays;

import org.junit.Test;


/**
 * QuickSort algorithm testing class
 */
public class QuickSortTest extends SortingTest {

    public QuickSortTest() {
        super(new QuickSort());
    }

    /**
     * Optimistic case - pivot is always found in the middle.
     * Complexity in this case is O(nlogn)
     */
    @Test
    public void shouldSortOnPivotInTheMiddle() {

        //given
        double[] nums = {30, 5, 13, 8, 22, 21, 17};

        //when
        sorting.sort(nums);

        //then
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

        //given
        double[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        //when
        sorting.sort(nums);

        //then
        double[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(Arrays.toString(nums));
        assertArrayEquals(nums, expected, 0);

    }


}
