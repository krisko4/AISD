package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class MergeSort implements Sorting {


    private void merge(double[] src, int s, int m, int e, double[] dst) {
        int i = s;
        int k = s;
        int j = m;
        while (i < m && j < e) {
            if (src[i] <= src[j]) {
                dst[k++] = src[i++];
            } else {
                dst[k++] = src[j++];
            }
        }
        while(i < m){
            dst[k++] = src[i++];
        }
        while(j < e){
            dst[k++] = src[j++];
        }
    }

    @Override
    public void sort(double[] nums) {
        if(nums == null){
            throw new IllegalArgumentException("input array cannot be null");
        }
        int n = nums.length;
        double[] src = nums;
        double[] tmp = new double[n];
        double[] dst = tmp;
        for (int l = 1; l < n; l *= 2) {
            for (int i = 0; i < n; i += 2 * l) {
                merge(src, i, Math.min(i + l, n), Math.min(i + 2 * l, n), dst);
            }
            tmp = src;
            src = dst;
            dst = tmp;
        }
        if(src != nums){
            System.arraycopy(src, 0, nums, 0, n);
        }

    }
}
