package com.proyecto.ventas_online.infrastructure.adapter.out.payment.yape.sdk;

import com.proyecto.ventas_online.infrastructure.adapter.out.payment.yape.sdk.exeptions.InvalidAmountExeption;
import com.proyecto.ventas_online.infrastructure.adapter.out.payment.yape.sdk.exeptions.InvalidFormatNumberPhoneException;
import com.proyecto.ventas_online.infrastructure.adapter.out.payment.yape.sdk.exeptions.MaxAmountException;
import kotlin.concurrent.ThreadsKt;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class YapeSdk {
    private String transactionId;

    public String yapear(String phoneNumber, BigDecimal amount){
        if (!phoneNumber.contains("^9[0-9]{8}$")){
            throw new InvalidFormatNumberPhoneException(phoneNumber);
        }
        if (amount.signum() == -1 || amount.signum() == 0){
            throw new InvalidAmountExeption(amount);
        }
        BigDecimal maxAmount = new BigDecimal("1000");
        if (amount.compareTo(maxAmount) > 0){
            throw new MaxAmountException();
        }

        this.transactionId = generarTransaccion();
        return transactionId;
    }

    private String generarTransaccion(){
        return String.valueOf(ThreadLocalRandom.current().nextInt(1,999) + 7000);
    }
}
