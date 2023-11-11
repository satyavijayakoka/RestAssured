package day1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class regresTestCases {
	
	@Test
	public void testcase() {
		// .body("data[0].email", equalTo("michael.lawson@reqres.in"));
	//	https://reqres.in/api/users?page=2
		
		// part 1
		RestAssured.baseURI = "https://reqres.in/api";
		given()
		.when()
		.get("/users?page=2")
		.then()
		.statusCode(200)
		//.log().all()
		.header("Content-Type","application/json; charset=utf-8")
		.body("data[0].email",equalTo("michael.lawson@reqres.in"));
		
		// part 2
	
		RestAssured.baseURI = "https://reqres.in/api";
		Response res = given()
				.when()
				.get("/users?page=2");
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"),"application/json; charset=utf-8" );
		
		String email = res.jsonPath().get("data[0].email").toString();
		Assert.assertEquals(email, "michael.lawson@reqres.in");	
	}
	
	@Test
	public void testCase2() {
		
		RestAssured.baseURI = "https://reqres.in/api";
		boolean found = false;
		Response res = RestAssured.given().when().get("/users?page=2");
		
		JSONObject jsonResponse = new JSONObject(res.getBody().asString());
		
		JSONArray data = jsonResponse.getJSONArray("data");
		
		for(int i =0; i<data.length();i++) {
			
			JSONObject user = data.getJSONObject(i);
			
			String firstName = user.getString("first_name");
			
			System.out.println(firstName);
			
			if(firstName.equals("Rachel")) {
				found = true;
				break;
			}
		}
		Assert.assertTrue(found);
				
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
