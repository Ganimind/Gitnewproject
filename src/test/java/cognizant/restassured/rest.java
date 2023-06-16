package cognizant.restassured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class rest {
	String placeid;

	@Test(priority=1)
	public void post() {
		
		//adding place in google maps using postmethod 
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response =given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").
		body(payload.postrequestbody()).
		when().post("maps/api/place/add/json").then().assertThat().statusCode(200).body("scope",equalTo("APP")).
		body("status",equalTo("OK")).header("Server","Apache/2.4.41 (Ubuntu)").extract().response().asString();
			
		System.out.println(response);	
		JsonPath json=new JsonPath(response);
		placeid=json.getString("place_id");
		System.out.println(placeid);
	}
	
	@Test(priority=2)
	public void update() {
		//updating address of above place in google maps using put method 	
			String newaddress= "Vijayawada";
			String updateresponse=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").
			body(" {\r\n"
					+ "    \"place_id\": \""+placeid+"\",\r\n"
					+ "    \"address\": \""+newaddress+"\",\r\n"
					+ "    \"key\": \"qaclick123\"\r\n"
					+ "}"). 
			when().put("maps/api/place/update/json").then().assertThat().statusCode(200).
			body("msg",equalTo("Address successfully updated")).log().all().extract().response().asString();
			
			JsonPath json333=new JsonPath(updateresponse);
			String message=json333.getString("msg");
			System.out.println(message); 

	}
	
	@Test(priority=3)
	public void get() {
			//retrieving  place in google maps using getmethod 
            
			String getresponse=given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeid).when().
			get("maps/api/place/get/json").then().assertThat().statusCode(200).log().all().extract().response().asString();
		   //we can either check response body using jsonpath or using normal body eqaualTo method 
		JsonPath js1=new JsonPath(getresponse);
		String actualaddress=js1.getString("address");
		System.out.println(actualaddress); 
		
	}
	
	
	@Test(priority=4)
	public void delete() {
		//deleting place in google maps
		String q1= given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").
		body("{\r\n"
				+ "    \"place_id\": \""+placeid+"\"\r\n"
				+ "}").when().delete("maps/api/place/delete/json").then().assertThat().statusCode(200).
		body("status",equalTo("OK")).log().all().extract().response().asString();
		JsonPath json22=new JsonPath(q1);
		String status=json22.getString("status");
		System.out.println(status);

		

		
	}

}
