package stepDefinitions;

import java.io.IOException;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataPayload;
import resources.Utils;

public class StepDefinition extends Utils {
	Response response;
	Response getresponse;
	RequestSpecification res;
	ResponseSpecification resspec;
	TestDataPayload data = new TestDataPayload();
	static String placeid;
	String namefromresponse;

	@Given("Add Place Payload with {string}  {string} {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address, String phonenumber, String website)
			throws IOException {
		res = given().spec(requestSpecification())
				.body(data.AddPlacepayload(name, language, address, phonenumber, website));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpmethod) {

		APIResources resourceAPI = APIResources.valueOf(resource);
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		System.out.println(resource);
		response = res.when().post(resourceAPI.getResource());
	}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {

		res.then().spec(resspec);
		assertEquals(response.getStatusCode(), 200);
		System.out.println(response.asString());
		String responsee = response.asString();
		JsonPath json = new JsonPath(responsee);
		placeid = json.getString("place_id");
		namefromresponse = json.getString("name");
		System.out.println(placeid);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String Expectedvalue) {
		assertEquals(getJsonPath(response, keyValue), Expectedvalue);
	}

	@Given("^Add placeid as queryparameter$")
	public void add_placeid_as_queryparameter() throws IOException {

		res = given().spec(requestSpecification()).queryParam("place_id", placeid);
	}

	@When("^user call \"([^\"]*)\" with \"([^\"]*)\" http request$")
	public void user_calls_something_with_something_http_request(String resource1, String method) throws Throwable {
		APIResources resourceapi = APIResources.valueOf(resource1);
		System.out.println(resource1);
		getresponse = res.when().get(resourceapi.getResource());
		String getres = getresponse.asString();
		System.out.println(getres);
		JsonPath json = new JsonPath(getres);
		namefromresponse = json.getString("name");
	}

	@Then("^verify name in response body is \"([^\"]*)\"$")
	public void verify_something_in_response_body_is(String strArg1) throws Throwable {

		System.out.println(namefromresponse);
		System.out.println(strArg1);
		assertEquals(namefromresponse, strArg1);
	}
}
