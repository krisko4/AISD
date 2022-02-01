package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import pl.edu.pw.ee.services.MinSpanningTree;

public abstract class MSTTest {

    public MinSpanningTree mst;

    public MSTTest(MinSpanningTree mst) {
        this.mst = mst;
    }

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    public File generateFile(File file, String content) throws IOException {
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(content);
        bw.close();
        return file;
    }

    public String sortEdges(String mstString) {
        String[] edges = mstString.split("\\|");
        Arrays.sort(edges);
        return String.join("|", edges);

    }

    public void shouldFindMST(String content, String expected) throws IOException {
        //when
        File file = generateFile(folder.newFile("test.txt"), content);
        String mstString = mst.findMST(file.getAbsolutePath());

        //then
        mstString = sortEdges(mstString);
        assertEquals(mstString, expected);
        System.out.println(mstString);
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfPathToFileIsNull() {
        //given
        String pathToFile = null;

        //when
        mst.findMST(pathToFile);

        //then
        assert false;

    }

    @Test
    public void shouldReturnEmptyStringIfFileIsEmpty() throws IOException {
        //given
        String content = "";
        File file = generateFile(folder.newFile("test.txt"), content);

        //when
        String mstString = mst.findMST(file.getAbsolutePath());

        //then
        assertEquals(mstString, "");
    }


    @Test
    public void shouldFindMSTOnFirstCustomExample() throws IOException {
        //given
        String content = "A G 3\n" +
                "A B 7\n" +
                "B D 1\n" +
                "G D 2\n" +
                "D F 5\n" +
                "D C 1\n" +
                "C F 2\n" +
                "F E 3";
        String expected = "A_3_G|B_1_D|C_2_F|D_1_C|F_3_E|G_2_D";

        //when
        shouldFindMST(content, expected);

        //then
        assert true;

    }

    @Test
    public void shouldFindMSTOnSecondCustomExample() throws IOException {
        //given
        String content = "A G 5\n" +
                "G F 9\n" +
                "F C 20\n" +
                "C A 15\n" +
                "C H 12\n" +
                "C E 9\n" +
                "E H 11\n" +
                "E D 10\n" +
                "D B 15\n" +
                "B A 12\n" +
                "B H 12";
        String expected = "A_5_G|B_12_A|B_12_H|C_9_E|E_10_D|E_11_H|G_9_F";

        //when
        shouldFindMST(content, expected);

        //then
        assert true;
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionIfGraphIsInconsistent() throws IOException {
        //given
        String content = "A B 10\n" +
                "B C 15\n" +
                "C D 12\n" +
                "G F 8\n" +
                "F E 10";
        File file = generateFile(folder.newFile("test.txt"), content);

        //when
        mst.findMST(file.getAbsolutePath());

        //then
        assert false;

    }


    @Test
    public void shouldFindMSTOnThirdCustomExample() throws IOException {
        //given
        String content = "A B 3\n" +
                "A C 5\n" +
                "A D 7\n" +
                "B C 1\n" +
                "C D 1\n" +
                "D E 7";
        String expected = "A_3_B|B_1_C|C_1_D|D_7_E";

        //when
        shouldFindMST(content, expected);

        //then
        assert true;

    }

    @Test
    public void shouldTestExampleLocalValidFile() {
        //given
        String pathToFile = "correct_small_data.txt";

        //when
        String mstString = mst.findMST(pathToFile);

        //then
        System.out.println(mstString);
        assert true;
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionIfFileIsInvalid() {
        //given
        String pathToFile = "small_data.txt";

        //when
        mst.findMST(pathToFile);

        //then
        assert false;
    }
}
