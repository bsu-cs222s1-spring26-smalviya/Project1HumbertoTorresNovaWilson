package bsu.edu.cs;

import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class TestMediaWiki {
    @Test
    public void testTimeStamp() throws IOException, JSONException {
        MediaWikiParser parser = new MediaWikiParser();
        InputStream jsonStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");
        String testDataStream= new String(jsonStream.readAllBytes(), StandardCharsets.UTF_8);
        List<Object> timeStamp = parser.parseForTimeStamp(testDataStream);
        Assertions.assertEquals("2026-02-18T18:44:39Z",timeStamp.get(0).toString());
    }
    @Test
    public void testNetworkConnection() throws JSONException, IOException {
        MediaWikiReader reader = new MediaWikiReader();
        InputStream json = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");
        String expectedResult= new String(json.readAllBytes(), StandardCharsets.UTF_8);
        String result = reader.getConnection("Zappa");
        Assertions.assertEquals(expectedResult.trim(),result.trim());
    }
    @Test
    public void testGettingURLString(){
        MediaWikiReader reader = new MediaWikiReader();
        String result = reader.getURLString("Zappa");
        Assertions.assertEquals("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=Zappa&rvprop=timestamp|user&rvlimit=15&redirects", result);
    }
    @Test
    public void testUsernames() throws IOException {
        MediaWikiParser parser = new MediaWikiParser();
        InputStream jsonStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");
        String testDataStream= new String(jsonStream.readAllBytes(), StandardCharsets.UTF_8);
        List<Object> userNames = parser.parseForUsernames(testDataStream);
        Assertions.assertEquals("UndergroundMan3000", userNames.get(0).toString());

    }

    @Test
    public void testCheckRedirections() throws IOException {
        MediaWikiParser parser = new MediaWikiParser();
        InputStream jsonStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");
        String testDataStream= new String(jsonStream.readAllBytes(), StandardCharsets.UTF_8);
        Assertions.assertTrue(parser.checkRedirections(testDataStream));
    }

    @Test
    public void testGetRedirection() throws IOException {
        MediaWikiParser parser = new MediaWikiParser();
        InputStream jsonStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");
        String testDataStream= new String(jsonStream.readAllBytes(), StandardCharsets.UTF_8);
        Assertions.assertEquals("Redirected to Frank Zappa",parser.getRedirection(testDataStream));
    }
}