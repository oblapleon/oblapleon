package listener;

import io.restassured.http.Header;

import java.util.ArrayList;

public class TestCaseObject {

    private String title;
    private String requestEndpoint;
    private String requestBody;
    private int responseStatusCode;
    private ArrayList<String> responseBody;
    private Header header;


    public TestCaseObject(String title, String requestEndpoint, String requestBody, int responseStatusCode, ArrayList<String> responseBody) {
        this.title = title;
        this.requestEndpoint = requestEndpoint;
        this.requestBody = requestBody;
        this.responseStatusCode = responseStatusCode;
        this.responseBody = responseBody;
    }

    public TestCaseObject(String title, String requestEndpoint, String requestBody, int responseStatusCode) {
        this.title = title;
        this.requestEndpoint = requestEndpoint;
        this.requestBody = requestBody;
        this.responseStatusCode = responseStatusCode;
    }

    public TestCaseObject(String title, Header header, String requestEndpoint, String requestBody, int responseStatusCode, ArrayList<String> responseBody) {
        this.title = title;
        this.header = header;
        this.requestEndpoint = requestEndpoint;
        this.requestBody = requestBody;
        this.responseStatusCode = responseStatusCode;
        this.responseBody = responseBody;
    }

    public TestCaseObject(String title, Header header, String requestEndpoint, String requestBody, int responseStatusCode) {
        this.title = title;
        this.header = header;
        this.requestEndpoint = requestEndpoint;
        this.requestBody = requestBody;
        this.responseStatusCode = responseStatusCode;
    }

    public Header getHeader() {
        return header;
    }

    public String getTitle() {
        return title;
    }
    public String getRequestEndpoint() {
        return requestEndpoint;
    }

    public int getResponseStatusCode() {
        return responseStatusCode;
    }

    public ArrayList<String> getResponseBody() {
        return responseBody;
    }

    public String getRequestBody() {
        return requestBody;
    }
}
