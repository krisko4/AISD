package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RedBlackTreePerformanceTest {

    public RedBlackTree<String, Integer> redBlackTree;
    private final List<String> words = new ArrayList<>();

    @Before
    public void setUp() {
        redBlackTree = new RedBlackTree<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("words.txt"));
            String line = br.readLine();
            while (line != null) {
                for (int i = 1; i < 11; i++) {
                    words.add(line + "_" + i);
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to load file");
        }
    }


    public List<Integer> countRecursion() {
        List<Integer> recursionCountList = new ArrayList<>();
        redBlackTree = new RedBlackTree<>();
        Integer value = 5;
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            redBlackTree.put(word, value);
            if (i % 32 == 0) {
                recursionCountList.add(redBlackTree.getReqCount());
            }
        }
        return recursionCountList;
    }

    public void saveResultsToFile(List<Integer> results, String fileName) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
        for (Integer result : results) {
            pw.println(result);
        }
        pw.close();
    }


    @Test
    public void shouldTestRecursionCount() throws IOException {
        List<Integer> list1 = countRecursion();
        Collections.reverse(words);
        List<Integer> list2 = countRecursion();
        Collections.shuffle(words);
        List<Integer> list3 = countRecursion();

        saveResultsToFile(list1, "ordered.txt");
        saveResultsToFile(list2, "reverse_ordered.txt");
        saveResultsToFile(list3, "randomized.txt");


    }


}
