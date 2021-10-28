package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.services.Sorting;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

public class HeapSortTest {

    private Sorting sorting;

    @Before
    public void setUp(){sorting = new HeapSort();}

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNullArray(){
        //when
        sorting.sort(null);
    }

    @Test
    public void shouldSortCorrectly(){
        // when
        double[] nums = {9,7,4,3,6,5,1,2,8,11};

        //given
        sorting.sort(nums);

        //then
        double[] expected = {1,2,3,4,5,6,7,8,9,11};
        assertArrayEquals(nums, expected, 0);

    }

    /**
     * Optimistic case - array elements are equal. Complexity in this case equals O(n).
     */

    @Test
    public void shouldSortEqualDataArray(){

        //when
        double[] nums = {1,1,1,1,1,1,1,1,1,1};

        //given
        sorting.sort(nums);

        //then
        double[] expected = {1,1,1,1,1,1,1,1,1,1};
        assertArrayEquals(nums, expected, 0);


    }


    @Test
    public void shouldSortOrderedDescendingArray(){

        // given
        double[] nums = {10, 9, 8, 7, 6, 5, 4, 3, 2 , 1};

        // when
        sorting.sort(nums);

        // then
        double[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
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




}
