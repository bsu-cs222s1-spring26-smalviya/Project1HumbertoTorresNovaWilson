package bsu.edu.cs;

import org.json.JSONException;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RevisionFormatter {
    private static MediaWikiParser parser = new MediaWikiParser();

    public static String formatRevisions(String JsonString) throws IOException, JSONException {
        String formattedString = new String();
        if(parser.checkRedirections(JsonString)){
            formattedString = formattedString.concat(parser.getRedirection(JsonString)+"\n\n");
        }else{
            formattedString = formattedString.concat("No Redirections found\n\n");
        }
        List<Object> timeStamps = parser.parseForTimeStamp(JsonString);
        List<Object> usernames = parser.parseForUsernames(JsonString);

        for(int pivot = 0; pivot < timeStamps.size() && pivot < 15; pivot++){
            formattedString = formattedString.concat("Username: "+usernames.get(pivot).toString()+".\n");
            formattedString = formattedString.concat("Time edited: "+ correctTimeFormatter(timeStamps.get(pivot).toString())+".\n\n");
        }
        return formattedString;
    }
    public static String correctTimeFormatter(String time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd hh:mm a");
        Instant instant = Instant.parse(time);
        ZonedDateTime localTime = instant.atZone(ZoneId.systemDefault());
        return localTime.format(formatter);
    }
}
