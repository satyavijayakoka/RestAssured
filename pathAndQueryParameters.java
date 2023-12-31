package day1;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;

public class pathAndQueryParameters {
	// https://reqres.in/api/users?page=2
	@Test
	public void testPathAndQueryParameters() {
		
		Response res = RestAssured.given()
				       .pathParam("mypath","users")
				       .queryParam("page", 2)
				       .when()
				       .get("https://reqres.in/api/{mypath}");
		
		res.then().log().all();
		res.then().statusCode(200);
		
		res.then().log().headers();
		res.then().log().body();
		
		res.then().header("Content-Type", Matchers.equalTo("application/json; charset=utf-8"));
		res.then().header("Server", Matchers.equalTo("cloudflare"));
		res.then().header("Content-Encoding", Matchers.equalTo("gzip"));
		res.then().header("Connection", Matchers.equalTo("keep-alive"));
		res.then().header("Cache-Control", Matchers.equalTo("max-age=14400"));
		res.then().header("Vary", Matchers.equalTo("Accept-Encoding"));
		res.then().header("CF-Cache-Status", Matchers.equalTo("HIT"));
		
		
		res.then().body("page", Matchers.equalTo(2));
		res.then().body("per_page",Matchers.equalTo(6));
		res.then().body("total",Matchers.equalTo(12));
		res.then().body("total_pages",Matchers.equalTo(12));
		res.then().body("data[0].id",Matchers.equalTo(7));
		res.then().body("data[0].email",Matchers.equalTo("michael.lawson@reqres.in"));
		res.then().body("data[2].first_name", Matchers.equalTo("Tobias"));
		
		
		
	}

}
