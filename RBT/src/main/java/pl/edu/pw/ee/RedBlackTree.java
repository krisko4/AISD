package pl.edu.pw.ee;

import static pl.edu.pw.ee.Color.BLACK;
import static pl.edu.pw.ee.Color.RED;

public class RedBlackTree<K extends Comparable<K>, V> {

    private Node<K, V> root;
    private int reqCount;

    public int getReqCount() {
        return reqCount;
    }

    public Node<K, V> getLastElem() {
        if (root == null) return null;
        Node<K, V> node = root;
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node;
    }

    public Node<K, V> getRoot() {
        return root;
    }

    public V get(K key) {
        validateKey(key);
        Node<K, V> node = root;

        V result = null;

        while (node != null) {

            if (shouldCheckOnTheLeft(key, node)) {
                node = node.getLeft();

            } else if (shouldCheckOnTheRight(key, node)) {
                node = node.getRight();

            } else {
                result = node.getValue();
                break;
            }
        }
        return result;
    }

    public void put(K key, V value) {
        reqCount = 0;
        validateParams(key, value);
        root = put(root, key, value);
        root.setColor(BLACK);
    }

    private void validateKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
    }

    public String getPreOrder() {
        return preOrder(root);
    }

    public String getInOrder() {
        return inOrder(root);

    }

    public String getPostOrder() {
        return postOrder(root);
    }

    private String inOrder(Node<K, V> node) {
        String value = "";
        if (node == null) {
            return value;
        }
        value += inOrder(node.getLeft());
        value += node.getKey().toString() + ":" + node.getValue().toString() + " ";
        value += inOrder(node.getRight());
        return value;
    }

    private String postOrder(Node<K, V> node) {
        String value = "";
        if (node == null) {
            return value;
        }
        value += postOrder(node.getLeft());
        value += postOrder(node.getRight());
        value += node.getKey().toString() + ":" + node.getValue().toString() + " ";
        return value;
    }

    private String preOrder(Node<K, V> node) {
        String value = "";
        if (node == null) {
            return value;
        }
        value = node.getKey().toString() + ":" + node.getValue().toString() + " ";
        value += preOrder(node.getLeft());
        value += preOrder(node.getRight());
        return value;
    }

    public void deleteMax() {
        if (root == null) {
            throw new IllegalStateException("Cannot delete element from empty tree.");
        }
        root = deleteMax(root);
    }

    private Node<K, V> deleteMax(Node<K, V> node) {
        if (isLeftChildColorRed(node)) {
            node = rotateRight(node);
        }
        if (node.getRight() == null) {
            return null;
        }
        if (!isLeftChildColorRed(node.getRight()) && !isRightChildColorRed(node)) {
            reverseChangeColors(node);
            if (isLeftChildColorRed(node.getLeft())) {
                node = rotateRight(node);
                reverseChangeColors(node);
            }
        }
        node.setRight(deleteMax(node.getRight()));
        return reorganizeTree(node);
    }


    private boolean isRightChildColorRed(Node<K, V> node) {
        return node.getRight() != null && node.getRight().getColor() == RED;
    }

    private boolean isLeftChildColorRed(Node<K, V> node) {
        return node.getLeft() != null && node.getLeft().getColor() == RED;
    }

    private boolean shouldCheckOnTheLeft(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private boolean shouldCheckOnTheRight(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private void validateParams(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Input params (key, value) cannot be null.");
        }
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null) {
            return new Node<>(key, value);
        }
        if (isKeyBiggerThanNode(key, node)) {
            putOnTheRight(node, key, value);

        } else if (isKeySmallerThanNode(key, node)) {
            putOnTheLeft(node, key, value);

        } else {
            node.setValue(value);
        }

        node = reorganizeTree(node);

        return node;
    }

    private boolean isKeyBiggerThanNode(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private void putOnTheRight(Node<K, V> node, K key, V value) {
        reqCount++;
        Node<K, V> rightChild = put(node.getRight(), key, value);
        node.setRight(rightChild);
    }

    private boolean isKeySmallerThanNode(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private void putOnTheLeft(Node<K, V> node, K key, V value) {
        reqCount++;
        Node<K, V> leftChild = put(node.getLeft(), key, value);
        node.setLeft(leftChild);
    }

    private Node<K, V> reorganizeTree(Node<K, V> node) {
        node = rotateLeftIfNeeded(node);
        node = rotateRightIfNeeded(node);
        changeColorsIfNeeded(node);

        return node;
    }

    private Node<K, V> rotateLeftIfNeeded(Node<K, V> node) {
        if (isBlack(node.getLeft()) && isRed(node.getRight())) {
            node = rotateLeft(node);
        }
        return node;
    }

    private Node<K, V> rotateLeft(Node<K, V> node) {
        Node<K, V> head = node.getRight();
        node.setRight(head.getLeft());
        head.setLeft(node);
        head.setColor(node.getColor());
        node.setColor(RED);

        return head;
    }

    private Node<K, V> rotateRightIfNeeded(Node<K, V> node) {
        if (isRed(node.getLeft()) && isRed(node.getLeft().getLeft())) {
            node = rotateRight(node);
        }
        return node;
    }

    private Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> head = node.getLeft();
        node.setLeft(head.getRight());
        head.setRight(node);
        head.setColor(node.getColor());
        node.setColor(RED);
        return head;
    }

    private void changeColorsIfNeeded(Node<K, V> node) {
        if (isRed(node.getLeft()) && isRed(node.getRight())) {
            changeColors(node);
        }
    }

    private void changeColors(Node<K, V> node) {
        node.setColor(RED);
        node.getLeft().setColor(BLACK);
        node.getRight().setColor(BLACK);
    }

    private void reverseChangeColors(Node<K, V> node) {
        node.setColor(BLACK);
        node.getLeft().setColor(RED);
        node.getRight().setColor(RED);
    }

    private boolean isBlack(Node<K, V> node) {
        return !isRed(node);
    }

    private boolean isRed(Node<K, V> node) {
        return node != null && node.isRed();
    }
}
