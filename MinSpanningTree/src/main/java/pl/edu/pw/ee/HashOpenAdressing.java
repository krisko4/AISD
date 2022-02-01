package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;
import java.util.Iterator;



public abstract class HashOpenAdressing<T extends Comparable<T>> implements HashTable<T> {

    private final T nil = null;
    private int size;
    private int nElems;
    private T[] hashElems;
    private final double correctLoadFactor;
    private int[] hashIds = new int[1000];

    public int getHashId(T elem) {
        return find(elem);
    }

    private class MyIterator implements Iterator<T> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return hashElems[hashIds[index]] != null;
        }

        @Override
        public T next() {
            return hashElems[hashIds[index++]];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    @Override
    public int size() {
        return nElems;
    }

    public int getSize() {
        return size;
    }

    public T getFirst() {
        return hashElems[hashIds[0]];
    }

    HashOpenAdressing() {
        this(2039);
    }

    HashOpenAdressing(int size) {
        validateHashInitSize(size);
        this.size = size;
        this.hashElems = (T[]) new Comparable[this.size];
        this.correctLoadFactor = 0.75;
    }




    private T[] put(T newElem, T[] hashElems) {
        validateInputElem(newElem);
        hashElems = resizeIfNeeded(hashElems);
        int key = Math.abs(newElem.hashCode());
        int i = 0;
        int hashId = hashFunc(key, i);
        while (hashElems[hashId] != nil && hashElems[hashId].compareTo(newElem) != 0) {
            i = (i + 1) % size;
            hashId = hashFunc(key, i);
            if (i == 0) hashElems = doubleResize(hashElems);
        }
        if (hashElems[hashId] != nil && hashElems[hashId].compareTo(newElem) == 0) {
            return hashElems;
        }
        hashElems[hashId] = newElem;
        nElems++;
        resizeHashIdsIfNeeded();
        hashIds[nElems - 1] = hashId;
        return hashElems;
    }

    private void resizeHashIdsIfNeeded() {
        if (hashIds.length == nElems) {
            int[] newHashIds = new int[hashIds.length * 2];
            System.arraycopy(hashIds, 0, newHashIds, 0, hashIds.length);
            hashIds = newHashIds;
        }
    }

    @Override
    public void put(T newElem) {
        hashElems = put(newElem, hashElems);
    }

    @Override
    public T get(T elem) {
        int hashId = find(elem);
        if (hashId == size + 1) return null;
        return hashElems[hashId];
    }

    private int find(T elem) {
        validateInputElem(elem);
        int key = Math.abs(elem.hashCode());
        int i = 0;
        int hashId = hashFunc(key, i);
        while ((hashElems[hashId] != nil && hashElems[hashId].compareTo(elem) != 0)) {
            i = (i + 1) % size;
            hashId = hashFunc(key, i);
            if (i == 0) return size + 1;
        }
        if (hashElems[hashId] == nil || hashElems[hashId].compareTo(elem) != 0)
            return size + 1;
        return hashId;
    }

    private void validateHashInitSize(int initialSize) {
        if (initialSize < 1) {
            throw new IllegalArgumentException("Initial size of hash table cannot be lower than 1!");
        }
    }

    private void validateInputElem(T newElem) {
        if (newElem == null) {
            throw new IllegalArgumentException("Input elem cannot be null!");
        }
    }

    abstract int hashFunc(int key, int i);

    private T[] resizeIfNeeded(T[] hashElems) {
        double loadFactor = countLoadFactor();
        if (loadFactor >= correctLoadFactor) {
            return doubleResize(hashElems);
        }
        return hashElems;
    }

    private double countLoadFactor() {
        return (double) nElems / size;
    }

    private T[] doubleResize(T[] hashElems) {
        size *= 2;
        T[] newHashElems = (T[]) new Comparable[size];
        nElems = 0;
        for (T elem : hashElems) {
            if (elem != nil) put(elem, newHashElems);
        }
        return newHashElems;
    }
}