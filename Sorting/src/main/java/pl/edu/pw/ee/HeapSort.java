package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HeapInterface;
import pl.edu.pw.ee.services.Sorting;


public class HeapSort implements Sorting {


    private final HeapInterface<Double> heap = new Heap<>();


    @Override
    public void sort(double[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("nums array cannot be null!");
        }
        for (double num : nums) {
            heap.put(num);
        }
        for (int i = 0; i < nums.length; i++) {
            nums[nums.length - 1 - i] = heap.pop();
        }

    }
}
