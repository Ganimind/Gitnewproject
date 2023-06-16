package cognizant.restassured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class files {
	
	@Test
	public void post() throws IOException {
		
		
		//readallbytes method converts our file data into bytes 
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response =given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\LENOVO\\OneDrive\\Desktop\\request.json")))).
		when().post("maps/api/place/add/json").then().assertThat().statusCode(200).body("scope",equalTo("APP")).
		body("status",equalTo("OK")).header("Server","Apache/2.4.41 (Ubuntu)").extract().response().asString();
			
		System.out.println(response);	
		JsonPath json=new JsonPath(response);
		String placeid=json.getString("place_id");
		System.out.println(placeid);

	}

}
