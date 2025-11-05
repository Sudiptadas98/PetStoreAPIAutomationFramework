package api.test;

import org.testng.annotations.Test;

import io.restassured.response.Response;


import static io.restassured.RestAssured.*;


public class LoginAPITest {

	@Test
	public void LoginTest() {
		
	 	 Response res = given().baseUri("https://dummyjson.com/auth")
		.header("Content-Type", "application/json")
		.body("{ \"username\": \"emilys\", \"password\": \"emilyspass\" }")
		.when().post("/login")
		.then().log().all().extract().response();
	 	 
	 	 System.out.println(res.getStatusCode());
									
	}

}
