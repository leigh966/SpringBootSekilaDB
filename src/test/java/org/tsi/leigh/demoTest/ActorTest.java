package org.tsi.leigh.demoTest;

import io.cucumber.java.bs.A;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.tsi.leigh.demo.Actor;

public class ActorTest
{
    @Test
    public void testSetFirstNameLower()
    {
        Actor a = new Actor();
        a.setFirst_name("blah");
        Assertions.assertEquals("BLAH", a.getFirst_name(), "First name has not been set to the uppercase of what was provided");
    }

    @Test
    public void testSetFirstNameBlank()
    {
        Actor a = new Actor();
        Assertions.assertThrows(Exception.class, ()->{a.setFirst_name("");});
    }


    public String buildString(int length)
    {
        String testString = "";
        for(int i = 0;i < length;i++)
        {
            testString+="A";
        }
        return testString;
    }

    final int FIRST_NAME_LENGTH_LIMIT = 20;
    @Test
    public void testSetFirstNameLengthUpperOver()
    {
        Actor a = new Actor();

        String testString = buildString(FIRST_NAME_LENGTH_LIMIT+1);

        Assertions.assertThrows(Exception.class, ()->{a.setFirst_name(testString);}, "First name should be rejected as it is too long");
    }
    @Test
    public void testSetFirstNameLengthUpperOkay()
    {
        Actor a = new Actor();

        String testString = buildString(FIRST_NAME_LENGTH_LIMIT);

        a.setFirst_name(testString);

        Assertions.assertEquals(testString, a.getFirst_name(), "First name should update as first_name is within the length limit");

    }

    @Test
    public void testSetLastNameLower()
    {
        Actor a = new Actor();
        a.setLast_name("blah");
        Assertions.assertEquals("BLAH", a.getLast_name(), "Last name has not been set to the uppercase of what was provided");
    }

    @Test
    public void testSetLastNameBlank()
    {
        Actor a = new Actor();
        Assertions.assertThrows(Exception.class, ()->{a.setLast_name("");});
    }



    final int LAST_NAME_LENGTH_LIMIT = 30;
    @Test
    public void testSetLastNameLengthUpperOver()
    {
        Actor a = new Actor();

        String testString = buildString(LAST_NAME_LENGTH_LIMIT+1);

        Assertions.assertThrows(Exception.class, ()->{a.setLast_name(testString);}, "Last name should be rejected as it is too long");
    }
    @Test
    public void testSetLastNameLengthUpperOkay()
    {
        Actor a = new Actor();

        String testString = buildString(LAST_NAME_LENGTH_LIMIT);

        a.setLast_name(testString);

        Assertions.assertEquals(testString, a.getLast_name(), "First name should update as first_name is within the length limit");

    }
}
