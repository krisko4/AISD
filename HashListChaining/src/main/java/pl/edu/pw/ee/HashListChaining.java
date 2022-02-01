package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class HashListChaining<T extends Comparable<T>> implements HashTable<T> {

    private final Elem nil = null;
    private final List<Elem> hashElems;
    private final int size;
    private int nElem;

    private class Elem {
        private T value;
        private Elem next;

        Elem(T value, Elem nextElem) {
            this.value = value;
            this.next = nextElem;
        }
    }

    public T getValue(int index) {
        if (hashElems.get(index) == nil) return null;
        return hashElems.get(index).value;
    }

    public int calculateIndex(T value) {
        int hashCode = value.hashCode();
        return countHashId(hashCode);
    }


    public boolean isNextElementNull(T value) {
        int hashId = calculateIndex(value);
        Elem elem = hashElems.get(hashId);
        while (elem != nil && elem.value.compareTo(value) != 0) {
            elem = elem.next;
        }
        if (elem == nil) throw new NoSuchElementException("Object with value" + value + " not found");
        return elem.next == null;
    }

    public T getNextElementValue(T value) {
        return hashElems.get(calculateIndex(value)).next.value;
    }

    public int getnElems() {
        return nElem;
    }


    public HashListChaining(int size) {
        if (size <= 0) throw new IllegalArgumentException("HashTable size cannot be equal to or lower than 0");
        hashElems = new ArrayList<>();
        this.size = size;
        initializeHash();
    }

    @Override
    public void add(T value) {
        if (value == null) throw new IllegalArgumentException("Null values can't be added to HashTable");
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem oldElem = hashElems.get(hashId);
        while (oldElem != nil && oldElem.value.compareTo(value) != 0) {
            oldElem = oldElem.next;
        }
        if (oldElem != nil) {
            oldElem.value = value;
        } else {
            hashElems.set(hashId, new Elem(value, hashElems.get(hashId)));
            nElem++;
        }
    }

    @Override
    public T get(T value) {
        if (value == null) throw new IllegalArgumentException("HashTable does not contain null values");
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem elem = hashElems.get(hashId);

        while (elem != nil && elem.value.compareTo(value) != 0) {
            elem = elem.next;
        }

        return elem != nil ? elem.value : null;
    }

    @Override
    public void delete(T value) {
        if (value == null) throw new IllegalArgumentException("Cannot delete null element");
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);
        Elem elem = hashElems.get(hashId);
        if (elem == nil)
            throw new NoSuchElementException("Cannot delete item with value: " + value + ". It doesn't belong to HashTable.");
        if (elem.value.compareTo(value) == 0) {
            hashElems.set(hashId, elem.next);
            nElem--;
            return;
        }
        while (elem.next != nil && elem.next.value.compareTo(value) != 0) {
            elem = elem.next;
        }
        if (elem.next == nil)
            throw new NoSuchElementException("Cannot delete item with value: " + value + ". It doesn't belong to HashTable.");
        elem.next = elem.next.next;
        nElem--;
    }

    public double countLoadFactor() {
        return nElem / size;
    }

    private void initializeHash() {
        for (int i = 0; i < size; i++) {
            hashElems.add(nil);
        }
    }

    private int countHashId(int hashCode) {
        return Math.abs(hashCode) % size;
    }

}