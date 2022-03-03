package org.tsi.leigh.demo;

public class NotSavedException extends Exception
{
    NotSavedException(String message)
    {
        super("Not Saved: "+message);
    }
}
