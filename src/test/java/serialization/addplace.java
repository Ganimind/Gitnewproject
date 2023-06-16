package serialization;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import cognizant.restassured.payload;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;


public class addplace {
	
	@Test
	public void add() {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		Map<String,String> map=new HashMap<String,String>();
		map.put("key","qaclick123");
		map.put("Content-Type","application/json");
		
		Pojo k=new Pojo();
		
		k.setAccuracy(50);
		k.setName("Ganeshpogula");
		k.setPhone_number("(+91) 983 893 3937");
		k.setAddress("29, side layout, cohen 09");
		k.setWebsite("http://rahulshettyacademy.com");
		k.setLanguage("Telugu");
		List<String> list=new ArrayList<String>();
		list.add("shoe park");
		list.add("shop");
		k.setTypes(list);
		
		Location l=new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		k.setLocation(l);
		
		
		String response =given().log().all().queryParams(map).headers(map).
		body(k).
		when().post("maps/api/place/add/json").then().assertThat().statusCode(200).body("scope",equalTo("APP")).
		body("status",equalTo("OK")).header("Server","Apache/2.4.41 (Ubuntu)").extract().response().asString();
			
		System.out.println(response);	
		JsonPath json=new JsonPath(response);
		String placeid=json.getString("place_id");
		System.out.println(placeid);

		
	}

}
