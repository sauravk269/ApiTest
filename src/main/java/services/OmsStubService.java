package services;

import org.testng.ITestContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static services.WireMockManager.wiremockServer;

public class OmsStubService {

    public static void configureOms(ITestContext context) throws IOException {
        String daynamiOrderId = "order_12" + UUID.randomUUID().toString().substring(0, 2);
        System.out.println("Order id is "+daynamiOrderId);
        context.setAttribute("OrderId", daynamiOrderId);
        // configureOms(daynamiOrderId);
        String template = new String(Files.readAllBytes(Path.of("C:\\ApiTestEndToEnd\\TestNew\\src\\test\\resources\\oms_order_response.json")));
        String responseBody = template.replace("{{ORDER_ID}}", daynamiOrderId);
        wiremockServer.stubFor(get(urlEqualTo("/oms/orders/" + daynamiOrderId))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseBody)));
    }
}
