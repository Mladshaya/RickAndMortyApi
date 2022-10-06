package ReqResApi;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class ReqResTest {

    @Test
    public void createUser() throws IOException {

        JSONObject body = new JSONObject(new String(Files.readAllBytes(Paths.get("src/test/json/reqres.json"))));
        body.put("name", "Tomato");
        body.put("job", "Eat maket");

        RequestSpecification request = given();
        request
                .baseUri("https://reqres.in/")
                .header("Content-type", "application/json");

        Response response = request
                .body(body.toString())
                .post("api/users")
                .then()
                .statusCode(201)
                .log().all()
                .extract().response();

        String expectedName = response.body().jsonPath().get("name").toString();
        String expectedJob = response.body().jsonPath().get("job").toString();
        assertEquals("Tomato", expectedName);
        assertEquals("Eat maket", expectedJob);
    }
}
