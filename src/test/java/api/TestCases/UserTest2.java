package api.TestCases;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.EndPoints.UserEndpoints;
import apiPayload.User;
import io.restassured.response.Response;

public class UserTest2 {
	
	Faker faker;
	User userPayload;
	public static Logger logger;

	@BeforeClass
	public void generateTestData()
	{
		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUserName(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());


		//obtain logger

		logger = LogManager.getLogger("RestAPI");
	}

	@Test(priority=1)
	public void testCreateUser()
	{
		Response response = UserEndpoints.createUser(userPayload);

		//log response
		response.then().log().all();


		//validation
		Assert.assertEquals(response.getStatusCode(),200);

		//log
		logger.info("Create User executed.");
	}


	/*@Test(priority=2)
	public void testGetUserData()
	{
		Response response = UserEndpoints.getUser(this.userPayload.getUserName());

		System.out.println("Read User Data.");
		//log response
		response.then().log().all();


		//validation
		Assert.assertEquals(response.getStatusCode(),200);

		//log
		//logger.info("Get User Data executed.");
	}*/

	@Test(priority=2)
	public void testUpdateUser()
	{
		userPayload.setFirstName(faker.name().firstName());
		Response response = UserEndpoints.updateUser(userPayload,this.userPayload.getUserName());


		//log response
		response.then().log().all();


		//validation
		Assert.assertEquals(response.getStatusCode(),200);

		//Read User data to check if first name is updated 

		Response responsePostUpdate = UserEndpoints.getUser(this.userPayload.getUserName());

		System.out.println("After Update User Data.");

		responsePostUpdate.then().log().all();

		//log
		logger.info("Update User executed.");

	}

	/*@Test(priority=4)
	public void testDeleteUser()
	{

		Response response = UserEndpoints.deleteUser(this.userPayload.getUserName());

		System.out.println("Delete User Data.");

		//log response
		response.then().log().all();


		//validation
		Assert.assertEquals(response.getStatusCode(),200);

		
		//log
				//logger.info("Delete User executed.");


	}*/
}
