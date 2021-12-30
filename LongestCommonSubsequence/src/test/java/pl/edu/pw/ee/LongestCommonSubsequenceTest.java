package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class LongestCommonSubsequenceTest {
    public LongestCommonSubsequence lcs;

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfFirstConstructorParamIsNull() {
        // given
        String topStr  = null;
        String leftStr = "test";

        // when 
        lcs = new LongestCommonSubsequence(topStr, leftStr);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfSecondConstructorParamIsNull() {
        // given
        String  topStr = "test";
        String leftStr = null;

        // when 
        lcs = new LongestCommonSubsequence(topStr, leftStr);

        // then
        assert false;
    }

    @Test
    public void shouldCreateTableCellArray(){
        // given
        String topStr = "często_z_odkrywaniem";
        String leftStr = "rzeczy_nie_trzeba\n_się_spieszyć";

        // when
        lcs = new LongestCommonSubsequence(topStr, leftStr);

        // then
        assertNotNull(lcs.getTableCellArray());
        int expectedTableCellCount = (topStr.length() + 1) * (leftStr.length() + 1);
        int tableCellCount = lcs.getTableCellArray().length * lcs.getTableCellArray()[0].length;
        assertEquals(tableCellCount, expectedTableCellCount);

    }


    @Test
    public void shouldFindLongestCommonSubsequence() {
        // given
        String topStr = "często_z_odkrywaniem";
        String leftStr = "rzeczy_nie_trzeba\n_się_spieszyć";

        // when
        lcs = new LongestCommonSubsequence(topStr, leftStr);
        String lcsString = lcs.findLCS();

        // then
        String expectedResult = "cz__raie";
        assertEquals(lcsString, expectedResult);
    }

    @Test
    public void shouldDisplay() {
        // given
        String topStr = "często_z_odkrywaniem";
        String leftStr = "rzeczy_nie_trzeba\n_się_spieszyć";

        // when 
        lcs = new LongestCommonSubsequence(topStr, leftStr);
        lcs.display();

        // then
        assert true;

    }
}
