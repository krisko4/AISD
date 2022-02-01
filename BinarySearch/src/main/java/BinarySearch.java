
public class BinarySearch implements Searcher {


    @Override
    public int search(double[] nums, double toFind) {
        int result = -1;
        if (nums == null) {
            return result;
        }
        int start = 0;
        int end = nums.length - 1;
        int currentIndex;

        while (start <= end) {
            currentIndex = start + (end - start) / 2;
            if (nums[currentIndex] > toFind) {
                end = currentIndex - 1;
            } else if (nums[currentIndex] < toFind) {
                start = currentIndex + 1;
            } else {
                result = currentIndex;
                break;
            }
        }
        return result;
    }
}


