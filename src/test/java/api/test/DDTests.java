package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.base.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	
	UserEndPoints userendpoint = new UserEndPoints();
		
	@Test(priority=1, dataProvider="userData", dataProviderClass=DataProviders.class)
	public void testCreateUser(String id, String username, String firstName, String lastName, String email, String password, String phone) {
		
		User userpayload = new User(Integer.parseInt(id), username, firstName, lastName, email, password, phone);
		
		Response response =userendpoint.createUser(userpayload);
		
		System.out.println(response.asPrettyString());
		
	}
	
	
	@Test(priority=2, dataProvider="userNames", dataProviderClass=DataProviders.class)
	public void testDeleteUser(String username) {
		
		Response response = userendpoint.deleteUser(username);
		
		//System.out.println(response.asPrettyString());
		System.out.println(response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
