package pl.edu.pw.ee;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class DeterministicFiniteAutomatonTextSearchTest {

    public DeterministicFiniteAutomatonTextSearch ts;

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfPatternIsNull(){
        //given
        String pattern = null;

        //when
        ts = new DeterministicFiniteAutomatonTextSearch(pattern);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfFindAllArgumentIsNull(){
        //given
        String pattern = "ABC";
        String text = null;

        //when
        ts = new DeterministicFiniteAutomatonTextSearch(pattern);
        ts.findAll(text);

        //then
        assert false;

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfFindFirstArgumentIsNull(){
        //given
        String pattern = "ABC";
        String text = null;

        //when
        ts = new DeterministicFiniteAutomatonTextSearch(pattern);
        ts.findFirst(text);

        //then
        assert false;

    }

    @Test
    public void shouldAssignPatternCorrectly(){
        //given
        String pattern = "ABC";

        //when
        ts = new DeterministicFiniteAutomatonTextSearch(pattern);

        //then
        assertEquals(ts.getPattern(), pattern);
    }


    @Test
    public void shouldBuildValidTransitionalMatrix(){
        //given
        String pattern = "ABCD";

        //when
        ts = new DeterministicFiniteAutomatonTextSearch(pattern);

        //then
        char[] chars = pattern.toCharArray();
        int state = 0;
        for(char character : chars){
            assertTrue(ts.hasState(character, state++));
        }

    }

    @Test
    public void shouldBuildTransformationMatrix(){
        //given
        String pattern = "CCCC";

        //when
        ts = new DeterministicFiniteAutomatonTextSearch(pattern);
        int firstIndex = ts.findFirst("CCECCVCCCC");


        //then
        System.out.println(firstIndex);

    }

    @Test
    public void shouldFindAll(){
        //given
        String pattern = "ABC";
        String text = "ABCABCABC";

        //when
        ts = new DeterministicFiniteAutomatonTextSearch(pattern);
        int[] indexes = ts.findAll(text);

        //then
        int[] expected = {0,3,6};
        assertArrayEquals(expected, indexes);

    }

    @Test
    public void shouldReturnMinusOneWhenPatternIsEmptyString(){
        //given
        String pattern = "";
        String text = "ABC";

        //when
        ts = new DeterministicFiniteAutomatonTextSearch(pattern);
        int result = ts.findFirst(text);

        //then
        int expected = -1;
        assertEquals(result, expected);

    }

    @Test
    public void shouldReturnEmptyArrayWhenPatternIsEmptyString(){
        //given
        String pattern = "";
        String text = "ABC";

        //when
        ts = new DeterministicFiniteAutomatonTextSearch(pattern);
        int[] result = ts.findAll(text);

        //then
        int[] expected = {};
        assertArrayEquals(result, expected);

    }

    @Test
    public void shouldFindAllCorrectlyWhenMatchesAreOverlapped(){
        //given
        String pattern = "ABA";
        String text = "ABABABABAB";

        //when
        ts = new DeterministicFiniteAutomatonTextSearch(pattern);
        int[] indexes = ts.findAll(text);

        //then
        System.out.println(Arrays.toString(indexes));
        int[] expected = { 0, 2, 4, 6};
        assertArrayEquals(expected, indexes);

    }

    @Test
    public void shouldFindCorrectlyWhenTextEqualsPattern(){
        //given
        String pattern = "ABC";
        String text = pattern;

        //when
        ts = new DeterministicFiniteAutomatonTextSearch(pattern);
        int firstFind = ts.findFirst(text);
        int[] indexes = ts.findAll(text);

        //then
        int[] expected = {0};
        int expectedFirst = 0;
        assertArrayEquals(expected, indexes);
        assertEquals(firstFind, expectedFirst);

    }

    @Test
    public void shouldFindCorrectlyWhenTextStartsWithPattern(){
        //given
        String pattern = "CCC";
        String text = "CCCEGH";

        //when
        ts = new DeterministicFiniteAutomatonTextSearch(pattern);
        int firstFind = ts.findFirst(text);
        int[] indexes = ts.findAll(text);

        //then
        int[] expected = {0};
        int expectedFirst = 0;
        assertArrayEquals(expected, indexes);
        assertEquals(firstFind, expectedFirst);

    }

    @Test
    public void shouldNotFindIfPatternIsNotFullyMatched(){
        //given
        String pattern = "CCCB";
        String text = "ABCDEFCCC";

        //when
        ts = new DeterministicFiniteAutomatonTextSearch(pattern);
        int firstFind = ts.findFirst(text);
        int[] indexes = ts.findAll(text);

        int[] expected = {};
        int expectedFirst = -1;
        assertArrayEquals(expected, indexes);
        assertEquals(firstFind, expectedFirst);

    }

    @Test
    public void test(){
        //given
        String pattern = "AAA";
        String text = "AAAAA";

        //when
        ts = new DeterministicFiniteAutomatonTextSearch(pattern);
        int firstFind = ts.findFirst(text);
        int[] indexes = ts.findAll(text);

        System.out.println(firstFind);
        System.out.println(Arrays.toString(indexes));
    }

    @Test
    public void shouldFindCorrectlyWhenPatternIsInTheMiddle(){
        //given
        String pattern = "CCC";
        String text = "ABCDCCCADGH";

        //when
        ts = new DeterministicFiniteAutomatonTextSearch(pattern);
        int firstFind = ts.findFirst(text);
        int[] indexes = ts.findAll(text);

        //then
        int[] expected = {4};
        int expectedFirst = 4;
        assertArrayEquals(expected, indexes);
        assertEquals(firstFind, expectedFirst);

    }

    @Test
    public void shouldFindCorrectlyWhenTextEndsWithPattern(){
        //given
        String pattern = "CCC";
        String text = "ABGFGHCCC";

        //when
        ts = new DeterministicFiniteAutomatonTextSearch(pattern);
        int firstFind = ts.findFirst(text);

        //then
        int expectedFirst = 6;
        assertEquals(firstFind, expectedFirst);

    }




}
