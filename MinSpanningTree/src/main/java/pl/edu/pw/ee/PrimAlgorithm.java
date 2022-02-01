package pl.edu.pw.ee;

import pl.edu.pw.ee.services.MinSpanningTree;


public class PrimAlgorithm implements MinSpanningTree {


    public String findMST(String pathToFile) {
        StringBuilder mstBuilder = new StringBuilder();
        Graph graph = new Graph(pathToFile);
        if (graph.isEmpty()) return mstBuilder.toString();
        Heap<Edge> priorityQueue = new Heap<>();
        Vertex vertex = graph.getFirstVertex();
        vertex.isMST = true;
        addEdgesToPriorityQueue(vertex, priorityQueue);
        int mstSize = 1;
        while (!priorityQueue.isEmpty() && mstSize < graph.size()) {
            Edge edge = priorityQueue.pop();
            Vertex nonMSTVertex = getNonMSTVertex(edge, graph);
            if (nonMSTVertex == null) {
                continue;
            }
            nonMSTVertex.isMST = true;
            if (mstSize > 1) mstBuilder.append("|");
            mstBuilder.append(edge.getFrom()).append("_").append(edge.getWeight()).append("_").append(edge.getTo());
            mstSize++;
            addEdgesToPriorityQueue(nonMSTVertex, priorityQueue);
        }
        if (mstSize < graph.size()) {
            throw new IllegalStateException("Graph is inconsistent!");
        }
        return mstBuilder.toString();
    }


    private void addEdgesToPriorityQueue(Vertex vertex, Heap<Edge> priorityQueue) {
        for (Edge edge : vertex.getEdges()) {
            if (!edge.isMST) {
                priorityQueue.put(edge);
                edge.isMST = true;
            }
        }
    }

    private Vertex getNonMSTVertex(Edge edge, Graph graph) {
        Vertex sourceVertex = graph.get(edge.getFrom());
        Vertex targetVertex = graph.get(edge.getTo());
        if (sourceVertex.isMST && targetVertex.isMST) {
            return null;
        }
        return sourceVertex.isMST ? targetVertex : sourceVertex;

    }


}
