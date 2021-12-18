package pl.edu.pw.ee;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.services.Sorting;

/**
 * SelectionSort algorithm testing class
 * IMPORTANT: The complexity of SelectionSort is O(n^2).
 * This algorithm has no optimistic or pesimistic case. Its' duration depends on size of the array, not an order of the elements.
 */

public class SelectionSortTest {
    private Sorting sorting;

    @Before
    public void setUp() {
        sorting = new SelectionSort();
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
        double[] nums = new double[100000];
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

    @Test
    public void shouldSortUnorderedArray(){

        // given
        double[] nums = { 30, 5, 13, 8, 22, 21, 17 };

        // when
        sorting.sort(nums);

        // then
        double[] expected = {5, 8, 13, 17, 21, 22, 30};
        System.out.println(Arrays.toString(nums));
        assertArrayEquals(nums, expected, 0);

    }



    @Test
    public void shouldSortOrderedAscendingArray(){

        // given
        double[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // when
        sorting.sort(nums);

        // then
        double[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(Arrays.toString(nums));
        assertArrayEquals(nums, expected, 0);

    }


    @Test
    public void shouldSortOrderedDescedingArray(){

        // given
        double[] nums = {10, 9, 8, 7, 6, 5, 4, 3, 2 , 1};

        // when
        sorting.sort(nums);

        // then
        double[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(Arrays.toString(nums));
        assertArrayEquals(nums, expected, 0);

    }

}
