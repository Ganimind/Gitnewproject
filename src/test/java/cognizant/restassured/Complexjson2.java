package cognizant.restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class Complexjson2 {
	@Test
	public void json() {
		 JsonPath js=new JsonPath(payload.complexjsonbody());
		 
		 //Print No of courses returned by API
         int count=js.getInt("courses.size()");
		System.out.println(count);
		
		//Print Purchase Amount
		int amount=js.getInt("dashboard.purchaseAmount");
		System.out.println(amount);
		
		//Print Title of the first course
		String course=js.getString("courses[0].title");
		System.out.println(course);
		
		//Print All course titles and their respective Prices
		for(int i=0;i<count;i++) {
			String courses=js.getString("courses["+i+"].title");
			int prices=js.getInt("courses["+i+"].price");
			System.out.println(courses);
			System.out.println(prices);
			
		}
		//Print no of copies sold by RPA Course
		for(int i=0;i<count;i++) {
			String courses=js.getString("courses["+i+"].title");
          if(courses.equalsIgnoreCase("RPA")) {
        	  int copies=js.getInt("courses["+i+"].copies");
        	  System.out.println(copies);
        	  
          } 	  
          }
		
		//Verify if Sum of all Course prices matches with Purchase Amount
		int sum=0;
		for(int i=0;i<count;i++) {
			
			int prices=js.getInt("courses["+i+"].price");
			//System.out.println(prices);
			int copies=js.getInt("courses["+i+"].copies");
      	     //System.out.println(copies);
      	     int totalamount=prices*copies;
      	     sum=sum+totalamount;

		}
		System.out.println(sum);
		Assert.assertEquals(amount,sum);
	

}
}