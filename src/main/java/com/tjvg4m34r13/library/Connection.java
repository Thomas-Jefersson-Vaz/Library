/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tjvg4m34r13.library;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author thomas.vaz
 */
public class Connection {
    public static void main(String[] args) throws IOException {
        Request request = new Request()
                .url(BASE_URL + "/date")
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
    }
}
