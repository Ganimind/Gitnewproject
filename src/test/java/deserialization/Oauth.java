package deserialization;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;


public class Oauth {
	
	@Test
	public void courses() {
		
		String url ="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AbUR2VM6cPYeWEiCmagmNvinKOwKxsavb7BrsUL7CfrZuHxxcnhLN41HaSt89qB2FCULMw&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";


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
		
		Getcourse res= given().log().all().queryParam("access_token",accesstoken).expect().defaultParser(Parser.JSON).
		when().get("https://rahulshettyacademy.com/getCourse.php")
		.then().assertThat().statusCode(200).extract().response().as(Getcourse.class);
		
		System.out.println(res.getInstructor());
		Assert.assertEquals("RahulShetty",res.getInstructor());
		System.out.println(res.getLinkedIn());
		System.out.println(res.getExpertise());
		System.out.println(res.getServices());
		System.out.println(res.getUrl());
		
		// printing Rest Assured Automation using Java
		System.out.println(res.getCourses().getApi().get(0).getCourseTitle());
		
		 // printing SoapUI Webservices testing
		System.out.println(res.getCourses().getApi().get(1).getCourseTitle());
		
		//printing api price 40
		System.out.println(res.getCourses().getApi().get(1).getPrice());
		
		//printing all webautomation course prices
		 List<Webautomation> price= res.getCourses().getWebAutomation();
		 
		for(int i=0;i<price.size();i++) {
			System.out.println(price.get(i).getPrice());
		}
		
		//checking  all webautomation courses are present or not 
		
		String[] array = {"Selenium Webdriver Java","Cypress","Protractor"};
		List<String> a=new ArrayList<String>();
		a.add("Selenium Webdriver Java");
		a.add("Cypress");
		a.add("Protractor");
		List<String> b=new ArrayList<String>();

		
		List<Webautomation> web= res.getCourses().getWebAutomation();
		 
		for(int j=0;j<web.size();j++) {
			  b.add(web.get(j).getCourseTitle());
		}
        
		List<String> c=Arrays.asList(array);
		
		Assert.assertTrue(a.equals(b)); 
		Assert.assertTrue(c.equals(b));
		
		//printing Appium-Mobile Automation using Java
		System.out.println(res.getCourses().getMobile().get(0).getCourseTitle());
		
		//printing course price=40 if course title is Protractor
		
		List<Webautomation>  web2= res.getCourses().getWebAutomation();
        
		for (int k=0;k<web2.size();k++) {
			if(web2.get(k).getCourseTitle().equalsIgnoreCase("Protractor")) {
				System.out.println(web2.get(k).getPrice());
			}
		}
		
		
	}
	

}
