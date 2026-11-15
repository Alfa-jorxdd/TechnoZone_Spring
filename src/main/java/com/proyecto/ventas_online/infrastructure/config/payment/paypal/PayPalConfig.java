package com.proyecto.ventas_online.infrastructure.config.payment.paypal;

import com.paypal.sdk.Environment;
import com.paypal.sdk.PaypalServerSdkClient;
import com.paypal.sdk.authentication.ClientCredentialsAuthModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PayPalConfig {

    @Value("${paypal.client-id}")
    private String CLIENT_ID;
    @Value("${paypal.secret-key}")
    private String SECRET_KEY;

    @Bean
    public PaypalServerSdkClient paypalServerSdkClient(){
        return new PaypalServerSdkClient.Builder()
                .clientCredentialsAuth(
                        new ClientCredentialsAuthModel.Builder(
                                CLIENT_ID,
                                SECRET_KEY
                        ).build()
                )
                .environment(Environment.SANDBOX)
                .build();
    }
}
