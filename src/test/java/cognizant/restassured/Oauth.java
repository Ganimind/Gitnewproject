package cognizant.restassured;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;



import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


public class Oauth {
	
	@Test
	public void getcourses() {
		
		String url ="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AbUR2VPTGg7QqUB4VTVmv_Icunpqm6VtjUTjVtBzR2wcSDljwtEYBTtSCq3Qn7XlegSoKg&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";


		String partialcode=url.split("code=")[1];

		String code=partialcode.split("&scope")[0];

		System.out.println(code);


		
		
		String accesstokenresponse= given().urlEncodingEnabled(false).log().all().queryParam("code",code)
		.queryParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParam("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParam("grant_type","authorization_code")
		.when().post("https://www.googleapis.com/oauth2/v4/token").then().assertThat().statusCode(200).log().all()
		.extract().response().asString();
		
		
		JsonPath js2=new JsonPath(accesstokenresponse);
		String accesstoken=js2.getString("access_token");
		
		String response= given().log().all().queryParam("access_token",accesstoken).when().get("https://rahulshettyacademy.com/getCourse.php")
		.then().assertThat().statusCode(200).log().all().extract().response().asString();
		
		System.out.println(response);
		
		
		

}
}