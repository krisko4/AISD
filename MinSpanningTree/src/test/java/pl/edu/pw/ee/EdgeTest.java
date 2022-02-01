package pl.edu.pw.ee;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EdgeTest {

    public Edge edge;


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnLoopedEdge() {
        //given
        String key = "A";
        int weight = 10;

        //when
        edge = new Edge(key, key, weight);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfFromIsNull() {
        //given
        String from = null;
        String to = "A";
        int weight = 10;

        //when
        edge = new Edge(from, to, weight);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfToIsNull() {
        //given
        String from = "A";
        String to = null;
        int weight = 10;

        //when
        edge = new Edge(from, to, weight);

        //then
        assert false;
    }

    @Test
    public void shouldCreateNewEdge() {
        //given
        String from = "A";
        String to = "B";
        int weight = 10;

        //when
        edge = new Edge(from, to, weight);

        //then
        assertNotNull(edge);
        assertEquals(edge.getTo(), to);
        assertEquals(edge.getFrom(), from);
        assertEquals(edge.getWeight(), weight);
        assertFalse(edge.isMST);
    }

    @Test
    public void shouldGetFrom() {
        //given
        String from = "A";
        String to = "B";
        int weight = 10;
        edge = new Edge(from, to, weight);

        //when
        String result = edge.getFrom();

        //then
        assertEquals(result, from);

    }

    @Test
    public void shouldGetTo() {
        //given
        String from = "A";
        String to = "B";
        int weight = 10;
        edge = new Edge(from, to, weight);

        //when
        String result = edge.getTo();

        //then
        assertEquals(result, to);

    }

    @Test
    public void shouldGetWeight() {
        //given
        String from = "A";
        String to = "B";
        int weight = 10;
        edge = new Edge(from, to, weight);

        //when
        int result = edge.getWeight();

        //then
        assertEquals(result, weight);

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNullCompareToParam() {
        //given
        edge = new Edge("A", "B", 10);
        Edge otherEdge = null;

        //when
        edge.compareTo(otherEdge);

        //then
        assert false;

    }

    @Test
    public void shouldReturn0WhenComparingEdgesWithEqualWeights() {
        //given
        edge = new Edge("A", "B", 10);
        Edge otherEdge = edge;

        //when
        int result = edge.compareTo(otherEdge);

        //then
        assertEquals(result, 0);

    }

    @Test
    public void shouldCompareWeights() {
        //given
        Integer firstWeight = 10;
        Integer secondWeight = 8;
        edge = new Edge("A", "B", firstWeight);
        Edge otherEdge = new Edge("C", "D", secondWeight);

        //when
        int result = edge.compareTo(otherEdge);
        int secondResult = otherEdge.compareTo(edge);

        //then
        assertEquals(result, firstWeight.compareTo(secondWeight));
        assertEquals(secondResult, secondWeight.compareTo(firstWeight));
    }


}
