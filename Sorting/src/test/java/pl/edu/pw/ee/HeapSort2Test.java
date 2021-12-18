package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.services.Sorting;
import java.util.Arrays;
import static org.junit.Assert.assertArrayEquals;

public class HeapSort2Test {

    private Sorting sorting;

    @Before
    public void setUp(){sorting = new HeapSort2();}

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNullArray(){

        //when
        sorting.sort(null);
    }

    @Test
    public void shouldSortUnorderedData() {

        // given
        double[] nums = { 30, 5, 13, 8, 22, 21, 17 };

        // when
        sorting.sort(nums);

//        // then
        double[] expected = {5, 8, 13, 17, 21, 22, 30};
        System.out.println(Arrays.toString(nums));
        assertArrayEquals(nums, expected, 0);
    }
}
