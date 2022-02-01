package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.services.HashTable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


/**
 * HashTable performance tests
 */
public class HashTablePerformanceTest {

    private HashTable<String> hashTable;
    private final String[] words = new String[100000];

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

    /**
     * The test fills hashTable with 100 000 words
     * and calculates the time of getting all the words back from the hashTable. The calculation is repeated 30 times.
     * All the results are inserted into an array. The first 10 and last 10 results are ignored.
     * From the middle 10 results, an average is calculated and printed into console.
     * EXAMPLE TEST RESULTS:
     * 4096 - 56.52054 ms
     * 8192 - 32.38326 ms
     * 16384 - 22.76098 ms
     * 32768 - 10.57578 ms
     * 65536 - 7.55254 ms
     * 131072 - 5.70871 ms
     * 262144 - 4.34697 ms
     */
    @Test
    public void shouldTestHashTablePerformance() {

        int size = 4096;
        for (int i = 0; i < 7; i++) {
            hashTable = new HashListChaining<>(size);
            long[] times = new long[30];
            for (String word : words) {
                hashTable.add(word);
            }
            for (int j = 0; j < 30; j++) {
                long startTime = System.nanoTime();
                for (String word : words) {
                    hashTable.get(word);
                }
                long endTime = System.nanoTime();
                long diff = endTime - startTime;
                times[j] = diff;
            }
            Arrays.sort(times);
            long[] properTimes = new long[10];
            System.arraycopy(times, 9, properTimes, 0, 10);
            double avg = 0;
            System.out.println(Arrays.toString(properTimes));
            for (long properTime : properTimes) {
                avg += (double) (properTime / properTimes.length);
            }
            System.out.println(avg / 1000000);
            size *= 2;
        }

    }

}
