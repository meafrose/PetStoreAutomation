package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
//import com.github.scribejava.core.model.Response;

import api.endpoints.UserEndpoints;
import api.payload.User;

import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User userPayload;
	
	public Logger logger; //for logs
	
	@BeforeClass
	public void setupData()
	{
		faker=new Faker();
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		logger=LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority=1)
	public void testPostuser()
	{
		logger.info("***************creating user*************");
		Response response=UserEndpoints.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("***************user is created*************");
	}
	
	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("***************reading user info*************");
		
		Response response=UserEndpoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 404);
		
		logger.info("***************user info is displayed*************");
	}
	
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		logger.info("***************updating user*************");
		
		//update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndpoints.updateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 404);
		
		logger.info("***************user is updated*************");
		
		//checking data after updating
		
		Response responseAfterUpdate=UserEndpoints.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 404);
		
	}
	
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		logger.info("***************deleting user*************");
		
		Response response=UserEndpoints.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 404);
		
		logger.info("***************user deleted*************");
	}
	
		

}







