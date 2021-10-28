package pl.edu.pw.ee;


import org.junit.Before;
import org.junit.jupiter.api.*;
import pl.edu.pw.ee.services.Sorting;
import java.util.Arrays;
import java.util.Random;

/**
 * SelectionSort has O(n^2) time complexity in each case.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SelectionSortPerformanceTest {

    private Sorting sorting;

    @BeforeEach
    public void setUp() {
        sorting = new SelectionSort();
    }


    @Test
    @Order(1)
    public void shouldTestSortedArrayCasePerformance() {

        int n = 100;
        long[] times = new long[n];
        for(int i = 1; i < n + 1; i++){
            double[] nums = new double[i * 500];
            for(int j = 0; j < nums.length; j++){
                nums[j] = j;
            }
            long startTime = System.nanoTime();
            sorting.sort(nums);
            long endTime = System.nanoTime();
            times[i - 1] = (endTime - startTime) / 1000000;
        }
        System.out.println(Arrays.toString(times));
    }

    @Test
    @Order(2)
    public void shouldTestSortedDescendantArrayCasePerformance() {
        int n = 100;
        long[] times = new long[n];
        for(int i = 1; i < n + 1; i++){
            double[] nums = new double[i * 500];
            for(int j = 0; j < nums.length; j++){
                nums[j] = nums.length - j;
            }
            long startTime = System.nanoTime();
            sorting.sort(nums);
            long endTime = System.nanoTime();
            times[i - 1] = (endTime - startTime) / 1000000;
        }
        System.out.println(Arrays.toString(times));
    }

    @Test
    @Order(3)
    public void shouldTestRandomArrayCasePerformance() {

        int n = 100;
        long[] times = new long[n];
        Random random = new Random(1);
        for(int i = 1; i < n + 1; i++){
            double[] nums = new double[i * 500];
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
