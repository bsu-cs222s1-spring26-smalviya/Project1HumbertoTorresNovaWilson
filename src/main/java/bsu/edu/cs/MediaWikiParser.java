package bsu.edu.cs;

import java.io.InputStream;

public class MediaWikiParser {
    public String parse(InputStream testDataStream) {
        JsonPath.read(testDataStream, "$... timestamp");
    }
}
