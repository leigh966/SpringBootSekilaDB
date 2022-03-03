package org.tsi.leigh.demoTest;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.tsi.leigh.demo.Film;

public class FilmTest
{
    final int MAX_TITLE_LENGTH = 60;
    @Test
    public void testSetTitleLowerCase()
    {
        Film f = new Film();
        f.setTitle("blah");
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
        Assertions.assertThrows(Exception.class, ()->{f.setTitle("");}, "Blank title should be rejected");
    }

    @Test
    public void testSetTitleUpperBoundOkay()
    {
        Film f = new Film();
        String testString = TestTools.buildString(MAX_TITLE_LENGTH);
        f.setTitle(testString);
        Assertions.assertEquals(testString, f.getTitle(), "Title of max length (but not over) should be accepted");
    }

}
