package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

import java.util.NoSuchElementException;

public abstract class HashOpenAdressing<T extends Comparable<T>> implements HashTable<T> {

    private final T nil = null;
    private int size;
    private int nElems;
    private T[] hashElems;
    private final double correctLoadFactor;


    public int getnElems() {
        return nElems;
    }

    public int getHashId(T elem) {
        return find(elem);
    }

    public T getElem(int hashId) {
        return hashElems[hashId];
    }

    int getSize() {
        return size;
    }

    private static class DeletedElem implements Comparable {
        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }


    HashOpenAdressing() {
        this(2039); // initial size as random prime number
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
        while (hashElems[hashId] != nil && !(hashElems[hashId] instanceof DeletedElem) && hashElems[hashId].compareTo(newElem) != 0) {
            i = (i + 1) % size;
            hashId = hashFunc(key, i);
            if (i == 0) hashElems = doubleResize(hashElems);
        }
        if (hashElems[hashId] != nil && !(hashElems[hashId] instanceof DeletedElem) && hashElems[hashId].compareTo(newElem) == 0) {
            return hashElems;
        }
        hashElems[hashId] = newElem;
        nElems++;
        return hashElems;
    }

    @Override
    public void put(T newElem) {
        hashElems = put(newElem, hashElems);
    }

    @Override
    public T get(T elem) {
        int hashId = find(elem);
        return hashElems[hashId];
    }

    public boolean isDeleted(int hashId) {
        return hashElems[hashId] instanceof DeletedElem;
    }

    @Override
    public void delete(T elem) {
        int hashId = find(elem);
        hashElems[hashId] = (T) new DeletedElem();
        nElems--;
    }


    private int find(T elem) {
        validateInputElem(elem);
        if (nElems == 0) throw new IllegalStateException("Unable to search for element in empty hashTable");
        int key = Math.abs(elem.hashCode());
        int i = 0;
        int hashId = hashFunc(key, i);
        while ((hashElems[hashId] != nil && hashElems[hashId].compareTo(elem) != 0) || hashElems[hashId] instanceof DeletedElem) {
            i = (i + 1) % size;
            hashId = hashFunc(key, i);
            if (i == 0) throw new NoSuchElementException("Element: " + elem + " not found in hashTable ");
        }
        if (hashElems[hashId] == nil || hashElems[hashId].compareTo(elem) != 0)
            throw new NoSuchElementException("Element: " + elem + " not found in hashTable ");
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


    @Override
    public void reset(int size) {
        this.size = size;
        nElems = 0;
        hashElems = (T[]) new Comparable[this.size];
    }

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