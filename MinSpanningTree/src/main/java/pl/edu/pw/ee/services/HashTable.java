package pl.edu.pw.ee.services;


public interface HashTable<T extends Comparable<T>> extends Iterable<T> {

    void put(T elem);

    T get(T elem);

    T getFirst();

    int size();



}
