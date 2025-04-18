package OAUTH_decerilisation;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojoResource.Api;
import pojoResource.GetCourse;
import pojoResource.WebAutomation;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CourseOAUTH {
	
	@Test
	public static void getCourseDetails() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String tokenResponse = given().formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.formParam("grant_type", "client_credentials")
				.formParam("scope", "trust")
		.when().post("/oauthapi/oauth2/resourceOwner/token")
		.then().extract().response().asString();
		
		JsonPath js = new JsonPath(tokenResponse);
		String token = js.getString("access_token");
		System.out.println(token);
		
		GetCourse courseResponse = given().queryParam("access_token", token).when().get("/oauthapi/getCourseDetails").then().extract().response().as(GetCourse.class);
		
		System.out.println(courseResponse.getLinkedIn());
		System.out.println(courseResponse.getInstructor());
		System.out.println(courseResponse.getCourses().getApi().get(1).getCourseTitle());
		
		List<Api> apiCourses = courseResponse.getCourses().getApi();
		
		for (int i = 0; i < apiCourses.size(); i++) {
			if (apiCourses.get(i).getCourseTitle().equalsIgnoreCase("Rest Assured Automation using Java")) {
				System.out.println("********");
				System.out.println(apiCourses.get(i).getCourseTitle());
				System.out.println(apiCourses.get(i).getPrice());
				System.out.println("********");
			}
		}
		
		/*Assignment*/
		List<WebAutomation> webAutomationCourses = courseResponse.getCourses().getWebAutomation();
		for (int i = 0; i < webAutomationCourses.size(); i++) {
			System.out.println(webAutomationCourses.get(i).getCourseTitle());
		}
		
		//Course title test case
		String[] WebAutomationCourseTitles = {"Selenium Webdriver Java","Cypress","Protractor"};
		ArrayList<String> a = new ArrayList<String>();
		for (int i = 0; i < webAutomationCourses.size(); i++) {
			a.add(webAutomationCourses.get(i).getCourseTitle());
		}
		List<String> expectedList=	Arrays.asList(WebAutomationCourseTitles);
		Assert.assertTrue(a.equals(expectedList));
	}

}
