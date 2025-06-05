package BookStore;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import utils.ConstantsData;
import utils.JsonReader;
import utils.PropertyFileReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class createUser extends BaseTest{


    @Test(priority=1)
    public void createUserData(ITestContext context) throws IOException, ParseException {
        test.info("Create user");
        PropertyFileReader reader= new PropertyFileReader();
        String userData= JsonReader.getTestData(ConstantsData.FIlePath,"userName");
        String Password=JsonReader.getTestData(ConstantsData.FIlePath,"password");
        test.info("Passing url");
        String url=reader.get("BaseUri");
        System.out.println("User Name that we are getting from json -"+userData);
        String randomString= UUID.randomUUID().toString().substring(0,4);
        Map<String,String>hm= new HashMap<>();
        Map<String,String>body= new HashMap<>();
        hm.put("Content-Type","application/json");
        body.put("userName",userData+randomString);
        body.put("password",Password);
       RestAssured.baseURI=url;
        test.info("Extracting response");
        Response response=given().headers(hm).body(body).when()
                .post("/Account/v1/User")
                .then().extract().response();
        System.out.println(response.asString());
        String userId= response.jsonPath().getString("userID");
        String userName= response.jsonPath().getString("username");
        System.out.println("userid is -"+userId);
        test.info("userId is -"+userId);
        System.out.println("userName is -"+userName);
        test.info("userName is -"+userName);
        context.setAttribute("userId", userId);
        context.setAttribute("username", userName);
        System.out.println(randomString);
    }


    @Test(priority=2)
    public void generateToken(ITestContext context){
        test.info("Generate token");
        Map<String,Object>body1= new HashMap<>();
        body1.put("userName",context.getAttribute("username"));
        body1.put("password","Password@123");
        Response response=given().header("accept","application/json")
                .header("Content-Type","application/json")
                .body(body1).when().post("https://demoqa.com/Account/v1/GenerateToken").then().extract().response();
        System.out.println(response.asString());
        String token= response.jsonPath().getString("token");
        test.info("Token is -"+token);
        context.setAttribute("Token",token);
        String status= response.jsonPath().getString("status");
        System.out.println("Status is -"+status);
       assertEquals(status,"Success");
    }

    @Test(priority=3)
    public void getCreatedUserID(ITestContext context){
        test.info("Get user id");
        System.out.println("Token from attribute "+context.getAttribute("Token"));
        System.out.println("userid -"+context.getAttribute("userId"));
        Response response=given().header("Authorization","Bearer "+context.getAttribute("Token"))
                .pathParams("userId",context.getAttribute("userId"))
                .when().get("https://demoqa.com/Account/v1/User/{userId}").then().extract().response();
        System.out.println(response.asString());
    }
    @Test(priority=4)
    public void deleteCreatedUser(ITestContext context){
        test.info("Delete created user");
        System.out.println("Token from attribute "+context.getAttribute("Token"));
        System.out.println("userid -"+context.getAttribute("userId"));
        Response response=given().header("Authorization","Bearer "+context.getAttribute("Token"))
                .pathParams("userId",context.getAttribute("userId"))
                .when().delete("https://demoqa.com/Account/v1/User/{userId}").then().extract().response();
        System.out.println(response.statusCode());
        assertEquals(response.statusCode(),204);
    }

}
