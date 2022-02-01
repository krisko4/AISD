package pl.edu.pw.ee;


import pl.edu.pw.ee.services.HeapInterface;

public class Heap<T extends Comparable<T>> implements HeapInterface<T> {


    private int size = 1000;
    private T[] elements = (T[]) new Comparable[size];


    public int size() {
        return nElems;
    }

    private int nElems = 0;


    private void heapUp() {

        int newElementIndex = nElems - 1;
        while (newElementIndex > 0) {
            int parentIndex = (newElementIndex - 1) / 2;
            if (elements[parentIndex].compareTo(elements[newElementIndex]) >= 0) {
                return;
            }
            swap(parentIndex, newElementIndex);
            newElementIndex = parentIndex;
        }

    }

    private void heapDown() {
        int startIndex = 0;
        int child = 1;
        while (child < nElems) {
            if (child + 1 < nElems && elements[child + 1].compareTo(elements[child]) > 0) {
                child++;
            }
            if (elements[startIndex].compareTo(elements[child]) >= 0) return;
            swap(startIndex, child);
            startIndex = child;
            child = 2 * startIndex + 1;
        }
    }

    private T swap(int firstId, int secondId) {
        T firstValue = elements[firstId];
        elements[firstId] = elements[secondId];
        elements[secondId] = firstValue;
        return firstValue;
    }


    public void put(T item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");
        elements[nElems] = item;
        nElems++;
        heapUp();
        resizeIfNeeded();
    }

    private void resizeIfNeeded() {
        if (nElems != size) {
            return;
        }
        size *= 2;
        T[] newEls = (T[]) new Comparable[size];
        System.arraycopy(elements, 0, newEls, 0, elements.length);
        elements = newEls;

    }


    public T pop() {
        if (nElems == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T poppedItem = swap(0, --nElems);
        elements[nElems] = null;
        heapDown();
        return poppedItem;
    }
}
