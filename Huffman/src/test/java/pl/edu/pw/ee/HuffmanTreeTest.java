package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HuffmanTreeTest {

    private HuffmanTree huffmanTree;

    @Before
    public void setUp(){
        huffmanTree = new HuffmanTree();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNullNodePut(){
        //given
        HuffmanNode node = null;

        //when
        huffmanTree.put(node);

        //then
        assert false;
    }

    @Test
    public void shouldCreateRootIfTreeIsEmpty(){
        //given
        HuffmanNode node = new HuffmanNode();

        //when
        huffmanTree.put(node);

        //then
        assertEquals(huffmanTree.getRoot(), node);
    }

    @Test
    public void shouldPutNewNodeToTheLeftIfPossible(){
        //given
        HuffmanNode firstNode = new HuffmanNode();
        HuffmanNode secondNode = new HuffmanNode();

        //when
        huffmanTree.put(firstNode);
        huffmanTree.put(secondNode);

        //then
        assertEquals(huffmanTree.getRoot(), firstNode);
        assertEquals(huffmanTree.getRoot().getLeft(), secondNode);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionIfTreeHasNoAvailableNodes(){
        //given
        HuffmanNode root = new HuffmanNode();
        HuffmanNode firstLeaf = new HuffmanLeaf('c');
        HuffmanNode secondLeaf = new HuffmanLeaf('d');
        HuffmanNode node = new HuffmanNode();

        //when
        huffmanTree.put(root);
        huffmanTree.put(firstLeaf);
        huffmanTree.put(secondLeaf);
        huffmanTree.put(node);

        //then
        assert false;

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfPuttingLeafAsRoot(){
        //given
        HuffmanLeaf leaf = new HuffmanLeaf('c');

        //when
        huffmanTree.put(leaf);

        //then
        assert false;
    }

    @Test
    public void shouldPutNewElementToTheRightIfLeftSideIsFulfilled(){
        //given
        HuffmanNode node = new HuffmanNode();
        HuffmanNode leftNode = new HuffmanNode();
        HuffmanLeaf firstLeaf = new HuffmanLeaf('c');
        HuffmanLeaf secondLeaf = new HuffmanLeaf('d');
        HuffmanNode rightNode = new HuffmanNode();


        //when
        huffmanTree.put(node);
        huffmanTree.put(leftNode);
        huffmanTree.put(firstLeaf);
        huffmanTree.put(secondLeaf);
        huffmanTree.put(rightNode);

        //then
        HuffmanNode root = huffmanTree.getRoot();
        HuffmanNode left = root.getLeft();
        HuffmanNode right = root.getRight();
        assertEquals(root, node);
        assertEquals(left, leftNode);
        assertEquals(left.getLeft(), firstLeaf);
        assertEquals(left.getRight(), secondLeaf);
        assertEquals(root.getRight(), right);

    }






}
