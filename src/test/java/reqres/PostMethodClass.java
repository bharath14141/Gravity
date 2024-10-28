package reqres;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostMethodClass {
	
	
	
	@Test
	
public static void postMethod() {
	JSONObject newdata = new JSONObject();
	newdata.put("name", "Barath");
	newdata.put("job","API Tester");
	
	RestAssured.baseURI="https://reqres.in";
	RequestSpecification resquestSpecification = RestAssured.given();
	resquestSpecification.header("Content-Type", "application/json");
	resquestSpecification.body(newdata.toJSONString());
	Response Response = resquestSpecification.request(Method.POST,"/api/users");
	System.out.println(Response.asString());
	
	int AcutualStatusCode =Response.statusCode();
    Assert.assertEquals(AcutualStatusCode,201);
	System.out.println("Status Code Verify after hard assert"); 
	if(AcutualStatusCode==201) {
		System.out.println("Status code Verify");
	}
	
	String AcutualheaderResponse =Response.header("Content-Type");
	if(AcutualheaderResponse.equals("application/json; charset=utf-8")) {
		System.out.println("Content-type Verify");
	}
	else {
		System.out.println("ConTent-Type mismatch");
	}
	JsonPath CompleteResponseBody = Response.jsonPath();
	String uniqueID =CompleteResponseBody.getString("id").toString();
	System.out.println(uniqueID);
}

}
