package bsu.edu.cs;

import com.jayway.jsonpath.JsonPath;
import org.json.JSONException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MediaWikiParser {
    public boolean checkRedirections;

    //Finds the time stamp in a json string and returns a json list
    public List<Object> parseForTimeStamp(String json) throws IOException, JSONException {
        InputStream inputStream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        List<Object> result = JsonPath.read(inputStream, "$..timestamp");
        return result;
    }

    //Finds the usernames in a json string and returns a json list of usernames
    public List<Object> parseForUsernames(String json) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        List<Object> result = JsonPath.read(inputStream,"$..user");
        return result;
    }

    //Checks if there are any redirections in the site
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

    //Gets the redirection name
    public String getRedirection(String json) throws IOException {
        try {
            String redirectedTo = JsonPath.read(json, "$.query.redirects[0].to");
            return "Redirected to " + redirectedTo;
        } catch (com.jayway.jsonpath.PathNotFoundException e) {
            return null;
        }
    }
}
