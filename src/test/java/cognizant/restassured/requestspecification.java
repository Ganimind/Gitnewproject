package cognizant.restassured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class requestspecification {
	RequestSpecification request;
	RequestSpecification request2;
	//String path="https://rahulshettyacademy.com";
	String placeid;
	@BeforeTest
	public void requestspeci() {
		/*requestspecification specifies how our request should be and also if there are 4 tests and there are some common
		properties or values so we can specify these common values in requestspec and these can be reused in all tests */  
       /* request=RestAssured.given().baseUri(path).log().all().queryParam("key","qaclick123").
    		   header("Content-Type","application/json").body(payload.postrequestbody());
    		   request.when().post("maps/api/place/add/json").
		then().assertThat().statusCode(200).body("scope",equalTo("APP")).
				body("status",equalTo("OK")).header("Server","Apache/2.4.41 (Ubuntu)").log().all(); */
		
		request2=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123")
		.setContentType(ContentType.JSON).build();
      /*  request=RestAssured.given();
		request.baseUri("https://rahulshettyacademy.com");
		request.header("Content-Type","application/json");
		request.queryParam("key","qaclick123"); */
	}
		@Test(priority=1)
		public void Add() {
			
			request2=given().spec(request2).log().all().body(payload.postrequestbody());
			Response response2 = request2.when().post("maps/api/place/add/json");
			String response333=response2.then().assertThat().statusCode(200).body("scope",equalTo("APP")).
			body("status",equalTo("OK")).header("Server","Apache/2.4.41 (Ubuntu)").extract().response().asString();
				
		/*	String response=RestAssured.given(request).log().all().body(payload.postrequestbody()).
			when().post("maps/api/place/add/json").then().assertThat().statusCode(200).body("scope",equalTo("APP")).
			body("status",equalTo("OK")).header("Server","Apache/2.4.41 (Ubuntu)").extract().response().asString(); */
				
			System.out.println(response333);	
			JsonPath json=new JsonPath(response333);
			 placeid=json.getString("place_id");
			System.out.println(placeid); 
			
		}
		
			//updating address of above place in google maps using put method 
		@Test(priority=2)
		public void update() {
				String newaddress= "Vijayawada";
				
				String updateresponse2=given().spec(request2).log().all().
						body(" {\r\n"
								+ "    \"place_id\": \""+placeid+"\",\r\n"
								+ "    \"address\": \""+newaddress+"\",\r\n"
								+ "    \"key\": \"qaclick123\"\r\n"
								+ "}"). 
						when().put("maps/api/place/update/json").then().assertThat().statusCode(200).
						body("msg",equalTo("Address successfully updated")).log().all().extract().response().asString();

				
			/*	String updateresponse=RestAssured.given(request).log().all().
				body(" {\r\n"
						+ "    \"place_id\": \""+placeid+"\",\r\n"
						+ "    \"address\": \""+newaddress+"\",\r\n"
						+ "    \"key\": \"qaclick123\"\r\n"
						+ "}"). 
				when().put("maps/api/place/update/json").then().assertThat().statusCode(200).
				body("msg",equalTo("Address successfully updated")).log().all().extract().response().asString(); */
				
				JsonPath json333=new JsonPath(updateresponse2);
				String message=json333.getString("msg");
				System.out.println(message); 
							
		}	
		
		//retrieving  place in google maps using getmethod 
	     @Test(priority=3)     
		public void get() {
				
	    	 String getresponse2= given().spec(request2).log().all().queryParam("place_id",placeid).when().
	 				get("maps/api/place/get/json").then().assertThat().statusCode(200).log().all().extract().response().asString();

	    /*	 String getresponse= RestAssured.given(request).log().all().queryParam("place_id",placeid).when().
				get("maps/api/place/get/json").then().assertThat().statusCode(200).log().all().extract().response().asString(); */
			   //we can either check response body using jsonpath or using normal body eqaualTo method 
	    	 
			JsonPath js1=new JsonPath(getresponse2);
			String actualaddress=js1.getString("address");
			System.out.println(actualaddress); 
			
	     }
	     
	     @Test(priority=4)
	     public void delete() {
			//deleting place in google maps
	    		String q2= given().spec(request2).log().all().
	    				body("{\r\n"
	    						+ "    \"place_id\": \""+placeid+"\"\r\n"
	    						+ "}").when().delete("maps/api/place/delete/json").then().assertThat().statusCode(200).
	    				body("status",equalTo("OK")).log().all().extract().response().asString();
	    				

	    	 
	/*		String q1= RestAssured.given(request).log().all().
			body("{\r\n"
					+ "    \"place_id\": \""+placeid+"\"\r\n"
					+ "}").when().delete("maps/api/place/delete/json").then().assertThat().statusCode(200).
			body("status",equalTo("OK")).log().all().extract().response().asString(); */
			
			JsonPath json22=new JsonPath(q2);
			String status=json22.getString("status");
			System.out.println(status);

			

		}
         
    		  		   
		
	}


