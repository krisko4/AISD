package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.services.HashTestInterface;
import pl.edu.pw.ee.services.HashTable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * HashTable performance tests
 */
public class HashTablePerformanceTest {


    private final String[] words = new String[100000];
    private final HashTestInterface<String> hashPut = HashTable::put;
    private final HashTestInterface<String> hashGet = HashTable::get;

    /**
     * Converts 100 000 words from text file "words.txt" to String[] array
     */
    @Before
    public void setUp() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("words.txt"));
            String line = br.readLine();
            int i = 0;
            while (line != null) {
                words[i] = line;
                line = br.readLine();
                i++;
            }
        } catch (IOException e) {
            System.out.println("Failed to load file");
        }
    }


    private double calculateAverage(long[] times) {
        Arrays.sort(times);
        long[] properTimes = new long[10];
        System.arraycopy(times, 9, properTimes, 0, 10);
        double avg = 0;
        for (long properTime : properTimes) {
            avg += (double) (properTime / properTimes.length);
        }
        return avg / 1000000;
    }


    private double calculatePerformance(HashTable<String> hash, int size, HashTestInterface<String> hashTestInterface) {
        long[] times = new long[30];
        for (int i = 0; i < 30; i++) {
            if (hashTestInterface.equals(hashPut)) {
                hash.reset(size);
            }
            long startTime = System.nanoTime();
            for (String word : words) {
                hashTestInterface.execute(hash, word);
            }
            long endTime = System.nanoTime();
            long diff = endTime - startTime;
            times[i] = diff;
        }
        return calculateAverage(times);
    }


    /**
     * The test fills hashTable with 100 000 words
     * and calculates the time of putting and getting all the words back from the hashTable using three implemented algorithms.
     * The calculation is repeated 30 times. All the results are inserted into an array. The first 10 and last 10 results are ignored.
     * From the middle 10 results, an average is calculated and printed into console.
     */
    @Test
    public void shouldTestHashTablePerformance() {

        int size = 262144;
        HashTable<String> hash1 = new HashLinearProbing<>(size);
        List<HashTable<String>> hashList = new ArrayList<>();
        hashList.add(hash1);
        int a = 10;
        int b = 1;
        for (int i = 0; i < 10; i++) {
            hashList.add(new HashQuadraticProbing<>(size, a--, b++));
        }
        HashTable<String> hash2 = new HashDoubleHashing<>(size);
        hashList.add(hash2);
        for (HashTable<String> hash : hashList) {
            size = 262144;
            String hashName = hash.getClass().getSimpleName();
            System.out.println("===================== " + hashName + " ======================");
            if (hashName.equals("HashQuadraticProbing")) {
                System.out.println("a: " + ((HashQuadraticProbing<String>) hash).getA() + " b: " + ((HashQuadraticProbing<String>) hash).getB());
            }
            System.out.println("|\tsize \t| \t put \t\t| \t get \t\t|");

            for (int i = 0; i < 10; i++) {
                double putAverage = calculatePerformance(hash, size, hashPut);
                double getAverage = calculatePerformance(hash, size, hashGet);
                System.out.println("|\t" + size + "\t|\t" + putAverage + "\t|\t" + getAverage + "\t|");
                size /= 2;
            }
            System.out.println("================================================================");

        }
    }
}


