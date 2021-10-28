package pl.edu.pw.ee;
import pl.edu.pw.ee.services.HeapInterface;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Heap<T extends Comparable<T>> implements HeapInterface<T> {

    private final List<T> elements = new ArrayList<>();
    private int heapSize = 0;


    public List<T> getElements(){
        return elements;
    }


    private void heapUp() {

        int newElementIndex = heapSize - 1;
        while (newElementIndex > 0) {
            int parentIndex = (newElementIndex - 1) / 2;
            if (elements.get(parentIndex).compareTo(elements.get(newElementIndex)) >= 0) {
                return;
            }
            swap(parentIndex, newElementIndex);
            newElementIndex = parentIndex;
        }

    }

    private void heapDown() {
        int startIndex = 0;
        int child = 1;
        while (child < heapSize) {
            if (child + 1 < heapSize && elements.get(child + 1).compareTo(elements.get(child)) > 0) {
                child++;
            }
            if (elements.get(startIndex).compareTo(elements.get(child)) >= 0) return;
            swap(startIndex, child);
            startIndex = child;
            child = 2 * startIndex + 1;
        }
    }

    private T swap(int firstId, int secondId) {
        T firstValue = elements.get(firstId);
        elements.set(firstId, elements.get(secondId));
        elements.set(secondId, firstValue);
        return firstValue;
    }

    @Override
    public void put(T item) {
        if(item == null) throw new IllegalArgumentException("Item cannot be null");
        elements.add(item);
        heapSize++;
        heapUp();
    }

    @Override
    public T pop() {
        if (heapSize == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T poppedItem = swap(0, --heapSize);
        heapDown();
        return poppedItem;
    }
}
