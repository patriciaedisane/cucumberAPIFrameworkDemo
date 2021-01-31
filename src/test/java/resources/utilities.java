package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class utilities {
    public static RequestSpecification req;
    public RequestSpecification requestSpecificationBuild() throws IOException {


        if (req ==null) {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            PrintStream log = new PrintStream(new FileOutputStream("API_logfile_" + timeStamp + ".txt"));

            testDataBuild data = new testDataBuild();
            // Save the request to a variable
            req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURI")).addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
            return req;
        }
        else {
            return req;
        }
        //System.out.println("hi" +res);
    }

    // Method to load an red property file to get global values
    public String getGlobalValue(String value) throws IOException {

        Properties prop = new Properties();
        FileInputStream file = new FileInputStream("/Users/patriciaedisane/CucumberAPIFramework/src/test/java/resources/global.properties");
        prop.load(file);
        String globalValue = prop.getProperty(value);
        return globalValue;
    }

    public String getJsonPath(Response response, String key) {
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        return js.get(key).toString();
    }
}
