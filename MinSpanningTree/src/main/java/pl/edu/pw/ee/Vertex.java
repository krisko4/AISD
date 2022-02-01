package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.List;


public class Vertex implements Comparable<Vertex> {


    private final String key;
    private final List<Edge> edgeList;
    private int setIndex;
    public boolean isMST = false;


    public int getSetIndex() {
        return setIndex;
    }

    public String getKey() {
        return key;
    }

    public void specifySetIndex(int index) {
        setIndex = index;
    }

    public List<Edge> getEdges() {
        return edgeList;
    }

    public Vertex(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Vertex key cannot be null");
        }
        this.key = key;
        edgeList = new ArrayList<>();
    }

    public void put(Edge edge) {
        if (edge == null) throw new IllegalArgumentException("Unable to put null edge");
        edgeList.add(edge);
    }

    public boolean hasEdgeWithKeys(String from, String to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Provided parameters cannot be null");
        }
        for (Edge edge : edgeList) {
            if ((edge.getTo().equals(to) && edge.getFrom().equals(from)) || (edge.getFrom().equals(to) && edge.getTo().equals(from))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Vertex t) {
        if (t == null) {
            throw new IllegalArgumentException("Unable to compare TableNode with null object");
        }
        return key.compareTo(t.key);
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }


}
