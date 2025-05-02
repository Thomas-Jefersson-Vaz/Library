package com.tjvg4m34r13.library;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import okhttp3.*;

import java.io.IOException;


/**
 * @author thomas.vaz
 */

public class Connection {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    public static String resStatus = null;
    OkHttpClient client = new OkHttpClient();
    public Boolean success = false;
    private String baseUrl = "https://library.thomasjeferssonvaz.dev.br/api/";

    public Connection(String url, String json) throws IOException {
        String completeUrl = this.baseUrl+url;
        this.post(completeUrl, json);
    }
    public Connection(String url) throws IOException {
        String completeUrl = this.baseUrl+url;
        this.get(completeUrl);
    }

    private Boolean testStatusString(String text) {
        JsonElement jsonReceived = null;
        try {
            jsonReceived = JsonParser.parseString(text);
            String jsonReceivedAsString = jsonReceived.toString();
            String correctStatus = "{\"Status\":\"On\"}";
            return jsonReceivedAsString.equals(correctStatus);
        } catch (JsonSyntaxException e) {
            return false;
        }

    }

    public void get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        try (Response response = client.newCall(request).execute();){
            String responseString = response.body().string();
            if(testStatusString(responseString)){
                this.success = true;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String responseString = response.body().string();
            System.out.println(responseString);
            this.success = true;
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}
