package org.tsi.leigh.demoTest;

public class TestTools
{
    public static String buildString(int length)
    {
        String testString = "";
        for(int i = 0;i < length;i++)
        {
            testString+="A";
        }
        return testString;
    }
}
