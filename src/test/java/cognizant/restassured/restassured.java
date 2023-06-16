package cognizant.restassured;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;


public class restassured {
	 
	@Test
	public void postmethod() {
		
		//adding place in google maps using post method using restassured
		RestAssured.baseURI="https://rahulshettyacademy.com";
		given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").
		body(" {\r\n"
				+ "    \"location\": {\r\n"
				+ "        \"lat\": -38.383494,\r\n"
				+ "        \"lng\": 33.427362\r\n"
				+ "    },\r\n"
				+ "    \"accuracy\": 50,\r\n"
				+ "    \"name\": \"Ganeshpogula\",\r\n"
				+ "    \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "    \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "    \"types\": [\r\n"
				+ "        \"shoe park\",\r\n"
				+ "        \"shop\"\r\n"
				+ "    ],\r\n"
				+ "    \"website\": \"http://rahulshettyacademy.com\",\r\n"
				+ "    \"language\": \"French-IN\"\r\n"
				+ "}" ). 
		when().post("maps/api/place/add/json").then().assertThat().statusCode(200).body("scope",equalTo("APP")).
		body("status",equalTo("OK")).header("Server","Apache/2.4.41 (Ubuntu)").log().all();
			
	}

}
