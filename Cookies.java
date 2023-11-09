package day1;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Cookies {
	
	
	//When a user visits a website, the web server creates small blocks of data called cookies and
	//stores them on the user's computer or device via their web browser.
	//Multiple cookies may be placed on the device during a session. 
	//When the user revisits the website, it loads faster because the cookies are already stored on their device.

	
	// talk about local storage, session storage and cookies

	
	 // what would happen if we place mistakenly post instead of get

	
	// @Test(priority =1)
	public void testCookies() {
		
		given()
		.when()
		.get("https://www.google.com/")
		.then()
		.cookie("AEC","Ackid1T0wqcar0hLiB5h22ycZeiuqrnj0TYMRGpf6CUtJ7D94E2kNJYYSQ")
		.log().all();
	}
	
	@Test(priority =2)
	public void getCookiesInfo() {
		
		Response res =given()
		.when()
		.get("https://www.google.com/");
		
		// get single cookie
		
		//String cookie_value =res.getCookie("AEC");
		//System.out.println(cookie_value);
		
		// get all cookies
		
		Map<String, String> cookies_values = res.getCookies();
		// System.out.println(cookies_values.keySet());
		
		for(String k:cookies_values.keySet()) {
			String cookie_value =res.getCookie(k);
			System.out.println(k+"   "+cookie_value);
			
		}
	}

}
