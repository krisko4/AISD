package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Graph {

    private final HashTable<Vertex> graphNodes;

    public HashTable<Vertex> getGraphNodes() {
        return graphNodes;
    }

    public int size() {
        return graphNodes.size();
    }

    public Vertex getFirstVertex() {
        return graphNodes.getFirst();
    }

    public Vertex get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Vertex key cannot be null");
        }
        return graphNodes.get(new Vertex(key));
    }

    public boolean isEmpty() {
        return graphNodes != null && graphNodes.size() == 0;
    }

    public Graph(String pathToFile) {
        if(pathToFile == null){
            throw new IllegalArgumentException("File path cannot be null");
        }
        graphNodes = new HashDoubleHashing<>();
        buildGraph(pathToFile);
    }


    private void buildGraph(String pathToFile) {
        try {
            File file = new File(pathToFile);
            Scanner scanner = new Scanner(file);
            int counter = 0;
            while (scanner.hasNextLine()) {
                counter++;
                String line = scanner.nextLine();
                Matcher matcher = validateInputLine(line, counter);
                addNewEdge(matcher, counter);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found");
        }
    }

    private Matcher validateInputLine(String line, int counter) {
        Pattern pattern = Pattern.compile("^([a-zA-Z]+) ([a-zA-Z]+) ([0-9]+)$");
        Matcher matcher = pattern.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalStateException("Invalid graph edge found in the input file: " + line + ". Line: " + counter + ". Correct edge example: A B 1");
        }
        return matcher;
    }

    private void addNewEdge(Matcher matcher, int counter) {
        String from = matcher.group(1);
        String to = matcher.group(2);
        int weight = Integer.parseInt(matcher.group(3));
        Edge edge = new Edge(from, to, weight);
        Vertex targetVertex = graphNodes.get(new Vertex(to));
        Vertex sourceVertex = graphNodes.get(new Vertex(from));
        addVertex(targetVertex, edge, to, counter);

        addVertex(sourceVertex, edge, from, counter);
    }

    private void addVertex(Vertex vertex, Edge edge, String key, int counter) {
        if (vertex == null) {
            vertex = new Vertex(key);
            vertex.put(edge);
            graphNodes.put(vertex);
            return;
        }
        if (vertex.hasEdgeWithKeys(edge.getFrom(), edge.getTo())) {
            throw new IllegalStateException("Duplicate edge creation attempt." +
                    " You are trying to create the following edge:" +
                    " " + edge.getFrom() + " " + edge.getTo() + " " + edge.getWeight() +
                    ", but the graph already contains the connection between nodes " + edge.getFrom() + " and " + edge.getTo() + ". Line: " + counter);
        }
        vertex.put(edge);
    }




}
