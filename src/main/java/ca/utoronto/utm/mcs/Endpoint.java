package ca.utoronto.utm.mcs;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONException;

import java.io.IOException;

public abstract class Endpoint implements HttpHandler {
    public void handle(HttpExchange r) {
        try {
            if (r.getRequestMethod().equals("POST")) {
                this.handlePost(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract void handlePost(HttpExchange r) throws IOException, JSONException;
}
