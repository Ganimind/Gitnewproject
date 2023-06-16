package StepDefinitions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Responsedata {
	
	private static String response12;
   private static String placeid;
	
	public static String getPlaceid() {
	return placeid;
}

  public static String setPlaceid(String placeid) {
	  System.out.println("placeid");
	return  placeid = placeid;
      //System.out.println("placeid");
  }

	public static String getResponse12() {
		return response12;
	}

	public static String setResponse12(String response12) {
		return response12 = response12;
		
	}

	public static String setPlaceid(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

		

}
