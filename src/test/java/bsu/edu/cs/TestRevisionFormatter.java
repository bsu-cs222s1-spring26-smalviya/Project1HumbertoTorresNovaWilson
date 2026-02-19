package bsu.edu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestRevisionFormatter {
    @Test
    public void testTimeConverter(){
        RevisionFormatter formatter = new RevisionFormatter();
        //We are passing through the time for the wikipedia article for Ernsanchez00
        String result = formatter.correctTimeFormatter("2025-08-13T22:47:03Z");
        Assertions.assertEquals("2025 08 13 06:47 PM", result);
    }
}
