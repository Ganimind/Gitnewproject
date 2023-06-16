package cognizant.restassured;

public class Payload2 {

	public static String pay(String isbn,String aisle) {
		
    String data="{\r\n"
    		+ "    \"name\": \"Learn Appium Automation with Java\",\r\n"
    		+ "    \"isbn\": \""+isbn+"\",\r\n"
    		+ "    \"aisle\": \""+aisle+"\",\r\n"
    		+ "    \"author\": \"John foe\"\r\n"
    		+ "}";
    
    return data;
}
}
