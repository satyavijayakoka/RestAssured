package day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APIUtilities {
	
	private String accessToken = "956e38dfc3c6f27c212a3c058cf11d52bbc76dced2c4453459ab7a206a672acb";
	
	public APIUtilities(String accessToken) {
		this.accessToken = accessToken;
		RestAssured.baseURI = "https://gorest.co.in/public/v2";
	}
	
	// get
	
	public Response sendGetRequest(String endpoint) {
		return RestAssured
				.given()
				.header("Authorization","Bearer "+accessToken)
				.header("Content-Type","Application/json")
				.header("Accept","Application/json")
				.when()
				.get(endpoint);
	}

	// post
	
	public Response sendPostRequest(String endpoint, String payload) {
		return RestAssured
				.given()
				.header("Authorization","Bearer "+accessToken)
				.header("Content-Type","Application/json")
				.header("Accept","Application/json")
				.body(payload)
				.when()
				.post(endpoint);
	}
	
	// put
	
	public Response sendPutRequest(String endpoint, String payload) {
		return RestAssured
				.given()
				.header("Authorization","Bearer "+accessToken)
				.header("Content-Type","Application/json")
				.header("Accept","Application/json")
				.body(payload)
				.when()
				.put(endpoint);
	}
	
	// delete
	
	public Response sendDeleteRequest(String endpoint) {
		return RestAssured
				.given()
				.header("Authorization","Bearer "+accessToken)
				.header("Content-Type","Application/json")
				.header("Accept","Application/json")
				.when()
				.delete(endpoint);
	}
	
}
