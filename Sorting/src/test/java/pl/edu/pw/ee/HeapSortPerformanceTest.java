package pl.edu.pw.ee;



import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pl.edu.pw.ee.services.Sorting;

import java.util.Arrays;
import java.util.Random;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HeapSortPerformanceTest {

    private Sorting sorting;

    /**
     * Pessimistic case - an array is already sorted. Complexity in this case is O(nlogn)
     */
    @Test
    @Order(1)
    public void shouldTestPessimisticCasePerformance(){

        int n = 100;
        long[] times = new long[n];
        for(int i = 1; i < n + 1; i++){
            double[] nums = new double[i * 10000];
            for(int j = 0; j < nums.length; j++){
                nums[j] = j;
            }
            sorting = new HeapSort();
            long startTime = System.nanoTime();
            sorting.sort(nums);
            long endTime = System.nanoTime();
            times[i - 1] = (endTime - startTime) /1000000;
        }
        System.out.println(Arrays.toString(times));
    }

    /**
     * Optimistic case - array elements are equal. Complexity in this case equals O(n).
     */
    @Test
    @Order(2)
    public void shouldTestOptimisticCasePerformance(){
        int n = 100;
        long[] times = new long[n];
        for(int i = 1; i < n + 1; i++){
            double[] nums = new double[i * 10000];
            Arrays.fill(nums, 1);
            sorting = new HeapSort();
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
    @Order(3)
    public void shouldTestRandomCasePerformance() {
        int n = 100;
        long[] times = new long[n];
        Random random = new Random(1);
        for(int i = 1; i < n + 1; i++){
            sorting = new HeapSort();
            double[] nums = new double[i * 10000];
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
