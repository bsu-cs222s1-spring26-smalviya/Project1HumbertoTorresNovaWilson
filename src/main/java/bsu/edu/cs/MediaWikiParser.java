package bsu.edu.cs;

import com.jayway.jsonpath.JsonPath;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MediaWikiParser {
    public String parseForTimeStamp(InputStream testDataStream,int position) throws IOException, JSONException {
        List<Object> result = JsonPath.read(testDataStream, "$..timestamp");
        return result.get(position).toString();
    }
}
