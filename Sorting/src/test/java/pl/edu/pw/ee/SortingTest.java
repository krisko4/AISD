package pl.edu.pw.ee;

import org.junit.Test;
import pl.edu.pw.ee.services.Sorting;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

public abstract class SortingTest {

    public Sorting sorting;

    public SortingTest(Sorting sorting) {
        this.sorting = sorting;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNullArray() {
        //given
        double[] nums = null;

        //when
        sorting.sort(nums);

        //then
        assert false;
    }

    @Test
    public void shouldSortBigRandomData() {

        //given
        Random random = new Random(40);
        double[] nums = new double[1000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextDouble();
        }


        //when
        sorting.sort(nums);

        //then
        double[] expected = Arrays.copyOf(nums, nums.length);
        Arrays.sort(expected);
        assertArrayEquals(nums, expected, 0);

    }


    @Test
    public void shouldSortUnorderedArray() {

        //given
        double[] nums = {30, 5, 13, 8, 22, 21, 17};

        //when
        sorting.sort(nums);

        //then
        double[] expected = {5, 8, 13, 17, 21, 22, 30};
        System.out.println(Arrays.toString(nums));
        assertArrayEquals(nums, expected, 0);

    }


    @Test
    public void shouldSortOrderedAscendingArray() {

        //given
        double[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        //when
        sorting.sort(nums);

        //then
        double[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(Arrays.toString(nums));
        assertArrayEquals(nums, expected, 0);

    }

    @Test
    public void shouldSortEqualDataArray() {

        //given
        double[] nums = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

        //when
        sorting.sort(nums);

        //then
        double[] expected = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        assertArrayEquals(nums, expected, 0);


    }


    @Test
    public void shouldSortOrderedDescedingArray() {

        //given
        double[] nums = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        //when
        sorting.sort(nums);

        //then
        double[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(Arrays.toString(nums));
        assertArrayEquals(nums, expected, 0);

    }

}
