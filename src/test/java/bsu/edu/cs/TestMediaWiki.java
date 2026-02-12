package bsu.edu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestMediaWiki {
    @Test
    public void testParse() {
        MediaWikiParser parser = new MediaWikiParser();
        InputStream testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");
        String timeStamp = parser.parse(testDataStream);
        Assertions.assertEquals("2025-08-13T22:46:33Z",timeStamp);
    }
}
