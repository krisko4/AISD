package pl.edu.pw.ee.services;

public interface HashTable<T extends Comparable<T>> {

    void put(T elem);

    T get(T elem);

    void delete(T elem);

    // method for testing purposes, boilerplate code reduction
    void reset(int size);
}
