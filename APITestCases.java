package day1;

import java.util.Random;


import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APITestCases {
	int id = 0;
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
	
	@Test(priority= 1)
	public void testGetRequest() {
		Response getResponse = apiUtils.sendGetRequest("/users");
		getResponse.then().statusCode(200);
		getResponse.then().log().all();
		
	}
	@Test(priority= 2)
	public void testPostRequest() {
		String email =getSaltString()+"@gmail.com";
		String payload = "{\"name\":\"Tenali Ramakrishna\", \"gender\":\"male\", \"email\":\""+email+"\", \"status\":\"active\"}";
		Response postResponse = apiUtils.sendPostRequest("/users", payload);
		postResponse.then().statusCode(201);
		postResponse.then().log().all();
		
		 id = postResponse.jsonPath().getInt("id");
		System.out.println(id);
		String name =postResponse.jsonPath().getString("name");
		System.out.println(name);
		
		String status =postResponse.jsonPath().getString("status");
		System.out.println(status);
		
	}
	
	@Test(priority= 3)
	public void testPutRequest() {
		String email =getSaltString()+"@gmail.com";
		String payload = "{\"name\":\"Tenali Sharma\", \"gender\":\"male\", \"email\":\""+email+"\", \"status\":\"active\"}";	
		Response putResponse = apiUtils.sendPutRequest("/users/" +id, payload);
		putResponse.then().statusCode(200);
		putResponse.then().log().all();
	}
	
	@Test(priority= 4)
	public void testDeleteRequest() {
		Response deleteResponse = apiUtils.sendDeleteRequest("/users/"+id);
		deleteResponse.then().statusCode(204);
		
	}
	

}
