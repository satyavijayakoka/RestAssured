package day1;

import java.util.Random;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APITestCases {
	
	private String accessToken = "956e38dfc3c6f27c212a3c058cf11d52bbc76dced2c4453459ab7a206a672acb";
	
	APIUtilities  apiUtils = new APIUtilities(accessToken) ;
	String token = "Bearer 956e38dfc3c6f27c212a3c058cf11d52bbc76dced2c4453459ab7a206a672acb";
	protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
	}
	
	@Test
	public void testGetRequest() {
		Response getResponse = apiUtils.sendGetRequest("/users");
		getResponse.then().statusCode(200);
		getResponse.then().log().all();
		
	}
	@Test
	public void testPostRequest() {
		String email =getSaltString()+"@gmail.com";
		String payload = "{\"name\":\"Tenali Ramakrishna\", \"gender\":\"male\", \"email\":\""+email+"\", \"status\":\"active\"}";
		Response postResponse = apiUtils.sendPostRequest("/users", payload);
		postResponse.then().statusCode(201);
		postResponse.then().log().all();
		//5705349
	}
	
	@Test
	public void testPutRequest() {
		String email =getSaltString()+"@gmail.com";
		String payload = "{\"name\":\"Tenali Sharma\", \"gender\":\"male\", \"email\":\""+email+"\", \"status\":\"active\"}";	
		Response putResponse = apiUtils.sendPutRequest("/users/5705349", payload);
		putResponse.then().statusCode(200);
		putResponse.then().log().all();
	}
	
	@Test
	public void testDeleteRequest() {
		Response deleteResponse = apiUtils.sendDeleteRequest("/users/5705349");
		deleteResponse.then().statusCode(204);
		
	}
	

}
