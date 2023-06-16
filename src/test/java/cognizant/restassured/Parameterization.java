package cognizant.restassured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Parameterization {
	
	@Test
	public void multipletests() {
		
		RestAssured.baseURI="http://216.10.245.166";
		String res=given().header("Content-Type","application/json").body(Payload2.pay("gayle","333")).when().post("Library/Addbook.php").
		then().statusCode(200).body("Msg",equalTo("successfully added")).header("Server","Apache").extract().response().asString();
	    	
		JsonPath js=new JsonPath(res);
		String id=js.getString("ID");
		System.out.println(id);

	}
	
	@Test(dataProvider="librarydata")
	public void dataprovider(String isbn,String aisle ) {
		RestAssured.baseURI="http://216.10.245.166";
		String res=given().header("Content-Type","application/json").body(Payload2.pay(isbn,aisle)).when().post("Library/Addbook.php").
		then().statusCode(200).body("Msg",equalTo("successfully added")).header("Server","Apache").extract().response().asString();
	    	
		JsonPath js=new JsonPath(res);
		String id=js.getString("ID");
		System.out.println(id);

	}
		@DataProvider(name="librarydata")
		public Object[][] dp(){
			Object[][]  data=new Object[][] {
				{"ganesh","100"},
				{"Sai","101"},
				{"siva","103"}
			};
			return data;
				
			}
		}
		
	


