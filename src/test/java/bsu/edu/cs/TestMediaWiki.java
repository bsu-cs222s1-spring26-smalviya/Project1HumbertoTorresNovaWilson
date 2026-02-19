package bsu.edu.cs;

import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class TestMediaWiki {
    @Test
    public void testTimeStamp() throws IOException, JSONException {
        MediaWikiParser parser = new MediaWikiParser();
        InputStream testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");
        String timeStamp = parser.parseForTimeStamp(testDataStream,0);
        Assertions.assertEquals("2026-02-18T18:44:39Z",timeStamp);
    }
    @Test
    public void testNetworkConnection() throws JSONException, IOException {
        MediaWikiReader reader = new MediaWikiReader();
        String encodedTitle = URLEncoder.encode("Zappa", java.nio.charset.StandardCharsets.UTF_8);
        String urlString = String.format(
                "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=%s&rvprop=timestamp|user&rvlimit=4&redirects",
                encodedTitle
        );
        InputStream json = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");
        String expectedResult= new String(json.readAllBytes(), StandardCharsets.UTF_8);
        String result = reader.getConnection(urlString);
        Assertions.assertEquals(expectedResult.trim(),result.trim());
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