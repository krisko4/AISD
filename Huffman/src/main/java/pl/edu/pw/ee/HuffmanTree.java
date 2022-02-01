package pl.edu.pw.ee;

public class HuffmanTree {

    public HuffmanNode getRoot() {
        return root;
    }

    private HuffmanNode root;

    public void put(HuffmanNode node) {
        if (node == null) {
            throw new IllegalArgumentException("Cannot put null element to Huffman tree");
        }
        if (root == null) {
            if(node instanceof HuffmanLeaf){
                throw new IllegalArgumentException("Cannot put HuffmanLeaf as HuffmanTree root");
            }
            root = node;
            return;
        }
        HuffmanNode freeNode = findAvailableNode(root);
        if (freeNode == null) {
            throw new IllegalStateException("Huffman tree has no available nodes");
        }
        if (freeNode.getLeft() == null) {
            freeNode.setLeft(node);
            return;
        }
        freeNode.setRight(node);

    }

    private HuffmanNode findAvailableNode(HuffmanNode node) {
        if (isLeaf(node.getLeft()) && isLeaf(node.getRight())) {
            return null;
        }
        HuffmanNode leftNode = null;
        if (isLeftChildNode(node)) {
            leftNode = findAvailableNode(node.getLeft());
        }
        if (leftNode == null && isRightChildNode(node)) {
            return findAvailableNode(node.getRight());
        }
        if (leftNode == null && isLeaf(node.getRight())) {
            return null;
        }
        if (leftNode == null) {
            return node;
        }
        return leftNode;
    }

    private boolean isLeaf(HuffmanNode node) {
        return node instanceof HuffmanLeaf;
    }

    private boolean isLeftChildNode(HuffmanNode node) {
        return node.getLeft() != null && !(node.getLeft() instanceof HuffmanLeaf);
    }

    private boolean isRightChildNode(HuffmanNode node) {
        return node.getRight() != null && !(node.getRight() instanceof HuffmanLeaf);
    }

}
