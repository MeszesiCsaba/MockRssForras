/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mockrssforras;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;
/**
 *
 * @author mcsab
 */
public final class UrlHttpGateway implements HttpGateway {
    @Override
    public HttpResult get(String address) throws SourceException {
        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection)
                    URI.create(address).toURL().openConnection();
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(5000);
            connection.setRequestProperty("User-Agent", "MockRssForras/1.0");

            int status = connection.getResponseCode();
            var stream = status >= 400
                    ? connection.getErrorStream()
                    : connection.getInputStream();

            String body = stream == null
                    ? ""
                    : new String(stream.readAllBytes(), StandardCharsets.UTF_8);

            return new HttpResult(status, body);
        } catch (IOException | IllegalArgumentException e) {
            throw new SourceException("A HTTP-kérés nem sikerült.", e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
