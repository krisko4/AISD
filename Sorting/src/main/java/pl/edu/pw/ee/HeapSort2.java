package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;


/**
 * HeapSort implementation without separate Heap class
 */
public class HeapSort2 implements Sorting {


    @Override
    public void sort(double[] nums) {

        if (nums == null) throw new IllegalArgumentException("Array cannot be null");
        if (nums.length == 1) return;
        int lastIndex = nums.length - 1;
        int parentIndex = (lastIndex - 1) / 2;
        while (parentIndex >= 0) {
            validate(nums, parentIndex, nums.length);
            parentIndex--;
        }
        int length = nums.length;
        while (length > 0) {
            swap(nums, 0, length - 1);
            length--;
            validate(nums, 0, length);
        }
    }

    private void validate(double[] nums, int parentIndex, int length) {
        int firstChild = parentIndex * 2 + 1;
        if (firstChild >= length) return;
        int secondChild = firstChild + 1;
        if (secondChild < length) {
            if (nums[firstChild] <= nums[parentIndex] && nums[secondChild] <= nums[parentIndex]) return;
            if (nums[firstChild] > nums[secondChild]) {
                swap(nums, parentIndex, firstChild);
                validate(nums, firstChild, length);
                return;
            }
            swap(nums, parentIndex, secondChild);
            validate(nums, secondChild, length);
            return;
        }
        if (nums[firstChild] > nums[parentIndex]) {
            swap(nums, parentIndex, firstChild);
            validate(nums, firstChild, length);
        }
    }


    private void swap(double[] data, int firstId, int secondId) {
        if (firstId != secondId) {
            double firstValue = data[firstId];
            data[firstId] = data[secondId];
            data[secondId] = firstValue;
        }
    }
}
