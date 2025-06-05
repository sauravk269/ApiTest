import BookStore.BaseTest;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class OMS_Service extends BaseTest {


@Test
    public void getOrderDetails(ITestContext context) throws IOException, ParseException, InterruptedException {
     String orderId= (String) context.getAttribute("OrderId");
    System.out.println("order id in test class -"+orderId);
     Response response=given().baseUri("http://localhost:9089").when().get("/oms/orders/" +orderId).then().extract().response();
    System.out.println(response.asString());
    String OrderId=response.jsonPath().getString("orderId");
    String status=response.jsonPath().getString("status");
    }
}
