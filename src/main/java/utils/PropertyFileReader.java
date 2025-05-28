package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {

    FileInputStream fis;
    Properties pro;

    public PropertyFileReader(){

        FileInputStream fis= null;
        try {
            fis = new FileInputStream("C:\\ApiTestEndToEnd\\TestNew\\config.properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Failed to load property file: " +fis,e);
        }

        pro= new Properties();
        try {
            pro.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //pro.getProperty(key);
    }
    public  String get(String key) {
        return pro.getProperty(key);
    }

}
