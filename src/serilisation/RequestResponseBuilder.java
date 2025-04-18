package serilisation;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import serilisationResources.JsonBuilderLocation;
import serilisationResources.Location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;

public class RequestResponseBuilder {

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
		
		RequestSpecification req = new RequestSpecBuilder().setContentType(ContentType.JSON).addQueryParam("key", "qaclick123").build();
		ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).build();
		
		Response AddAPIResponse = given().spec(req).body(j)
		.when().post("/maps/api/place/add/json")
		.then().spec(res).extract().response();
		
		System.out.println(AddAPIResponse.asString());
	}
}
