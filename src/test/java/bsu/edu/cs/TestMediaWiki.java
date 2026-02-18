package bsu.edu.cs;

import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class TestMediaWiki {
    @Test
    public void testTimeStamp() throws IOException, JSONException {
        MediaWikiParser parser = new MediaWikiParser();
        InputStream testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");
        String timeStamp = parser.parseForTimeStamp(testDataStream,0);
        Assertions.assertEquals("2025-08-13T22:47:03Z",timeStamp);
    }
    @Test
    public void testNetworkConnection() throws JSONException, IOException {
        MediaWikiReader reader = new MediaWikiReader();
        String encodedTitle = URLEncoder.encode("Frank Zappa", java.nio.charset.StandardCharsets.UTF_8);
        String urlString = String.format(
                "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=%s&rvprop=timestamp|user&rvlimit=4&redirects",
                encodedTitle
        );
        String result = reader.getConnection(urlString);
        Assertions.assertTrue(result.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z"));
    }
    @Test
    public void testGettingURLString(){
        MediaWikiReader reader = new MediaWikiReader();
        String result = reader.getURLString("Zappa");
        Assertions.assertEquals("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=Zappa&rvprop=timestamp|user&rvlimit=4&redirects", result);
    }
    @Test
    public void testTimeConverter(){
        MediaWikiParser parser = new MediaWikiParser();
        //We are passing through the time for the wikipedia article for Ernsanchez00
        String result = parser.correctTimeFormatter("2025-08-13T22:47:03Z");
        Assertions.assertEquals("2025 08 13 06:47 PM", result);
    }
}