package day01;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

public class HTTPRequests {
	private int id;

	@Test(priority = 1)
	public void getUsers() {
		given().when().get("https://reqres.in/api/users?page=2").then().statusCode(200).body("page", equalTo(2)).log()
				.all();
	}

	@Test(priority = 2)
	public void createUser() {
		HashMap<String, String> data = new HashMap<>();
		data.put("name", "vimal 0");
		data.put("job", "student 0");

		id = given().contentType("application/json").body(data).when().post("https://reqres.in/api/users").jsonPath()
				.getInt("id");

//				.then()
//				.statusCode(201).log().all();
	}

	@Test(priority = 3, dependsOnMethods = { "createUser" })
	public void updateUser() {
		HashMap<String, String> data = new HashMap<>();
		data.put("name", "vimal 2");
		data.put("job", "student 2");

		given().contentType("application/json").body(data).when().put("https://reqres.in/api/users/" + id).then()
				.statusCode(200).log().all();
	}

	@Test(priority = 4)
	public void deleteUser() {
		given().when().delete("https://reqres.in/api/users/" + id).then().statusCode(204).log().all();
	}
}
