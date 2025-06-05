import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class PathFinder {

    @Test(priority = 1)
    public void Test1() {
        Response response =
                given().when().get("https://bookcart.azurewebsites.net/api/Book")
                        .then().extract().response();
        // System.out.println(response.asString());
        //Fetch all the titles

        List<String> titles = response.jsonPath().getList("title");
        Iterator<String> itr = titles.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }

    @Test(priority = 2)
    public void Test2() {
        Response response =
                given().when().get("https://bookcart.azurewebsites.net/api/Book")
                        .then().extract().response();
        List<String> titles = response.jsonPath().getList("findAll{it.author=='JKR'}.title");
        System.out.println(titles);
    }
}
