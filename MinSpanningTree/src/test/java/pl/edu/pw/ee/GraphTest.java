package pl.edu.pw.ee;

import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.TemporaryFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class GraphTest {

    public Graph graph;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();


    public File generateFile(File file, String content) throws IOException {
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(content);
        bw.close();
        return file;
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenGettingNullVertex() throws IOException {
        //given
        String content = "A B 10\nC D 10";
        File file = generateFile(folder.newFile("test.txt"), content);
        String key = null;
        //when
        graph = new Graph(file.getAbsolutePath());
        graph.get(key);

        //then
        assert false;

    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfPathToFileIsNull() {
        //given
        String pathToFile = null;

        //when
        graph = new Graph(pathToFile);

        //then
        assert false;

    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionIfFileNotFound() {
        //given
        String pathToFile = "random";

        //when
        graph = new Graph(pathToFile);

        //then
        assert false;

    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionIfValidateInputLineFails() throws IOException {
        //given
        String content = "AB10";
        File file = generateFile(folder.newFile("test.txt"), content);

        //when
        graph = new Graph(file.getAbsolutePath());

        //then
        assert false;
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionIfInputFileContainsDuplicateEdge() throws IOException {
        //given
        String content = "A B 10\nA B 10";
        File file = generateFile(folder.newFile("test.txt"), content);

        //when
        graph = new Graph(file.getAbsolutePath());

        //then
        assert false;
    }

    @Test
    public void shouldTestIfGraphIsEmpty() throws IOException {
        //given
        String content = "";
        File file = generateFile(folder.newFile("test.txt"), content);

        //when
        graph = new Graph(file.getAbsolutePath());

        //then
        assertNotNull(graph.getGraphNodes());
        assertTrue(graph.isEmpty());
        assertNull(graph.getFirstVertex());
    }

    @Test
    public void shouldCreateSimpleGraph() throws IOException {
        //given
        String content = "A B 10\nC D 10";
        File file = generateFile(folder.newFile("test.txt"), content);

        //when
        graph = new Graph(file.getAbsolutePath());
        Vertex firstVertex = graph.getFirstVertex();
        //then
        assertNotNull(graph.getGraphNodes());
        assertFalse(graph.isEmpty());
        assertEquals(graph.size(), 4);
        assertEquals(graph.getFirstVertex(), firstVertex);
    }

    @Test
    public void shouldTestGettingVerticesFromGraph() throws IOException {
        //given
        String randomKey = "random";
        String presentKey = "A";
        String content = "A B 10\nC D 10";
        File file = generateFile(folder.newFile("test.txt"), content);

        //when
        graph = new Graph(file.getAbsolutePath());
        Vertex randomVertex = graph.get(randomKey);
        Vertex presentVertex = graph.get(presentKey);
        Vertex firstVertex = graph.getGraphNodes().getFirst();

        //then
        assertNull(randomVertex);
        assertNotNull(presentVertex);
        assertEquals(graph.getFirstVertex(), firstVertex);

    }


}
