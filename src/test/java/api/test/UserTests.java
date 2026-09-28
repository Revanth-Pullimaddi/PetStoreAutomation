package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User userPayload;
	
	@BeforeClass
	public void setUpDta() 
	{
		faker = new Faker();
		userPayload =  new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());		
	}
	
	@Test(priority = 1)
	public void testPostUser()
	{
		Response response = UserEndPoints.createUser(userPayload);
		//Response response = UserEndPoints2.createUser(userPayload);
		response.then().log().all();		
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority=2)
	public void testGetUserByName() 
	{
		//Response response = UserEndPoints2.readUser(this.userPayload.getUsername());
		Response response = UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 3)
	public void testUpdateUser()
	{
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		
		Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		//Response response = UserEndPoints2.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//Checking data after update
		Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
		//Response responseAfterUpdate = UserEndPoints2.readUser(this.userPayload.getUsername());
		responseAfterUpdate.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=4)
	public void testdeleteUserByName() 
	{
		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		//Response response = UserEndPoints2.deleteUser(this.userPayload.getUsername());
		response.then().log().all();	
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
}
