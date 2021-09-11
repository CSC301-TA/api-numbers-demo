package ca.utoronto.utm.mcs;

import java.io.IOException;
import java.io.OutputStream;

import org.json.*;

import com.sun.net.httpserver.HttpExchange;

public class Add extends Endpoint
{
    private static Memory memory;

    public Add(Memory mem) {
        memory = mem;
    }

    public void handleGet(HttpExchange r) throws IOException, JSONException {
        String body = Utils.convert(r.getRequestBody());
        JSONObject deserialized = new JSONObject(body);

        long first = memory.getValue();
        long second = memory.getValue();

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

        /* TODO: Implement the math logic */
        long answer = first + second;
        System.out.println(first+","+second+","+answer);
        String response = Long.toString(answer) + "\n";
        r.sendResponseHeaders(200, response.length());
        OutputStream os = r.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public void handlePost(HttpExchange r) throws IOException, JSONException{
        /* TODO: Implement this.
           Hint: This is very very similar to the get just make sure to save
                 your result in memory instead of returning a value.*/
        String body = Utils.convert(r.getRequestBody());
        JSONObject deserialized = new JSONObject(body);

        long first = memory.getValue();
        long second = memory.getValue();

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
                first = deserialized.getLong("secondNumber");
            } catch(Exception e) {
                String response = "Second number is not a numeric value \n";
                r.sendResponseHeaders(400, response.length());
                OutputStream os = r.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }

        /* TODO: Implement the math logic */
        long answer = first + second;
        memory.setValue(answer);

        r.sendResponseHeaders(200, -1);
    }
}
