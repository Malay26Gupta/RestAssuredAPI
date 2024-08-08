package api.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.EndPoints.UserEndpoints;
import apiPayload.User;
import io.restassured.response.Response;
import utilities.DataProviders;

public class UserTest {
	
	@Test(priority=1, dataProvider = "AllData", dataProviderClass = DataProviders.class)
	public void testCreateUser(String UserID, String UserName, String fname, String lname, String email, String pwd,String Phone)
	{
		/*System.out.println("UserID: " + UserID); // Debug logging
	    System.out.println("UserName: " + UserName);
	    System.out.println("FirstName: " + fname);
	    System.out.println("LastName: " + lname);
	    System.out.println("Email: " + email);
	    System.out.println("Password: " + pwd);
	    System.out.println("Phone: " + Phone);*/
		
		User userPayload = new User();
		
		try {
	        if (UserID != null && !UserID.isEmpty()) {
	            userPayload.setId(Integer.parseInt(UserID));
	        } else {
	            throw new IllegalArgumentException("UserID cannot be empty");
	        }
	    } catch (NumberFormatException e) {
	        throw new IllegalArgumentException("Invalid UserID format: " + UserID, e);
	    }
		
		//userPayload.setId(Integer.parseInt(UserID));
		//userPayload.setId(uId);
		userPayload.setUserName(UserName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(email);
		userPayload.setPassword(pwd);
		userPayload.setPhone(Phone);
		Response response = UserEndpoints.createUser(userPayload);
		
		//log response
		response.then().log().all();
		
		//validation
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	/*@Test(priority=2, dataProvider = "UserNamesData", dataProviderClass = DataProviders.class)
	public void testGetUser(String UserName)
	{
		Response response = UserEndpoints.getUser(UserName);
		
		System.out.println("user details: " + UserName);
		//log response
		response.then().log().all();
		
		//validation
		Assert.assertEquals(response.getStatusCode(),200);		
	}*/
	
	@Test(priority=2,dataProvider = "UserNamesData", dataProviderClass = DataProviders.class)
	public void testDeleteUser(String userName)
	{
		System.out.println("Attempting to delete user: " + userName); // Log username
		// Check if user exists before deletion
	    //Response getUserResponse = UserEndpoints.getUser(username);
	    //Assert.assertEquals(getUserResponse.getStatusCode(), 200, "User does not exist: " + username);

	    // Adding a short delay to ensure user is available for deletion
	    try {
	        Thread.sleep(2000); // 2 seconds delay
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	    
		Response response = UserEndpoints.deleteUser(userName);

		//System.out.println("Delete User Data.");
		System.out.println("Delete User Data for username: " + userName);
		//log response
		response.then().log().all();


		//validation
		Assert.assertEquals(response.getStatusCode(),404);
	}

}
