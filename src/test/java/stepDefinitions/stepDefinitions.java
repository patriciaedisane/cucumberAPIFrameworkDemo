package stepDefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.requestSpecification;
import static org.junit.Assert.*;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.resourceAPI;
import resources.testDataBuild;
import resources.utilities;

import static io.restassured.RestAssured.given;

@RunWith(Cucumber.class)
public class stepDefinitions extends utilities {

    //RequestSpecification req;
    ResponseSpecification resSpec;
    Response response;
    RequestSpecification res;
    //String r;
    String status;
    String place_Id;


    utilities getJsonValue = new utilities();


    // Create an object for testDataBuild class to be able to access it
    testDataBuild data = new testDataBuild();


    @Given("^Add Place Payload is available with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void addPlacePayloadIsAvailableWith(String arg0, String arg1, String arg2) throws Throwable {
        RestAssured.baseURI="https://rahulshettyacademy.com";

        res = given().log().all().spec(requestSpecificationBuild()).body(data.addPlacePayLoad(arg0,arg1, arg2));


    }

    @When("^User calls \"([^\"]*)\" with \"([^\"]*)\" http request$")
    public void userCallsWithHttpRequest(String resource, String request) throws Throwable {

        // Constructor will be called with value of resource passed in this method.
        resourceAPI resources = resourceAPI.valueOf(resource);

        resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        // This checks the request being passed in this method, calls that particular request from enum class to get the
        // resource
        if(request.equalsIgnoreCase("POST"))
            response = res.when().post(resources.getResource());
        else if (request.equalsIgnoreCase("GET"))
            response = res.when().get(resources.getResource());
        else if (request.equalsIgnoreCase("Delete"))
            response = res.when().delete(resources.getResource());

        /*
        response = res.when().log().all().post(resources.getResource())
                .then().log().all().spec(resSpec).extract().response();

         */

    }

    @Then("^The API call is sucessful with status code (\\d+)$")
    public void the_api_call_is_sucessful_with_status_code_200(int arg0) throws Throwable {
        //assertEquals(response.getStatusCode(),200);
        assertEquals(200,response.getStatusCode());
        //throw new PendingException();
    }


    @And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
    public void inResponseBodyIs(String arg0, String arg1) throws Throwable {

        // Getting the value stored in "status" field from JSON generated
        // Verify the value of status field.
        status = getJsonValue.getJsonPath(response,"status");
        System.out.println(status);
        assertEquals(status, arg1);



    }

    @And("^place_Id mapped to \"([^\"]*)\" is captured to be used in \"([^\"]*)\" with \"([^\"]*)\" request$")
    public void place_idIsCapturesMappedToToBeUsedIn(String name, String resource, String request) throws Throwable {

        // Capture placeId
        place_Id = getJsonValue.getJsonPath(response,"place_id");
        System.out.println(place_Id);

        // Prepare a request specification
        res = given().spec(requestSpecificationBuild()).queryParam("place_id", place_Id);

        //call userCallsWithHTTTRequest method
        userCallsWithHttpRequest(resource, request);


        // get the name
        String n = getJsonValue.getJsonPath(response, "name");

        //Check if name retrieved is correctly mapped to the place Id generated

        if (n.equalsIgnoreCase(name)) {
            System.out.println("Name retrieved: " + n + " matches the name passed: " + name + " || place id is: " + place_Id);
        } else {
            System.out.println("Names don't match");
        }

    }


    @Given("^Delete Place Payload is available for \"([^\"]*)\"$")
    public void deletePlacePayloadIsAvailableFor(String placeIdValue) throws Throwable {
        // Build request spec
        res = given().log().all().spec(requestSpecificationBuild()).body(data.deletePlacePayLoad(placeIdValue));

        // Delete process
        userCallsWithHttpRequest("DeletePlaceAPI", "Delete");

        System.out.println("Place id number: " + placeIdValue + " has been DELETED");






    }

}