package setup;

import io.restassured.RestAssured;
import listener.TestCaseObject;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;


public class BaseTest extends Config {

    @BeforeSuite
    public void setUp() {
        RestAssured.baseURI = BASE_URI;
    }

    @AfterSuite
    public void teardown() {
        RestAssured.reset();
    }
}
