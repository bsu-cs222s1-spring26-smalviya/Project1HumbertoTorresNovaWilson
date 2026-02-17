package bsu.edu.cs;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Scanner;

public class MediaWikiReader {
    //comment
    public static void main(String[] args) {
        MediaWikiReader revisionReader = new MediaWikiReader();
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        try{
            String timestamp = revisionReader.getLatestRevisionOf(line);
            System.out.println(timestamp);
        } catch (IOException ioException){
            System.err.println("Network connection problem: " + ioException.getMessage());
        }
    }

    private String getLatestRevisionOf(String articleTitle) throws IOException{
        String urlString = String.format("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=Zappa&rvprop=timestamp|user&rvlimit=4&redirects",
                articleTitle);
        String encodedUrlString = URLEncoder.encode(urlString, Charset.defaultCharset());
        try {
            return getConnection(encodedUrlString);
        } catch (MalformedURLException malformedURLException){
            throw new RuntimeException(malformedURLException);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public String getConnection(String encodedUrlString) throws IOException, JSONException {
        URL url = new URL(encodedUrlString);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent",
                "MediaWikiReader; HumbertoTorres(humberto.torres@bsu.edu)");
        InputStream inputStream = connection.getInputStream();
        MediaWikiParser parser = new MediaWikiParser();
        //Should return timeStamp
        return parser.parse(inputStream);
    }
}
