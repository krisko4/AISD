package pl.edu.pw.ee;

import pl.edu.pw.ee.services.MinSpanningTree;

public class KruskalAlgorithm implements MinSpanningTree {

    @Override
    public String findMST(String pathToFile) {
        StringBuilder mstBuilder = new StringBuilder();
        Graph graph = new Graph(pathToFile);
        if (graph.isEmpty()) return mstBuilder.toString();
        Heap<Edge> priorityQueue = new Heap<>();
        int setQuantity = graph.size();
        int[] sets = new int[setQuantity];
        addEdgesToPriorityQueue(graph, sets, priorityQueue);
        while (setQuantity > 1 && !priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.pop();
            Vertex sourceVertex = graph.get(edge.getFrom());
            Vertex targetVertex = graph.get(edge.getTo());
            if (sourceVertex.getSetIndex() == targetVertex.getSetIndex()) {
                continue;
            }
            if (setQuantity < graph.size()) {
                mstBuilder.append("|");
            }
            mstBuilder.append(edge.getFrom()).append("_").append(edge.getWeight()).append("_").append(edge.getTo());
            setQuantity--;
            union(sets, sourceVertex, targetVertex);
        }
        if (setQuantity > 1) {
            throw new IllegalStateException("Graph is inconsistent!");
        }
        return mstBuilder.toString();
    }

    private void addEdgesToPriorityQueue(Graph graph, int[] sets, Heap<Edge> priorityQueue) {
        int index = 0;
        for (Vertex vertex : graph.getGraphNodes()) {
            sets[index] = 1;
            vertex.specifySetIndex(index);
            index++;
            for (Edge edge : vertex.getEdges()) {
                if (!edge.isMST) priorityQueue.put(edge);
                edge.isMST = true;
            }
        }
    }

    private void union(int[] sets, Vertex sourceVertex, Vertex targetVertex) {
        int sourceSetIndex = sourceVertex.getSetIndex();
        int targetSetIndex = targetVertex.getSetIndex();
        if (sets[sourceSetIndex] >= sets[targetSetIndex]) {
            sets[sourceSetIndex] += sets[targetSetIndex];
            targetVertex.specifySetIndex(sourceSetIndex);
            return;
        }
        sets[targetSetIndex] += sets[sourceSetIndex];
        sourceVertex.specifySetIndex(targetSetIndex);
    }


}
