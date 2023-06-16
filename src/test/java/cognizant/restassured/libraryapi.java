package cognizant.restassured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;


public class libraryapi {
	
	@Test
	public void postapi() {
	
	RestAssured.baseURI="http://216.10.245.166";
	String res=given().header("Content-Type","application/json").body(payload.library()).when().post("Library/Addbook.php").
	then().statusCode(200).body("Msg",equalTo("successfully added")).header("Server","Apache").extract().response().asString();
    	
	JsonPath js=new JsonPath(res);
	String id=js.getString("ID");
	System.out.println(id);
	
	//getting book details using book id
	
	String getresponse=given().queryParam("ID",id).when().get("Library/GetBook.php").then().statusCode(200).
			extract().response().asString();
	
	JsonPath js1=new JsonPath(getresponse);
     String isbn=js1.getString("isbn");
     String aisle=js1.getString("aisle");
     System.out.println(isbn);
      System.out.println(aisle);
      
      //deleting bookdetails using book id 
      given().body("{\r\n"
      		+ "    \"ID\": \"cricket333\"\r\n"
      		+ "}").when().delete("/Library/DeleteBook.php").then().statusCode(200).
      body("msg",equalTo("book is successfully deleted"));
      
      
}
}