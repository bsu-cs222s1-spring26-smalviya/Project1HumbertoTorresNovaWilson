package bsu.edu.cs;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MediaWikiReader {

    private static MediaWikiParser parser = new MediaWikiParser();

    public static void runReader(String articleName) {
        MediaWikiReader revisionReader = new MediaWikiReader();
        try{
            String timestamp = revisionReader.getLatestRevisionOf(articleName);
            System.out.println(parser.correctTimeFormatter(timestamp));
        } catch (IOException ioException){
            System.err.println("Network connection problem: " + ioException.getMessage());
        }
    }

    public String getConnection(String urlString) throws IOException, JSONException {
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent",
                "MediaWikiReader; HumbertoTorres(humberto.torres@bsu.edu)");
        try (InputStream inputStream = connection.getInputStream()) {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    public String getURLString(String articleTitle){
        String encodedTitle = URLEncoder.encode(articleTitle, java.nio.charset.StandardCharsets.UTF_8);
        String urlString = String.format(
                "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=%s&rvprop=timestamp|user&rvlimit=4&redirects",
                encodedTitle);
        return urlString;
    }

    private String getLatestRevisionOf(String articleTitle) throws IOException {
        String urlString = getURLString(articleTitle);
        try {
            return getConnection(urlString);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }


}