package org.example.project4.WebPacket.configuration;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PayPalConfig {
    @org.springframework.beans.factory.annotation.Value("${paypal.client.id}")
    private String clientId;

    @org.springframework.beans.factory.annotation.Value("${paypal.client.secret}")
    private String clientSecret;

    @org.springframework.beans.factory.annotation.Value("${paypal.mode}")
    private String mode;

    @Bean
    public APIContext apiContext() throws PayPalRESTException {
        return new APIContext(clientId, clientSecret, mode);
    }
}