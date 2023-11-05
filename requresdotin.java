package day1;

import static io.restassured.RestAssured.given;


import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class requresdotin {	
	
	@Test
	public void getUsers() {
		// https://reqres.in/api/users/2
		RestAssured.baseURI = "https://reqres.in/api";
		Response response = 
				given()
				.when()
				.get("users/2");
		
		response.then().statusCode(200);
		
		// second assertion
		response.then().assertThat()
		.body("data.id", equalTo(2))
		.body("data.email", equalTo("janet.weaver@reqres.in"))
		.body("data.first_name", equalTo("Janet"))
		.body("data.last_name", equalTo("Weaver"))
		.body("data.avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"));
		response.then().log().all();
		
	}
	
	@Test
	public void createUsers() {
		// Set the base URI for the API
		RestAssured.baseURI = "https://reqres.in/api";
       
        // Define the request payload (user data) in JSON format
		String requestBody = "{\"name\":\"John Doe\", \"job\":\"Software Engineer\"}";

        // Make a POST request to create a new user
		Response response = 
				given()
				.contentType(ContentType.JSON)
				.body(requestBody)
				.when()
				.post("/users");
		 // Validate the response status code
		response.then().statusCode(201);
		 // Validate the response body using Hamcrest matchers
		response.then().assertThat()
		.body("name", equalTo("John Doe"))
		.body("job",equalTo("Software Engineer"));

    }
	@Test
	public void updateUsers() {
		 // Set the base URI for the API
        RestAssured.baseURI = "https://reqres.in/api/";

        // Define the request payload (user data) in JSON format
        String requestBody = "{\"name\": \"Satya Koka\", \"job\": \"Software Engineer\"}";

        // Make a PUT to update the user
        Response response = given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when()
            .put("users/2");

        // Validate the response status code
        response.then().statusCode(200);

        // Validate the response body using Hamcrest matchers
        response.then().assertThat()
            .body("name", equalTo("Satya Koka"))
            .body("job", equalTo("Software Engineer"));
        response.then().log().all();
	}
	
	@Test
	public void deleteUsers() {
		 RestAssured.baseURI = "https://reqres.in/api/";
		 Response response = given()
		            .when()
		            .delete("users/2");
		
		 response.then().statusCode(204);
		 
		
	}

}
