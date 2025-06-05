package userManagement;

import io.restassured.RestAssured;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class GetUser {

    @Test
    public void getUserData() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        given().when()
                .get("/todos/1").then().assertThat().statusCode(200)
                .body(not(isEmptyString()))
                .body("title", equalTo("delectus aut autem"))
                .body("id", equalTo(1));
    }

    @Test
    public void verifyResponseHasItems() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = given().when().get("/posts")
                .then().extract().response();
        // System.out.println(response.asString());
        System.out.println(response.jsonPath().getList("title"));
        assertThat(response.jsonPath().getList("title"), hasItems("nesciunt quas odio", "consequatur id enim sunt et et"));
    }

    @Test
    public void verifyResponseHasSize() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = given().when().get("/comments")
                .then().extract().response();
        assertThat(response.jsonPath().getList(""), hasSize(500));

    }

    @Test
    public void verifyResponseContains() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = given().when().get("/comments")
                .then().extract().response();
        assertThat(response.jsonPath().getList("email"), contains("Jayne_Kuhic@sydney.com"));
    }

    @Test
    public void verifyResponseFirst() {
        RestAssured.baseURI = "http://localhost:8090";
        Response response1 = given().when().get("/products")
                .then().extract().response();
        System.out.println(response1.asString());
        int id = response1.jsonPath().get("data[0].id");
        assertThat(id, equalTo(43900));
    }

    //@Test
   /* public void schemaValidation() {
        RestAssured.baseURI = "http://localhost:8090";
        Response response1 = given().when().get("/products")
                .then().assertThat(JsonSchemaValidator.matchesJsonSchema(""));


    }*/
   /* @Test
    public void schemaValidation() {
        RestAssured.baseURI = "http://localhost:8090";
        Response response1 = given().when().get("/products").then()
                .extract().path("data.findAll{it.id==)



    }*/
    @Test
    public void testQueryParam() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = given().queryParam("email", "Eliseo@gardner.biz")
                .when().get("/comments").then().extract().response();
        assertThat(response.jsonPath().get("name"), hasItem("id labore ex et quam laborum"));
        //System.out.println(response.asString());
    }

    // if post id=1 then get all the names
    @Test
    public void testQueryParam2() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = given().queryParam("postId", 1)
                .when().get("/comments").then().extract().response();
        //System.out.println(response.jsonPath().getList("name"));
        List<String> li = response.jsonPath().getList("name");
        System.out.println(li);
    }
    @Test
    public void testHeader(){
        List<String>li= new ArrayList<>();
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response=given().queryParam("postId",1)
                .when().get("/comments").then().extract().response();
        Headers header= response.getHeaders();
       // System.out.println("Headers are"+response.getHeaders());
        for(Header h:header){
            //System.out.println("using for each"+h);
            System.out.println("Header Name is "+h.getName()+" "+"Header value is "+h.getValue());
         li.add(h.getValue());
        }
        assertThat(li,hasItem("Express"));
        for(Header h:header) {
            if(h.getName().equals("Server")){
            assertEquals(h.getValue(),"cloudflare");
        }}
}
    @Test
    public void testCookie() {
        HashMap<String,String>hm= new HashMap<>();
        hm.put("","");
        hm.put("","");
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = given().cookies(hm).queryParam("postId", 1)
                .when().get("/comments").then().extract().response();
    }
    @Test
    public void extractCookie() {
        HashMap<String,String>hm= new HashMap<>();
        hm.put("","");
        hm.put("","");
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = given().cookies(hm).queryParam("postId", 1)
                .when().get("/comments").then().extract().response();

        Cookies cookies= response.getDetailedCookies();
        System.out.println("Cookie info is "+cookies);


    }
}