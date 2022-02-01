package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class RedBlackTreeTest {

    public RedBlackTree<String, Integer> redBlackTree;

    @Before
    public void setUp() {
        redBlackTree = new RedBlackTree<>();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNullValuePut() {
        //given
        String key = "key";
        Integer value = null;

        //when
        redBlackTree.put(key, value);

        //then
        assert false;
    }


    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionOnEmptyTreeDelete() {
        //when
        redBlackTree.deleteMax();

        //then
        assert false;
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNullKeyPut() {
        //given
        String key = null;
        Integer value = 5;

        //when
        redBlackTree.put(key, value);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNullKeyGet() {
        //given
        String key = null;

        //when
        redBlackTree.get(key);

        //then
        assert false;
    }


    @Test
    public void shouldReturnNullOnNotFoundValueGet() {
        //given
        String key1 = "key";
        Integer value1 = 5;
        String key2 = "key2";

        //when
        redBlackTree.put(key1, value1);
        Integer value = redBlackTree.get(key2);

        //then
        assertNull(value);
    }

    @Test
    public void shouldReturnNullOnGetFromEmptyTree() {
        //given
        String key = "key";

        //when
        Integer value = redBlackTree.get(key);

        //then
        assertNull(value);
    }

    @Test
    public void shouldGetValueFromSingleKeyTreeCorrectly() {
        //given
        String key = "key";
        Integer value = 5;

        //when
        redBlackTree.put(key, value);
        Integer result = redBlackTree.get(key);

        //then
        assertEquals(result, value);

    }

    @Test
    public void shouldGetValueFromSmallerKeyCorrectly() {
        //given
        String key1 = "B";
        Integer value1 = 5;
        String key2 = "A";
        Integer value2 = 10;

        //when
        redBlackTree.put(key1, value1);
        redBlackTree.put(key2, value2);
        Integer result = redBlackTree.get(key2);

        //then
        assertEquals(result, value2);

    }


    @Test
    public void shouldGetValueFromBiggerKeyCorrectly() {
        //given
        String key1 = "B";
        Integer value1 = 5;
        String key2 = "A";
        Integer value2 = 10;
        String key3 = "C";
        Integer value3 = 15;

        //when
        redBlackTree.put(key1, value1);
        redBlackTree.put(key2, value2);
        redBlackTree.put(key3, value3);

        Integer result = redBlackTree.get(key3);

        //then
        assertEquals(result, value3);

    }


    @Test
    public void shouldCreateRootIfNoRootAndSetColorToBlack() {
        //given
        String key = "key";
        Integer value = 5;

        //when
        redBlackTree.put(key, value);

        //then
        assertEquals(redBlackTree.getRoot().getColor(), Color.BLACK);
        assertNotNull(redBlackTree.getRoot());
        assertEquals(redBlackTree.getRoot().getKey(), key);
        assertEquals(redBlackTree.getRoot().getValue(), value);
    }


    @Test
    public void shouldReplaceValueWhenPuttingSameKey() {
        //given
        String key = "key";
        Integer value1 = 5;
        Integer value2 = 10;

        //when
        redBlackTree.put(key, value1);
        redBlackTree.put(key, value2);

        //then
        assertEquals(redBlackTree.getRoot().getValue(), value2);
        assertNull(redBlackTree.getRoot().getLeft());
        assertNull(redBlackTree.getRoot().getRight());
    }

    @Test
    public void shouldRotateWhenPuttingBiggerElement() {
        //given
        String key1 = "A";
        Integer value = 5;
        String key2 = "L";

        //when
        redBlackTree.put(key1, value);
        redBlackTree.put(key2, value);

        //then
        assertEquals(redBlackTree.getRoot().getKey(), key2);
        assertEquals(redBlackTree.getRoot().getLeft().getKey(), key1);

    }

    @Test
    public void shouldPutSmallerKeyAsLeftChild() {
        //given
        String key1 = "L";
        Integer value = 5;
        String key2 = "A";

        //when
        redBlackTree.put(key1, value);
        redBlackTree.put(key2, value);

        //then
        assertEquals(redBlackTree.getRoot().getKey(), key1);
        assertEquals(redBlackTree.getRoot().getLeft().getKey(), key2);

    }


    @Test
    public void shouldSetRootAsNullIfAllElementsAreDeleted() {
        //given
        String word = "ALGORYTM";
        String[] keys = word.split("");
        Integer value = 5;

        //when
        for (String key : keys) {
            redBlackTree.put(key, value);
        }
        for (String key : keys) {
            redBlackTree.deleteMax();
        }

        //then
        assertNull(redBlackTree.getRoot());
    }


    @Test
    public void shouldRotateRightCorrectlyOnThreeElementsExample() {
        //given
        String key1 = "L";
        Integer value = 5;
        String key2 = "B";
        String key4 = "A";

        //when
        redBlackTree.put(key1, value);
        redBlackTree.put(key2, value);
        redBlackTree.put(key4, value);

        //then
        assertEquals(redBlackTree.getRoot().getKey(), key2);
        assertEquals(redBlackTree.getRoot().getLeft().getKey(), key4);
        assertEquals(redBlackTree.getRoot().getRight().getKey(), key1);
        assertEquals(redBlackTree.getRoot().getColor(), Color.BLACK);
        assertEquals(redBlackTree.getRoot().getLeft().getColor(), Color.BLACK);
        assertEquals(redBlackTree.getRoot().getRight().getColor(), Color.BLACK);

    }


    @Test
    public void shouldReturnEmptyStringOnEmptyTreePreOrder() {
        //when
        String result = redBlackTree.getPreOrder();

        //then
        String expected = "";
        assertEquals(expected, result);

    }

    @Test
    public void shouldReturnEmptyStringOnEmptyTreeInOrder() {
        //when
        String result = redBlackTree.getInOrder();

        //then
        String expected = "";
        assertEquals(expected, result);

    }

    @Test
    public void shouldReturnNullOnEmptyTreeLastElemGet() {
        //when
        Node<String, Integer> node = redBlackTree.getLastElem();

        //then
        assertNull(node);
    }


    @Test
    public void shouldReturnEmptyStringOnEmptyTreePostOrder() {
        //when
        String result = redBlackTree.getPostOrder();

        //then
        String expected = "";
        assertEquals(expected, result);

    }


    @Test
    public void shouldInOrderCorrectly() {
        //given
        String word = "ALGORYTM";
        String[] keys = word.split("");
        Integer value = 5;

        //when
        for (String key : keys) {
            redBlackTree.put(key, value);
        }
        String result = redBlackTree.getInOrder();

        //then
        String expected = "A:5 G:5 L:5 M:5 O:5 R:5 T:5 Y:5 ";
        System.out.println(result);
        assertEquals(result, expected);

    }


    @Test
    public void shouldDeleteRootFromSingleElementTree() {
        //given
        String key = "key";
        Integer value = 5;

        //when
        redBlackTree.put(key, value);
        redBlackTree.deleteMax();

        //then
        assertNull(redBlackTree.getRoot());
    }


    @Test
    public void shouldDeleteMaxElementFromTwoElementTree() {
        //given
        String key1 = "A";
        String key2 = "L";
        Integer value = 5;

        //when
        redBlackTree.put(key1, value);
        redBlackTree.put(key2, value);
        redBlackTree.deleteMax();

        //then
        assertEquals(redBlackTree.getRoot().getKey(), key1);
        assertNull(redBlackTree.getRoot().getLeft());
        assertNull(redBlackTree.getRoot().getRight());

    }

    @Test
    public void shouldDeleteMaxElement() {
        //given
        String word = "ALGORYTM";
        String[] keys = word.split("");
        Integer value = 5;

        //when
        for (String key : keys) {
            redBlackTree.put(key, value);
        }
        redBlackTree.deleteMax();

        //then
        String expectedLastKey = "T";
        assertEquals(redBlackTree.getLastElem().getKey(), expectedLastKey);
        assertEquals(redBlackTree.getLastElem().getColor(), Color.BLACK);
        assertNull(redBlackTree.getLastElem().getRight());
    }

    @Test
    public void shouldPostOrderCorrectly() {
        //given
        String word = "ALGORYTM";
        String[] keys = word.split("");
        Integer value = 5;

        //when
        for (String key : keys) {
            redBlackTree.put(key, value);
        }
        String result = redBlackTree.getPostOrder();

        //then
        String expected = "A:5 L:5 M:5 G:5 R:5 Y:5 T:5 O:5 ";
        System.out.println(result);
        assertEquals(result, expected);


    }

    @Test
    public void shouldPreOrderCorrectly() {
        //given
        String word = "ALGORYTM";
        String[] keys = word.split("");
        Integer value = 5;

        //when
        for (String key : keys) {
            redBlackTree.put(key, value);
        }
        String result = redBlackTree.getPreOrder();

        //then
        String expected = "O:5 G:5 A:5 M:5 L:5 T:5 R:5 Y:5 ";
        System.out.println(result);
        assertEquals(result, expected);


    }


    @Test
    public void shouldRotateRightCorrectlyWhenHeadHasRightChild() {
        //given
        String word = "EGZCFBD";
        String[] keys = word.split("");
        Integer value = 5;

        //when
        for (String key : keys) {
            redBlackTree.put(key, value);
        }

        //then
        Node<String, Integer> root = redBlackTree.getRoot();
        Node<String, Integer> right = root.getRight();
        Node<String, Integer> left = root.getLeft();
        assertEquals(root.getKey(), keys[0]);
        assertEquals(right.getKey(), keys[1]);
        assertEquals(left.getKey(), keys[3]);
        assertEquals(left.getLeft().getKey(), keys[5]);
        assertEquals(left.getRight().getKey(), keys[6]);
        assertEquals(right.getLeft().getKey(), keys[4]);
        assertEquals(right.getRight().getKey(), keys[2]);

    }


}
