package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//Created to perform create, read, update, delete requests to the user api
public class UserEndPoints2 {
	
	static ResourceBundle getURL() {
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
	}
	
	public static Response createUser(User payload)
	{
		String postUrl = getURL().getString("postURL");
		Response response = given()
								.contentType(ContentType.JSON)
								.accept(ContentType.JSON)
								.body(payload)
							.when()
								.post(postUrl);
		return response;
	}
	
	public static Response readUser(String username)
	{
		String getUrl = getURL().getString("getUrl");
		Response response = given()
								.pathParam("username", username)
							.when()
								.get(getUrl);
		return response;
	}
	
	public static Response updateUser(String username, User payload)
	{
		String updateUrl = getURL().getString("updateUrl");
		Response response = given()
								.contentType(ContentType.JSON)
								.accept(ContentType.JSON)
								.pathParam("username", username)
								.body(payload)
							.when()
								.put(updateUrl);
		return response;
	}
	
	public static Response deleteUser(String username)
	{
		String deleteUrl = getURL().getString("deleteUrl");
		Response response = given()
								.pathParam("username", username)
							.when()
								.delete(deleteUrl);
		return response;
	}

}
