package cognizant.restassured;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class Complexjson {

	@Test
	public void request() {
		baseURI="https://reqres.in/";
        String getresponse=given().
        when().get("api/users?page=2").
		then().assertThat().statusCode(200).body("total",equalTo(12)).body("data[2].last_name",equalTo("Funke")).
		extract().response().asString();
	   JsonPath js=new JsonPath(getresponse);
       //printing total no.of pages 
	   int totalpages=js.getInt("total_pages");
	   System.out.println(totalpages);
	   //support url
	   String url=js.get("support.url");
	   System.out.println(url);
       //printing id=9 in data array 
	   int id=js.get("data[2].id");
	   System.out.println(id);
		//printing lastname=Edwards in data array
	   String lastname=js.getString("data[4].last_name");
	   System.out.println(lastname);
	   //printing no. of elements  in data array
	   int dataelements=js.getInt("data.size()");
	   System.out.println(dataelements); //6
		
		
	}
	
	
}
