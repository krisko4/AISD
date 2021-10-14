import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;


public class BinarySearchTest {
    
    private Searcher searcher;

    @Before
    public void setUp(){searcher = new BinarySearch();}


    @Test
    public void shouldWorkOnSingleElementArray(){

        //given
        double[] nums = {10};

        //when
        int result = searcher.search(nums, 10);

        //then
        assertEquals(result, 0);


    }

    @Test
    public void shouldWorkOnTwoElementArray(){

        //given
        double[] nums = {10, 25};

        //when
        int result1 = searcher.search(nums, 25);
        int result2 = searcher.search(nums, 10);

        //then
        assertEquals(result1, 1);
        assertEquals(result2, 0);
    }

    @Test
    public void shouldWorkOnOddArray(){

        //given
        double[] nums = {10, 25, 37};

        //when
        int result1 = searcher.search(nums, 10);
        int result2 = searcher.search(nums, 25);
        int result3 = searcher.search(nums, 37);

        //then
        assertEquals(result1, 0);
        assertEquals(result2, 1);
        assertEquals(result3, 2);

    }

    @Test
    public void shouldWorkOnEvenArray(){

        //given
        double[] nums = {10, 25, 37, 76};

        //when
        int result1 = searcher.search(nums, 10);
        int result2 = searcher.search(nums, 25);
        int result3 = searcher.search(nums, 37);
        int result4 = searcher.search(nums, 76);

        //then
        assertEquals(result1, 0);
        assertEquals(result2, 1);
        assertEquals(result3, 2);
        assertEquals(result4, 3);

    }

    @Test
    public void shouldWorkOnToFindGreaterThanLastIndex(){

        //given
        double[] nums = {10, 25, 37, 76};

        //when
        int result = searcher.search(nums, 1500);

        //then
        assertEquals(result, -1);
    }

    @Test
    public void shouldWorkOnToFindEqualsLastIndex(){

        //given
        double[] nums = {10, 25, 37, 76};

        //when
        int result = searcher.search(nums, 1500);

        //then
        assertEquals(result, -1);

    }

    @Test
    public void shouldWorkOnToFindSmallerThanFirstIndex(){

        //given
        double[] nums = {10, 25, 37, 76};

        //when
        int result = searcher.search(nums, 7);

        //then
        assertEquals(result, -1);
    }



}
