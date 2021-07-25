package com.panko.onetouchtestapp;

import com.panko.onetouchtestapp.tools.Encoder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@SpringBootTest
class EncoderTests {

    @Test
    void testValidSignatureGeneration() throws GeneralSecurityException, IOException {
        final String expectedSignature = "bL7uNP1K3S0HG8IOC0A5Gf/Cl+Hs3YCVfA0ZrjPgGJFnOstxshCQHB7JbeBhTEDhsqd6CFj4U5xOjzselFkO1HhFrTWssB7CNiXaNmizYp2NKuZhkJcrTswVlk8z9NzAkYJfcqnXiC6lMX1X5t6/+dOX6rvLlHM7yfo9LzhVjKo1on9JMHoW8AiYcC8clKEqpyWTQ70euGXnqxRay5RVAmD1sxOlmz8VIX5irtpMOugNDIL1G3g4IgauPk8T2IfVierOFeALQrNx88Es6Dl8Bgb9ogm1W4xgL3Ve01p59DQNt0oorm0LZt/YqkWYGLL2lpd5Qb1FiX4O7+hfyPKN1Q==";
        RSAPrivateKey privateKey = Encoder.getPrivateKey("src/test/java/resources/privateKeyTestPcks8.pk8");

        String requestBody = "{\"user\":\"3nYTOSjdlF6UTz9Ir\",\"country\":\"XX\",\"currency\":\"BTC\",\"operator_id\":1,\"token\":\"cd6bd8560f3bb8f84325152101adeb45\",\"platform\":\"GPL_DESKTOP\",\"game_id\":39,\"lang\":\"en\",\"lobby_url\":\"https://examplecasino.io\",\"ip\":\"::ffff:10.0.0.39\"}";
        String actualSignature = null;

        try {
            actualSignature = Encoder.sign(privateKey, requestBody);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(expectedSignature, actualSignature);
    }

    @Test
    void testEncryptAndDecryptData() throws GeneralSecurityException, IOException {
        final String data = "Test message for encrypt";
        RSAPrivateKey privateKey = Encoder.getPrivateKey("src/main/resources/privateKeyPcks8.pem");
        String signature = Encoder.sign(privateKey, data);

        RSAPublicKey publicKey = Encoder.getPublicKey("src/main/resources/publicKeyOriginal.pem");

        Assertions.assertTrue(Encoder.verify(publicKey, data, signature));
    }
}
