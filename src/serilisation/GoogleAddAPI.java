package serilisation;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import serilisationResources.JsonBuilderLocation;
import serilisationResources.Location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;

public class GoogleAddAPI {

	@Test
	public static void AddAPI() {
		
		JsonBuilderLocation j = new JsonBuilderLocation();
		Location l = new Location();
		
		l.setLat(-38.383494);
		l.setLng(33.427362);
		j.setLocation(l);
		j.setAccuracy(50);
		j.setName("Frontline house");
		j.setPhone_number("(+91) 983 893 3937");
		j.setAddress("29, side layout, cohen 09");
		ArrayList<String> t = new ArrayList<String>();
		t.add("shoe park");
		t.add("shop");
		j.setTypes(t);
		j.setWebsite("http://google.com");
		j.setLanguage("French-IN");
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		Response AddAPIResponse = given().queryParam("key", "qaclick123").body(j)
		.when().post("/maps/api/place/add/json");
		
		System.out.println(AddAPIResponse.getStatusCode());
		
		JsonPath js = new JsonPath(AddAPIResponse.asString());
		System.out.println(js.getString("place_id"));
	}
}
