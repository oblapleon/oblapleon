package test.prerequests;

import io.restassured.response.Response;
import jsons.AuthToken;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import setup.BaseTest;

import static io.restassured.RestAssured.given;

public class Prerequests extends BaseTest {

    /**
     * Generating token for some user
     *
     * @param username
     * @param password
     * @return
     */
    public static String generateToken(String username, String password) {
        Response res = given()
                .contentType("application/json")
                .body(AuthToken.authToken_json(username, password))
                .when()
                .post(auth_token);
        return res.getBody().asString();
    }
}
