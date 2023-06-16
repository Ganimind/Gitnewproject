package StepDefinitions;

//import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
//import static org.testng.Assert.assertEquals;

import org.testng.Assert;

//import java.util.HashMap;
//import java.util.Map;

//import org.hamcrest.Matcher;
//import org.testng.Assert;

import cognizant.restassured.payload;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
//import io.restassured.specification.ResponseSpecification;
//import static io.restassured.RestAssured.*;
//import  io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
//import io.restassured.response.ResponseBody;



public class teststeps {
	
	RequestSpecification req;
	Response res;
	String placeid;
	RequestSpecification req2;
	RequestSpecification req3;
	RequestSpecification req4;
	Response res2;
	Response res3;
	Response res4;
	String response12;
	Responsedata obj=new Responsedata();
    String path="https://rahulshettyacademy.com";

	@Given("user have api headers information and Payload")
	public void user_have_api_headers_information_and_Payload() { 
	    // Write code here that turns the phrase above into concrete actions
		//RestAssured.baseURI="https://rahulshettyacademy.com";
	/*	  Map<String,String> map=new HashMap<String,String>();
		map.put("Content-Type","application/json");
		req=given().log().all().baseUri(path).queryParam("key","qaclick123").headers(map).body(payload.postrequestbody());  */
		
		req=RestAssured.given().baseUri(path).log().all().queryParam("key","qaclick123").header("Content-Type","application/json").
		body(payload.postrequestbody());
		
	   
	}

	@When("user calls the Addplaceapi using Post http method")
	public String user_calls_the_Addplaceapi_using_Post_http_method() {
	    // Write code here that turns the phrase above into concrete actions
		res= req.when().post("maps/api/place/add/json").andReturn();
     return res.toString();
	}

	@Then("user validate the Response")
	public void user_validate_the_response() {
	    // Write code here that turns the phrase above into concrete actions
		//res.then().assertThat().statusCode(200).body("scope",equalTo("APP")).
		//body("status",equalTo("OK")).header("Server","Apache/2.4.41 (Ubuntu)");
	   response12 =  res.then().assertThat().statusCode(200).body("scope",equalTo("APP")).
		body("status",equalTo("OK")).header("Server","Apache/2.4.41 (Ubuntu)").log().all().extract().response().asString();
	 String response13=res.asString();
		System.out.println("response"+response13);
		JsonPath js=new JsonPath(response13);
	//   placeid=js.getString("place_id");
	placeid=Responsedata.setPlaceid(js.get("place_id"));
		System.out.println("place id"+placeid);
		Assert.assertEquals(200 , res.getStatusCode()); 
	   String app= js.get("scope");
	   Assert.assertEquals("APP",app);
	}
	
@Given("user have update api headers information and Payload")
public void user_have_update_api_headers_information_and_payload() {
    // Write code here that turns the phrase above into concrete actions
	String newaddress= "Hyderabad";
	// response12 =  data.getResponse12();
	 //JsonPath js=new JsonPath(response12);
	 String response13=res.asString();
		System.out.println("response"+response13);
		JsonPath js=new JsonPath(response13);

	 placeid=Responsedata.getPlaceid();

    System.out.println(placeid); 
	
	req2=RestAssured.given().baseUri(path).log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
			.body("{\r\n"
					+ "    \"place_id\": \""+placeid+"\",\r\n"
					+ "    \"address\": \""+newaddress+"\",\r\n"
					+ "    \"key\": \"qaclick123\"\r\n"
					+ "}");
    
}

@When("user calls the Updateplaceapi using Put http method")
public void user_calls_the_updateplaceapi_using_put_http_method() {
    // Write code here that turns the phrase above into concrete actions
	res2=req2.when().put("maps/api/place/update/json");
}

@Then("user validate the updateapiResponse")
public void user_validate_the_updateapi_response() {
    // Write code here that turns the phrase above into concrete actions
	String response22=res2.then().assertThat().statusCode(200).
	body("msg",equalTo("Address successfully updated")).log().all().extract().response().asString();
	JsonPath js6=new JsonPath(response22);
	   String message=js6.getString("msg");
		System.out.println(message);	
}

@Given("user have get api information")
public void user_have_get_api_information() {
    // Write code here that turns the phrase above into concrete actions
    req3=RestAssured.given().baseUri(path).log().all().queryParam("key","qaclick123").queryParam("place_id",placeid);
}

@When("user calls the Getplaceapi using Get http method")
public void user_calls_the_getplaceapi_using_get_http_method() {
    // Write code here that turns the phrase above into concrete actions
    res2=req3.when().get("maps/api/place/get/json");
}

@Then("user validate the getapiResponse")
public void user_validate_the_getapi_response() {
    // Write code here that turns the phrase above into concrete actions
	String response33=res2.then().assertThat().statusCode(200).log().all().extract().response().asString();
	JsonPath js1=new JsonPath(response33);
	String actualaddress=js1.getString("address");
	System.out.println(actualaddress); 

}

@Given("user have delete api headers information and Payload")
public void user_have_delete_api_headers_information_and_payload() {
    // Write code here that turns the phrase above into concrete actions
	req4=RestAssured.given().baseUri(path).log().all().queryParam("key","qaclick123").header("Content-Type","application/json").
			body("{\r\n"
					+ "    \"place_id\": \""+placeid+"\"\r\n"
					+ "}");
    
}

@When("user calls the Deleteplaceapi using Delete http method")
public void user_calls_the_deleteplaceapi_using_delete_http_method() {
    // Write code here that turns the phrase above into concrete actions
    res4=req4.when().delete("maps/api/place/delete/json");
}

@Then("user validate the deleteapiResponse")
public void user_validate_the_deleteapi_response() {
    // Write code here that turns the phrase above into concrete actions
	String response44= res4.then().assertThat().statusCode(200).
	body("status",equalTo("OK")).log().all().extract().response().asString();
	JsonPath json22=new JsonPath(response44);
	String status=json22.getString("status");
	System.out.println(status);


	
}



}
