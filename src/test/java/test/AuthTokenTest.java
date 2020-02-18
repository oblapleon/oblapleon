package test;

import io.restassured.response.Response;
import jsons.AuthToken;
import setup.TestCaseObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import setup.BaseTest;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;


/**
 * POST http://mysite.com/api/auth/,
 * который принимает json с полями username и password и при успешной авторизации возвращает 200 OK с телом {“token”: “here_comes_token”}
 */
public class AuthTokenTest extends BaseTest {

    @DataProvider(name = "data-provider")
    public Object[]  authentication_token_test() {
        String title1 = "Check token received for permitted user. [positive]";
        String title2 = "Check token not received for not permitted user. [negative]";
        String title3 = "Check token not received in case with empty body. [negative]";

        String endpoint = auth_token;

        String json_body_positive = AuthToken.authToken_json(valid_username, valid_password);
        String json_body_negative_1 = AuthToken.authToken_json(wrong_username, wrong_password);
        String json_body_negative_2 = AuthToken.authToken_json("", "");
        ArrayList<String> response = new ArrayList<>();
        response.add("here_comes_token");
        return new Object[]{
                new TestCaseObject(title1, endpoint, json_body_positive, SC_OK, response),
                new TestCaseObject(title2, endpoint, json_body_negative_1, SC_BAD_REQUEST),
                new TestCaseObject(title3, endpoint, json_body_negative_2, SC_BAD_REQUEST)
        };
    }


    @Test(dataProvider = "data-provider")
    public void authentication_token_test(TestCaseObject testCaseObject) {
        Response res = given().relaxedHTTPSValidation()
                .contentType("application/json").log().everything()
                .body(testCaseObject.getRequestBody())
                .when()
                .post(testCaseObject.getRequestEndpoint());
        Assert.assertEquals(testCaseObject.getResponseStatusCode(), res.then().extract().statusCode());
        Assert.assertEquals(testCaseObject.getResponseBody().toString(), res.then().extract().body().asString());
    }
}