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
    public String parseForTimeStamp(String json,int position) throws IOException, JSONException {
        InputStream inputStream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        List<Object> result = JsonPath.read(inputStream, "$..timestamp");
        return result.get(position).toString();
    }

    public int getNumberOfRevisions(String json) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        List<Object> timestamps = JsonPath.read(inputStream, "$..timestamp");
        return timestamps.size();
    }

    public String correctTimeFormatter(String time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd hh:mm a");
        Instant instant = Instant.parse(time);
        ZonedDateTime localTime = instant.atZone(ZoneId.systemDefault());
        return localTime.format(formatter);
    }
}
