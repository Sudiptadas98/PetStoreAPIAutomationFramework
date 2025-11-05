package api.base;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints2 {
	
	
		//method created for getting URL's from properties file
		static ResourceBundle getUrl(){
			ResourceBundle routes = ResourceBundle.getBundle("routes");  //Load properties file
			return routes;
		}
	
		public Response createUser(User payload){
			
			String post_url = getUrl().getString("post_url");
			
			Response response = given()
							.contentType(ContentType.JSON)
							.accept(ContentType.JSON)
							.body(payload)
							.when()
							.post(post_url);
			
			return response;		
		}
	
	
		public Response getUser(String username){
			
			String get_url = getUrl().getString("get_url");
			
			Response response = given()
							.pathParam("username", username)
							.when()
							.get(get_url);
			
			return response;		
		}
	
		public Response updateUser(User payload, String username){
			
			String update_url = getUrl().getString("update_url");
			
			Response response = given()
							.contentType(ContentType.JSON)
							.accept(ContentType.JSON)
							.pathParam("username", username)
							.body(payload)
							.when()
							.put(update_url);
			
			return response;		
		}
		
		
		public Response deleteUser(String username){
			
			String delete_url = getUrl().getString("delete_url");
			
			Response response = given()
							.pathParam("username", username)
							.when()
							.delete(delete_url);
			
			return response;		
		}

}
