package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.base.UserEndPoints;
import api.payload.GetUserResponse;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	User userpayload = new User(1023,"sd1023", "ss", "das", "a@mail.com", "1234", "9832");
	
	UserEndPoints userendpoint = new UserEndPoints();
	
	String message;
	
	@Test(priority = 1)
	public void testCreateUser() {
		
		Response response =userendpoint.createUser(userpayload);
		
		System.out.println(response.asPrettyString());
		
		//this.message = response.jsonPath().getString("message");
	
	}
	
	
	@Test(priority=2)
	public void testGetUser() {
		
		Response response = userendpoint.getUser(userpayload.getUsername());
		
		System.out.println(response.asPrettyString());
		
		GetUserResponse getuserResponse = response.as(GetUserResponse.class);
		
		System.out.println(getuserResponse.getUsername());
		
		Assert.assertEquals(getuserResponse.getEmail(), "a@mail.com");
				
	}
	
	
	@Test(priority=3)
	public void testUpdateUser() {
		
		userpayload.setFirstName("Ssd");
		userpayload.setLastName("Ddas");
		
		Response response = userendpoint.updateUser(userpayload, userpayload.getUsername());
		
		System.out.println(response.asPrettyString());
		
		System.out.println(userendpoint.getUser(userpayload.getUsername()).asPrettyString());
	}
	
	
	
	@Test(priority=4)
	public void testDeleteUser() {
		
		Response response = userendpoint.deleteUser(userpayload.getUsername());
		
		//System.out.println(response.asPrettyString());
		System.out.println(response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	
	
	
	

}
