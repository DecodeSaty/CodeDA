package pages.resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utilities {

	public static RequestSpecification req;

	public static String getGlobalValue(String key) throws IOException
	{
		Properties prop =new Properties();
		File file = new File(System.getProperty("user.dir")+"//src//test//java//pages//resources//global.properties");
		FileInputStream inputStream = new FileInputStream(file);
		prop.load(inputStream);
		return prop.getProperty(key);

	}

	public String getJsonPath(Response response,String key)
	{
		  String resp=response.asString();
		JsonPath   js = new JsonPath(resp);
		return js.get(key).toString();
	}
}
