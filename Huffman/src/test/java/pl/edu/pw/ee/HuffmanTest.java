package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class HuffmanTest {

    public Huffman huffman;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUp() {
        huffman = new Huffman();
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfDirectoryIsEmpty() throws IOException {
        //given
        File emptyDirectory = folder.newFolder();
        //when
        huffman.huffman(emptyDirectory.getAbsolutePath(), true);
        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfPathToRootDirIsNull() throws IOException {
        //given
        String pathToRootDir = null;

        //when
        huffman.huffman(pathToRootDir, true);

        //then
        assert false;

    }

    @Test(expected = FileNotFoundException.class)
    public void shouldThrowExceptionIfNoDictionaryFoundForDecodedFile() throws IOException {
        //given
        File file = FileUtils.generateFile(folder.newFile("test.bin"), "test");
        //when
        huffman.huffman(file.getParentFile().getAbsolutePath(), false);
        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfEncodedFileContainsNonASCIISigns() throws IOException {
        //given
        File file = FileUtils.generateFile(folder.newFile("test.txt"), "treść");
        //when
        huffman.huffman(file.getParentFile().getAbsolutePath(), true);
        //then
        assert false;
    }

    @Test
    public void shouldReturnTheSameContentWhenDecodingEncodedFile() throws IOException {
        //given
        String content = "test";
        File file = FileUtils.generateFile(folder.newFile("text.txt"), content);

        //when
        huffman.huffman(file.getParentFile().getAbsolutePath(), true);
        huffman.huffman(file.getParentFile().getAbsolutePath(), false);

        //then
        File decodedFile = new File(file.getAbsolutePath());
        String decodedContent = Files.readString(Path.of(decodedFile.getAbsolutePath()));
        assertEquals(content, decodedContent);


    }

    @Test
    public void shouldGenerateEncodedFileAndDictionary() throws IOException {
        //given
        File file = FileUtils.generateFile(folder.newFile("test.txt"), "test");

        //when
        huffman.huffman(file.getParentFile().getAbsolutePath(), true);

        //then
        String directoryPath = file.getParentFile().getAbsolutePath();
        String encodedFilePath = directoryPath + FileUtils.getFileNameFromFile(file) + ".bin";
        File expectedBinaryFile = new File(encodedFilePath);
        String encodedDictionaryPath = directoryPath + FileUtils.getFileNameFromFile(file) + "_dict.bin";
        File expectedDictionaryFile = new File(encodedDictionaryPath);
        assertNotNull(expectedBinaryFile);
        assertNotNull(expectedDictionaryFile);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionIfLastByteOfDecodedFileIsInvalid() throws IOException {
        //given
        byte[] encodedFileBytes = {1, 1, 1, 16};
        File binaryFile = folder.newFile("test.bin");
        File dictionaryFile = folder.newFile("test_dict.bin");
        Files.write(Path.of(binaryFile.getAbsolutePath()), encodedFileBytes);
        Files.write(Path.of(dictionaryFile.getAbsolutePath()), encodedFileBytes);

        //when
        huffman.huffman(dictionaryFile.getParentFile().getAbsolutePath(), false);

        //then
        assert false;
    }


    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionWhenDecodingIncorrectlyEncodedFile() throws IOException {
        //given
        byte[] encodedFileBytes = {1, 1, 1, 1};
        File file = folder.newFile("test.bin");
        File dictionaryFile = folder.newFile("test_dict.bin");
        Files.write(Path.of(file.getAbsolutePath()), encodedFileBytes);
        Files.write(Path.of(dictionaryFile.getAbsolutePath()), encodedFileBytes);

        //when
        huffman.huffman(file.getParentFile().getAbsolutePath(), false);

        //then
        assert false;


    }

    @Test
    public void shouldDecodeFileCorrectlyIfEncodedFilesAreValid() throws IOException {
        //given
        String content = "niemanie";
        byte[] encodedFileBytes = {-58, 92, 64, 6};
        byte[] encodedDictionaryBytes = {45, 54, 82, -37, 97, -73, 0, 7};
        File file = folder.newFile("test.bin");
        File dictionaryFile = folder.newFile("test_dict.bin");
        Files.write(Path.of(file.getAbsolutePath()), encodedFileBytes);
        Files.write(Path.of(dictionaryFile.getAbsolutePath()), encodedDictionaryBytes);

        //when
        huffman.huffman(file.getParentFile().getAbsolutePath(), false);

        //then
        String directoryPath = file.getParentFile().getAbsolutePath();
        String decodedFilePath = directoryPath + "\\" + FileUtils.getFileNameFromFile(file) + ".txt";
        File expectedDecodedFile = new File(decodedFilePath);
        assertNotNull(expectedDecodedFile);
        String decodedFileContent = Files.readString(Path.of(expectedDecodedFile.getAbsolutePath()));
        assertEquals(decodedFileContent, content);


    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfFileIsEmpty() throws IOException {
        //given
        File file = FileUtils.generateFile(folder.newFile("test.txt"), "");

        //when
        huffman.huffman(file.getParentFile().getAbsolutePath(), true);

        //then
        assert false;
    }

    @Test
    public void shouldEncodeAndDecodeFilesFromLocalDirectory() throws IOException {
        //given
        String path = "files";

        //when
        int encodingResult = huffman.huffman(path, true);
        int decodingResult = huffman.huffman(path, false);

        //then
        int encodedDirectorySize = encodingResult / 8;
        double ratio = ((double) encodedDirectorySize / decodingResult) * 100;
        System.out.println("-----------------------------------------------------");
        System.out.println("Total number of bits in encoded files in directory: " + encodingResult);
        System.out.println("Total number of characters in decoded files in directory: " + decodingResult);
        System.out.println("Size of encoded directory: " + encodedDirectorySize + " B");
        System.out.println("Size of decoded directory: " + decodingResult + " B");
        System.out.println("Encoded directory size as percentage of decoded directory size: " + Math.round(ratio) + "%");


    }


}
