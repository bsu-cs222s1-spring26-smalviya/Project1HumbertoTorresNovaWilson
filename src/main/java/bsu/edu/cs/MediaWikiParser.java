package bsu.edu.cs;

import com.jayway.jsonpath.JsonPath;
import org.json.JSONException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class MediaWikiParser {
    public boolean checkRedirections;

    public List<Object> parseForTimeStamp(String json) throws IOException, JSONException {
        InputStream inputStream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        List<Object> result = JsonPath.read(inputStream, "$..timestamp");
        return result;
    }

    public List<Object> parseForUsernames(String json) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        List<Object> result = JsonPath.read(inputStream,"$..user");
        return result;
    }

    public boolean checkRedirections(String json){
        try{
            InputStream inputStream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
            List<Object> result = JsonPath.read(inputStream,"$.query.redirects[*]");
            return !result.isEmpty();
        } catch (com.jayway.jsonpath.PathNotFoundException e){
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getRedirection(String json) throws IOException {
        try {
            String redirectedTo = JsonPath.read(json, "$.query.redirects[0].to");
            return "Redirected to " + redirectedTo;
        } catch (com.jayway.jsonpath.PathNotFoundException e) {
            return null;
        }
    }

//    public int getNumberOfRevisions(String json) throws IOException {
//        InputStream inputStream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
//        List<Object> timestamps = JsonPath.read(inputStream, "$..timestamp");
//        return timestamps.size();
//    }

//    public String correctTimeFormatter(String time){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd hh:mm a");
//        Instant instant = Instant.parse(time);
//        ZonedDateTime localTime = instant.atZone(ZoneId.systemDefault());
//        return localTime.format(formatter);
//    }
}
