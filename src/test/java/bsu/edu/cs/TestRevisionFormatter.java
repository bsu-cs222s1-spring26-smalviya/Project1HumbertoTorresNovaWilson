package bsu.edu.cs;

import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class TestRevisionFormatter {
    @Test
    public void testFormatRevisions() throws Exception {
        String testJson = readResource("test.json");
        String expected = readResource("expectedFormat.txt");

        String result = RevisionFormatter.formatRevisions(testJson);

        Assertions.assertEquals(normalize(expected), normalize(result));
    }


    @Test
    public void testTimeConverter(){
        RevisionFormatter formatter = new RevisionFormatter();
        //We are passing through the time for the wikipedia article for Ernsanchez00
        String result = formatter.correctTimeFormatter("2025-08-13T22:47:03Z");
        Assertions.assertEquals("2025 08 13 06:47 PM", result);
    }

    private static String readResource(String name) throws IOException {
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(name)) {
            Assertions.assertNotNull(is, name + " not found");
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
    private static String normalize(String s) {
        return s.replace("\r\n", "\n").trim();
    }
}
