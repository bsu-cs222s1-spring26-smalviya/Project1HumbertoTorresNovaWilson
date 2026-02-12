package bsu.edu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestWikiJournal {
    @Test
    public void testPromptUser(){
        WikiJournal wikiJournal = new WikiJournal();
        String result = wikiJournal.promptUser();
        Assertions.assertTrue(result.equals("Please Enter a link here: "));
    }
}
