package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileUtilsTest {

    public File file;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUp(){
        try {
          file = folder.newFile("file.txt");
        } catch (IOException ioe) {
            System.err.println(
                    "error creating temporary test file in " +
                            this.getClass().getSimpleName());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenGettingFileNameFromNullFile(){
        //given
        File file = null;

        //when
        FileUtils.getFileNameFromFile(file);

        //when
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenGeneratedOutputFileNameIsNull() throws IOException {
        //given
        String fileName = null;
        byte[] bytes = {50};

        //when
        FileUtils.generateOutputFile(fileName, bytes);

        //then
        assert false;
    }

    @Test
    public void shouldGetFileNameFromFile() {
        //when
        String fileName = FileUtils.getFileNameFromFile(file);

        //then
        String expected = "file";
        assertEquals(expected, fileName);

    }

}
