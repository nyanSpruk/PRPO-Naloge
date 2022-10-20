package com.skupina7.naloga;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;
import java.net.URL;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.io.File;
import java.util.Map;

/*
 * Testing servlet features
 */
public class PrviJdbcServletTest
{
    /**
     * Test if servlet is reachable at http://localhost:8080/servlet
     */
    @Test
    public void testHttpResponseStatus() throws Exception 
    {
        URL url = new URL("http://localhost:8080/servlet");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        int responseCode = con.getResponseCode();
        assertEquals(responseCode, HttpURLConnection.HTTP_OK);
    }

    // TODO
    // @Test
    // public void testHttpOutput() throws Exception
    // {
    //     URL url = new URL("http://localhost:8080/servlet");
    //     HttpURLConnection con = (HttpURLConnection) url.openConnection();
    //     int responseCode = con.getResponseCode();

    //     if (responseCode == HttpURLConnection.HTTP_OK) {

    //         BufferedReader in = new BufferedReader(new java.io.InputStreamReader(con.getInputStream()));
    //         StringBuffer response = new StringBuffer();
    //         String inputLine;
    //         while ((inputLine = in.readLine()) != null) {
    //             response.append(inputLine);
    //         }
    //         in.close();

    //         Yaml config = new Yaml();
    //         URL filePath = getClass().getResource("config.yml");
    //         InputStream configFile = new FileInputStream(new File(filePath.getPath()));
    //         Map<String,Object> configMap = config.load(configFile);

    //         String responseString = response.toString(); 
    //         assertTrue(responseString.contains("Hello fellow java champion!"));
    //         assertTrue(responseString.contains("service.name: " + configMap.get("service.name")));
    //         assertTrue(responseString.contains("service.version: " + configMap.get("service_version")));
    //         assertTrue(responseString.contains("service.environment.name: " + configMap.get("environment_name")));
    //     }
    //     else {
    //         assertTrue(false);
    //     }
    // }
}
