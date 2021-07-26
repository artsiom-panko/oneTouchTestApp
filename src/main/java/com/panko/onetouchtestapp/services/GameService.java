package com.panko.onetouchtestapp.services;

import com.panko.onetouchtestapp.tools.Encoder;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPrivateKey;
import java.util.List;

@Component
public class GameService {

    public URI getGameUri(Integer operatorId, Integer gameId, String currency) {
        String result = "Error";

       List<NameValuePair> body = Form.form()
                .add("user", "john12345")
                .add("token", "f562a685-a160-4d17-876d-ab3363db331c")
                .add("sub_partner_id", "my-casino-id")
                .add("operator_id", operatorId.toString())
                .add("operator_id", "10")
                .add("lobby_url", "https://provider.com/casino")
                .add("lang", "ru")
                .add("ip", "142.245.172.168")
                .add("operator_id", gameId.toString())
                .add("display_unit", "mBTC")
                .add("deposit_url", "https://provider.com/deposit")
                .add("currency", currency)
                .add("country", "EE")
                .build();
        try {
            RSAPrivateKey privateKey = Encoder.getPrivateKey("src/main/resources/privateKeyPcks8.pem");
            // TODO Figure out what is the right way to convert body into String for signing
            String signature = Encoder.sign(privateKey, body.toString());

            Request request = prepareRequest(body, signature);

            HttpResponse response = request.execute().returnResponse();
            result = response.getEntity().getContent().toString();
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }

        return URI.create(result);
    }

    private Request prepareRequest(List<NameValuePair> data, String signature) {

        return Request.Post("https://test-platform.onetouch.io/api/operator/generic/v2/game/url")
                .addHeader("X-Signature", signature)
                .addHeader("accept", "application/json")
                .setHeader("Content-type", "application/json")
                .bodyForm(data, StandardCharsets.UTF_8);
    }
}
