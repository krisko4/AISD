package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.services.Sorting;

import java.util.Arrays;
import java.util.Random;

/**
 * Performance tests for QuickSort algorithm
 */
public class QuickSortPerformanceText {

    private Sorting sorting;

    @Before
    public void setUp() {
        sorting = new QuickSort();
    }

    /**
     * Pessimistic case - an array is already sorted
     */
    @Test
    public void shouldTestPessimisticCasePerformance() {

        int n = 100;
        long[] times = new long[n];
        for(int i = 1; i < n + 1; i++){
            double[] nums = new double[i * 1000];
            for(int j = 0; j < nums.length; j++){
                nums[j] = j;
            }
            long startTime = System.nanoTime();
            sorting.sort(nums);
            long endTime = System.nanoTime();
            times[i - 1] = (endTime - startTime)/1000000;
        }

        System.out.println(Arrays.toString(times));
    }

    /**
     * Random case
     */
    @Test
    public void shouldTestRandomCasePerformance() {
        int n = 100;
        long[] times = new long[n];
        Random random = new Random(1);
        for(int i = 1; i < n + 1; i++){
            double[] nums = new double[i * 1000];
            for(int j = 0; j < nums.length; j++){
                nums[j] = random.nextDouble();
            }
            long startTime = System.nanoTime();
            sorting.sort(nums);
            long endTime = System.nanoTime();
            times[i - 1] = (endTime - startTime)/1000000;
        }
        System.out.println(Arrays.toString(times));
    }


}
