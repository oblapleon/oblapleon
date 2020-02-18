package test;

import io.restassured.http.Header;
import io.restassured.response.Response;
import jsons.SubmitReport;
import setup.TestCaseObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import setup.BaseTest;
import test.prerequests.Prerequests;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;

/**
 * POST http://mysite.com/api/submit_report/, требующий авторизацию, а именно header “Authorization”,
 * который будет содержать “token here_comes_token” и принимает json с полями “priority” с допустимыми значениями от 1 до 5 и “report”,
 * являющееся обязательным текстовым полем.
 */
public class SubmitReportTest extends BaseTest {


    @DataProvider(name = "data-provider")
    public Object[] authentication_token_test() {
        String title1 = "Check report submitted priority value 1. [positive]";
        String title2 = "Check report submitted priority value 5. [positive]";
        String title3 = "Check report submitted priority value 0. [negative]";
        String title4 = "Check report submitted priority value 6. [negative]";
        String title5 = "Check report not submitted in case with report field not string. [negative]";
        String title6 = "Check report not submitted in case with empty token. [negative]";
        String title7 = "Check report not submitted in case with empty body. [negative]";

        Header header_valid_token = new Header("Authorization", Prerequests.generateToken(valid_username, valid_password));
        Header header_invalid_token = new Header("Authorization", "");
        String endpoint = submit_report;
        String json_positive_1 = SubmitReport.submitReport_json(1, "some report");
        String json_positive_2 = SubmitReport.submitReport_json(5, "any other report");
        String json_negative_1 = SubmitReport.submitReport_json(0, "some_report");
        String json_negative_2 = SubmitReport.submitReport_json(6, "any other report");
        String json_negative_3 = SubmitReport.submitReport_json_negative(1, 4000000);

        return new Object[]{
                new TestCaseObject(title1, header_valid_token, endpoint, json_positive_1, SC_OK),
                        new TestCaseObject(title2, header_valid_token, endpoint, json_positive_2, SC_OK),
                        new TestCaseObject(title3, header_valid_token, endpoint, json_negative_1, SC_BAD_REQUEST),
                        new TestCaseObject(title4, header_valid_token, endpoint, json_negative_2, SC_BAD_REQUEST),
                        new TestCaseObject(title5, header_valid_token, endpoint, json_negative_3, SC_BAD_REQUEST),
                        new TestCaseObject(title6, header_invalid_token, endpoint, json_positive_1, SC_FORBIDDEN),
                        new TestCaseObject(title7, header_valid_token, endpoint, "", SC_BAD_REQUEST)
                };
    }


    @Test(dataProvider = "data-provider")
    public void submit_report_test(TestCaseObject testCaseObject) {

        Response res = given()
                .relaxedHTTPSValidation()
                .contentType("application/json").log().everything()
                .header(testCaseObject.getHeader())
                .body(testCaseObject.getRequestBody())
                .when()
                .post(testCaseObject.getRequestEndpoint());

        Assert.assertEquals(testCaseObject.getResponseStatusCode(), res.then().extract().statusCode());
    }
}
