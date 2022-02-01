package pl.edu.pw.ee;


/**
 * SelectionSort algorithm testing class
 * IMPORTANT: The complexity of SelectionSort is O(n^2).
 * This algorithm has no optimistic or pesimistic case. Its' duration depends on size of the array, not an order of the elements.
 */

public class SelectionSortTest extends SortingTest {

    public SelectionSortTest() {
        super(new SelectionSort());
    }
}
