package utils;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonReader {

    public static String getTestData(String Filepath,String input) throws IOException, ParseException {
        //String testData;
        return (String)readJsonFromFile(Filepath).get(input);
    }
    public static JSONObject readJsonFromFile(String filePath) throws IOException, ParseException {
        File file= new File(filePath);
       // convert the file into string
        String json=FileUtils.readFileToString(file,"UTF-8");
        //parse the string into object
        JSONParser js= new JSONParser();
        JSONObject obj= (JSONObject) js.parse(json);
       /*Object obj= new JSONParser().parse(json);
       JSONObject jsonobj=(JSONObject) obj;*/
        return obj;
    }
}
