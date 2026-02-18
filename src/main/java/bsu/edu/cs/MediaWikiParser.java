package bsu.edu.cs;

import com.jayway.jsonpath.JsonPath;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class MediaWikiParser {
    public String parseForTimeStamp(InputStream testDataStream,int position) throws IOException, JSONException {
        List<Object> result = JsonPath.read(testDataStream, "$..timestamp");
        return result.get(position).toString();
    }

    public String correctTimeFormatter(String time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd hh:mm a");
        Instant instant = Instant.parse(time);
        ZonedDateTime localTime = instant.atZone(ZoneId.systemDefault());
        return localTime.format(formatter);
    }
}
