package bsu.edu.cs;

import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class TestMediaWiki {
    @Test
    public void testParse() throws IOException, JSONException {
        MediaWikiParser parser = new MediaWikiParser();
        InputStream testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");
        String timeStamp = parser.parse(testDataStream);
        Assertions.assertEquals("2025-08-13T22:47:03Z",timeStamp);
    }
}
