package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class SelectionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        if(nums == null){
            throw new IllegalArgumentException("nums array cannot be null!");
        }
        for (int i = 0; i < nums.length; i++) {
            int currentMinIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[currentMinIndex]) {
                    currentMinIndex = j;
                }
            }
            double currentMinValue = nums[i];
            nums[currentMinIndex] = nums[i];
            nums[i] = currentMinValue;
        }
    }

}
