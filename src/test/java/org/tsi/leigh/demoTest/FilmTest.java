package org.tsi.leigh.demoTest;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.tsi.leigh.demo.Film;
import org.tsi.leigh.demo.NotSavedException;

public class FilmTest
{
    final int MAX_TITLE_LENGTH = 60;
    @Test
    public void testSetTitleLowerCase()
    {
        Film f = new Film();
        try {
                f.setTitle("blah");
    }catch (NotSavedException nse)
    {
        Assertions.fail("Title should save successfully");
    }
        Assertions.assertEquals("BLAH", f.getTitle(), "Title should be added as uppercase");
    }

    @Test
    public void testSetTitleLowerBoundUnder()
    {
        Film f = new Film();
        Assertions.assertThrows(Exception.class, ()->{f.setTitle("");}, "Blank title should be rejected");
    }

    @Test
    public void testSetTitleUpperBoundOver()
    {
        Film f = new Film();
        String testString = TestTools.buildString(MAX_TITLE_LENGTH+1);
        Assertions.assertThrows(Exception.class, ()->{f.setTitle(testString);}, "Long title should be rejected");
    }

    @Test
    public void testSetTitleUpperBoundOkay()
    {
        Film f = new Film();
        String testString = TestTools.buildString(MAX_TITLE_LENGTH);
        try {
            f.setTitle(testString);
        }catch (NotSavedException nse)
        {
            Assertions.fail("Title should save successfully");
        }
        Assertions.assertEquals(testString, f.getTitle(), "Title of max length (but not over) should be accepted");
    }

}
