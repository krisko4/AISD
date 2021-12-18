package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class LongestCommonSubsequenceTest 
{
    public LongestCommonSubsequence lcs;

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfFirstConstructorParamIsNull(){
        //given
        String firstString = null;
        String secondString = "test";

        //when
        lcs = new LongestCommonSubsequence(firstString, secondString);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfSecondConstructorParamIsNull(){
        //given
        String firstString = "test";
        String secondString = null;

        //when
        lcs = new LongestCommonSubsequence(firstString, secondString);

        //then
        assert false;
    }

    @Test
    public void shouldFindLongestCommonSubsequence(){
        //given
        String firstString = "KOMPUTER";
        String secondString = "MAPPER ";

        //when
        lcs = new LongestCommonSubsequence(firstString, secondString);
        String lcsString = lcs.findLCS();

        //then
        String expected = "MPER";
        assertEquals(lcsString, expected);
    }

    @Test
    public void shouldDisplay(){
        //given
        String firstString = "TAJLAND";
        String secondString = "KURCZAK";


        //when
        lcs = new LongestCommonSubsequence(firstString, secondString);
        lcs.display();

    }
}
