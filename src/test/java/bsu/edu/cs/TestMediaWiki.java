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
        String timeStamp = parser.parse(testDataStream);
        Assertions.assertEquals("2025-08-13T22:47:03Z",timeStamp);
    }
    @Test
    public void testNetworkConnection(){
        MediaWikiReader reader = new MediaWikiReader();
        String urlString = String.format("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=Zappa&rvprop=timestamp|user&rvlimit=4&redirects",
                "Frank Zappa");
        String encodedUrlString = URLEncoder.encode(urlString, Charset.defaultCharset());
        String result = reader.getConnection(encodedUrlString);
        Assertions.assertEquals(result.equals("2025-08-13T22:47:03Z"));
    }
}
