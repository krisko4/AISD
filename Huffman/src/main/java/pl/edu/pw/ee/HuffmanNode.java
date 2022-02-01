package pl.edu.pw.ee;


public class HuffmanNode implements Comparable<HuffmanNode> {


    private int frequency;
    private HuffmanNode left;
    private HuffmanNode right;


    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }

    public HuffmanNode() {
    }

    public int getFrequency() {
        return frequency;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public HuffmanNode(int frequency) {
        this.frequency = frequency;
    }


    public HuffmanNode(HuffmanNode left, HuffmanNode right) {
        this.frequency = left.getFrequency() + right.getFrequency();
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(HuffmanNode node) {
        return Integer.compare(frequency, node.getFrequency());
    }
}
