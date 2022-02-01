package pl.edu.pw.ee.services;

/**
 * HashTable test interface, which reduces boilerplate code during performance tests.
 */
public interface HashTestInterface<T extends Comparable<T>> {

    void execute(HashTable<T> hashTable, T elem);
}
