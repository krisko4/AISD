package pl.edu.pw.ee;

import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VertexTest {

    public Vertex vertex;


    public Edge generateRandomEdge() {
        String from = "ABC";
        String to = "DEF";
        int weight = 10;
        return new Edge(from, to, weight);
    }


    @Test
    public void shouldCreateVertex() {
        //given
        String key = "key";

        //when
        vertex = new Vertex(key);

        //then
        assertNotNull(vertex);
        assertEquals(vertex.getKey(), key);
        assertNotNull(vertex.getEdges());
        assertEquals(vertex.getEdges().size(), 0);
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNullVertexKey() {
        //given
        String key = null;

        //when
        vertex = new Vertex(key);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenPuttingNullEdge() {
        //given
        Edge edge = null;

        //when
        vertex = new Vertex("any");
        vertex.put(edge);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNullCompareToParam() {
        //given
        vertex = new Vertex("any");
        Vertex other = null;

        //when
        vertex.compareTo(other);

        //then
        assert false;

    }

    @Test
    public void shouldCompareCorrectly() {
        //given
        String firstKey = "key1";
        String secondKey = "key2";
        Vertex t1 = new Vertex(firstKey);
        Vertex t2 = new Vertex(secondKey);

        //when
        int result = t1.compareTo(t2);

        //then
        int expected = firstKey.compareTo(secondKey);
        assertEquals(result, expected);

    }


    @Test
    public void shouldPutNewEdgeToEdgeList() {
        //given
        Edge edge = generateRandomEdge();

        //when
        Vertex node = new Vertex("ABC");
        node.put(edge);

        //then
        List<Edge> edgeList = node.getEdges();
        assertEquals(edgeList.size(), 1);
        assertEquals(edgeList.get(0), edge);

    }

    @Test
    public void shouldCheckIfHasEdgeWithKeys() {
        //given
        String from = "ABC";
        String to = "DEF";
        int weight = 10;
        Edge edge = new Edge(from, to, weight);

        //when
        Vertex node = new Vertex("ABC");
        node.put(edge);

        //then
        assertTrue(node.hasEdgeWithKeys(from, to));
        assertFalse(node.hasEdgeWithKeys("random1", "random2"));

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenFirstHasEdgeWithKeysParamIsNull() {
        //given
        String from = null;
        String to = "any";

        //when
        Vertex node = new Vertex("ABC");
        node.hasEdgeWithKeys(from, to);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenSecondHasEdgeWithKeysParamIsNull() {
        //given
        String from = "any";
        String to = null;

        //when
        Vertex node = new Vertex("ABC");
        node.hasEdgeWithKeys(from, to);

        //then
        assert false;
    }

    @Test
    public void shouldCalculateValidHashCode() {
        //given
        String key = "any";

        //when
        Vertex node = new Vertex(key);
        int hashCode = node.hashCode();

        //then
        assertEquals(hashCode, key.hashCode());
    }


}
