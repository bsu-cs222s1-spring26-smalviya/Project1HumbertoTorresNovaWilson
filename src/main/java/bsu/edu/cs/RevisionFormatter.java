package bsu.edu.cs;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class RevisionFormatter {
    public String correctTimeFormatter(String time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd hh:mm a");
        Instant instant = Instant.parse(time);
        ZonedDateTime localTime = instant.atZone(ZoneId.systemDefault());
        return localTime.format(formatter);
    }
}
