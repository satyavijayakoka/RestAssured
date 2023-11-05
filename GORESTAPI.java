package day1;

import java.util.Random;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GORESTAPI {
	
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
	public void getUsers() {
		RestAssured.baseURI = "https://gorest.co.in/public/v2/users";
		
		Response response = RestAssured
				            .given()
				            .header("Accept","application/json")
				            .header("Content-Type","application/json")
				            .header("Authorization",token)
				            .when()
				            .get();
		response.then().statusCode(200);
				
	}
	@Test
	public void createUser() {
		String email =getSaltString()+"@gmail.com";
		String requestBody = "{\"name\":\"Tenali Ramakrishna\", \"gender\":\"male\", \"email\":\""+email+"\", \"status\":\"active\"}";
		RestAssured.baseURI = "https://gorest.co.in/public/v2/users";
		Response response = RestAssured
				          .given()
				          .header("Accept","application/json")
				            .header("Content-Type","application/json")
				            .header("Authorization",token)
				            .body(requestBody)
				            .when().post();
		                 response.then().log().all();
				         response.then().statusCode(201);
		
	}
	@Test
	public void updateUser() {
		String email =getSaltString()+"@gmail.com";
		String requestBody = "{\"name\":\"Tenali Ramakrishna\", \"gender\":\"male\", \"email\":\""+email+"\", \"status\":\"active\"}";
		RestAssured.baseURI = "https://gorest.co.in/public/v2/users";
		Response response = RestAssured
				          .given()
				          .header("Accept","application/json")
				            .header("Content-Type","application/json")
				            .header("Authorization",token)
				            .body(requestBody)
				            .when().patch("/5662822");
		                 response.then().log().all();
				         response.then().statusCode(200);
		
		
		
	}
	
	@Test
	public void deleteUser() {
RestAssured.baseURI = "https://gorest.co.in/public/v2/users";
		
		Response response = RestAssured
				            .given()
				            .header("Accept","application/json")
				            .header("Content-Type","application/json")
				            .header("Authorization",token)
				            .when().delete("/5662822");
				            
		response.then().statusCode(204);
		
	}

}


