package ca.utoronto.utm.mcs;

import java.io.IOException;
import java.io.OutputStream;

import org.json.*;

import com.sun.net.httpserver.HttpExchange;

public class Add extends Endpoint {

    public void handlePost(HttpExchange r) throws IOException, JSONException{
        String body = Utils.convert(r.getRequestBody());
        JSONObject deserialized = new JSONObject(body);

        long first = 0, second = 0;

        if (deserialized.has("firstNumber")) {
            try {
                first = deserialized.getLong("firstNumber");
            } catch(Exception e) {
                String response = "First number is not a numeric value \n";
                r.sendResponseHeaders(400, response.length());
                OutputStream os = r.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }

        if (deserialized.has("secondNumber")) {
            try {
                second = deserialized.getLong("secondNumber");
            } catch(Exception e) {
                String response = "Second number is not a numeric value \n";
                r.sendResponseHeaders(400, response.length());
                OutputStream os = r.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
        long answer = first + second;
        String response = Long.toString(answer) + "\n";
        r.sendResponseHeaders(200, response.length());
        OutputStream os = r.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
