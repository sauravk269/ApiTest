import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class SchemaValidation {

    @Test
    public void validateSchema(){
        String path="C:\\ApiTestEndToEnd\\TestNew\\resources\\schema\\ExpectedSchema.json";
        File schemaFile= new File(path);
      RestAssured.baseURI="https://bookcart.azurewebsites.net";
       given().when()
                .get("/api/Book").then()
               .assertThat().statusCode(200).body(JsonSchemaValidator.matchesJsonSchema(schemaFile));
    }
}
