package ca.utoronto.utm.mcs;

import junit.framework.Test;
import junit.framework.TestCase;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

    private static HttpResponse<String> sendRequest(String endpoint, String method, String reqBody) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8004" + endpoint))
                .method(method, HttpRequest.BodyPublishers.ofString(reqBody))
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());

    }

    @Test
    public void confirm200() throws JSONException, IOException, InterruptedException {
        JSONObject confirmReq = new JSONObject()
                .put("firstNumber", 1)
                .put("secondNumber", 1);
        HttpResponse<String> confirmRes = sendRequest("/api/addTwoNumbers", "GET", confirmReq.toString());
        assertEquals(HttpURLConnection.HTTP_OK, confirmRes.statusCode());
    }

}
