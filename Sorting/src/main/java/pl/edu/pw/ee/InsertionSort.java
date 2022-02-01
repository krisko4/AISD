package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class InsertionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("nums array cannot be null!");
        }
        for (int i = 1; i < nums.length; i++) {
            int j = i - 1;
            double currentValue = nums[i];
            while (j >= 0 && currentValue <= nums[j]) {
                nums[j + 1] = nums[j];
                j -= 1;
            }
            nums[j + 1] = currentValue;
        }

    }

}
