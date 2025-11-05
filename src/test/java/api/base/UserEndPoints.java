package api.base;

import static io.restassured.RestAssured.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {
	
		public Response createUser(User payload){
		
			Response response = given()
							.contentType(ContentType.JSON)
							.accept(ContentType.JSON)
							.body(payload)
							.when()
							.post(Routes.post_url);
			
			return response;		
		}
	
	
		public Response getUser(String username){
			
			Response response = given()
							.pathParam("username", username)
							.when()
							.get(Routes.get_url);
			
			return response;		
		}
	
		public Response updateUser(User payload, String username){
			
			Response response = given()
							.contentType(ContentType.JSON)
							.accept(ContentType.JSON)
							.pathParam("username", username)
							.body(payload)
							.when()
							.put(Routes.update_url);
			
			return response;		
		}
		
		
		public Response deleteUser(String username){
			
			Response response = given()
							.pathParam("username", username)
							.when()
							.delete(Routes.delete_url);
			
			return response;		
		}

}
