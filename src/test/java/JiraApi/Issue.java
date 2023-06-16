package JiraApi;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;


public class Issue {
	
	@Test
	public void jira() {
		
		//creating Session ID
		RestAssured.baseURI="http://localhost:8080";
		
		SessionFilter session=new SessionFilter();
		
		String response=given().header("Content-Type","application/json").
		body("{\"username\": \"Pogulaganesh18\", \"password\": \"Ganesh@22\" }")
		.log().all().filter(session).when().post("/rest/auth/1/session").then().assertThat().
		statusCode(200).log().all().extract().response() .asString();
		
	/*	JsonPath js=new JsonPath(response);
		String id=js.getString("session.value");
		System.out.println(id); */
		
		//Creating new Issue
		
		String response2=given().log().all().header("Content-Type","application/json").
		body("{\r\n"
				+ "    \"fields\":{\r\n"
				+ "        \"project\":\r\n"
				+ "        {\r\n"
				+ "            \"key\":\"AP\"\r\n"
				+ "        },\r\n"
				+ "        \"summary\": \"Register Button\",\r\n"
				+ "        \"description\" : \"Creating an Register Button Bug(Issue) using restassured \",\r\n"
				+ "        \"issuetype\":{\r\n"
				+ "            \"name\":\"Bug\"\r\n"
				+ "        }\r\n"
				+ "    }\r\n"
				+ "}").filter(session).when().post("/rest/api/2/issue").then().assertThat().statusCode(201).log().all().
		       extract().response().asString();
		
		JsonPath js1=new JsonPath(response2);
		String Key=js1.getString("key");
		System.out.println(Key); 
		
		//Adding Comment to above issue
		
	 String response3= given().log().all().pathParam("issuekey",Key).header("Content-Type","application/json").
		body("{\r\n"
				+ "    \"body\": \"Register button is not working properly sometimes it is not Visible\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session).when().post("/rest/api/2/issue/{issuekey}/comment").then().assertThat().statusCode(201)
		.log().all().extract().response().asString();
		
	 JsonPath js2=new JsonPath(response3);
		int commentid=js2.getInt("id");
		System.out.println("comment id:"+commentid); 
		
		//adding attachments
	String response4=given().log().all().pathParam("issuekey",Key).header("Content-Type","multipart/form-data").
		header("X-Atlassian-Token","no-check").
    multiPart("file",new File("C:\\Users\\LENOVO\\eclipse-workspace\\restassured\\src\\test\\java\\JiraApi\\RESUME(Ganesh.P).pdf"))
    .filter(session).when().post("/rest/api/2/issue/{issuekey}/attachments").then().assertThat().statusCode(200).
    log().all().extract().response().asString() ;
			
		JsonPath js3=new JsonPath(response4);
		String file=js3.getString("filename");
		System.out.println(file); 
		
		//get issue details
		
        String response5=given().log().all().pathParam("issuekey",Key).queryParam("fields","attachment").
        queryParam("fields","comment").filter(session).when().get("/rest/api/2/issue/{issuekey}").then().assertThat().statusCode(200).
        log().all().
		extract().response().asString();
        
        System.out.println(response5);
        
      /*  JsonPath js4=new JsonPath(response5); */
	
        

		
		
		
}
}