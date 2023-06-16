package cognizant.restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;



public class Getrequest {
	
	
	@Test
	public void getrequest() {
		
		//RestAssured.get("https://reqres.in/api/users?page=2");
		baseURI="https://reqres.in/";
		Response response=RestAssured.get("api/users?page=2");
		System.out.println(response.statusCode());
		System.out.println(response.getTime());
		System.out.println(response.getStatusLine());
		System.out.println(response.getBody().asString());
		System.out.println(response.getHeader("server"));
		System.out.println(response.getHeader("Content-Type"));
		
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode,200);
		
	}
	
	@Test
	public void getrequest2() {
		baseURI="https://reqres.in/";
        given().log().all().
        when().get("api/users?page=2").
		then().assertThat().statusCode(200).body("total",equalTo(12)).body("data[2].last_name",equalTo("Funke")).log().all();
	}

}
