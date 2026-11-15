package com.proyecto.ventas_online.infrastructure.adapter.out.payment.credit_cart.sdk;

import com.proyecto.ventas_online.infrastructure.adapter.out.payment.credit_cart.sdk.exeptions.InvalidAmountCreditCartException;
import com.proyecto.ventas_online.infrastructure.adapter.out.payment.credit_cart.sdk.exeptions.InvalidFormatBankAccountException;
import com.proyecto.ventas_online.infrastructure.adapter.out.payment.credit_cart.sdk.exeptions.InvalidFormatDNIException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class CreditCartSdk {
    public String pay(String bankAccount, String dni, BigDecimal amount){
        if (!bankAccount.contains("^[0-9]{10}$")){
            throw new InvalidFormatBankAccountException(bankAccount);
        }
        if (!dni.contains("^[0-9]{8}$")){
            throw new InvalidFormatDNIException(dni);
        }
        if (amount.signum() == -1 || amount.signum() == 0){
            throw new InvalidAmountCreditCartException();
        }
        return generarTransaccion();
    }

    private String generarTransaccion(){
        return String.valueOf(ThreadLocalRandom.current().nextInt(1,999) + 9000);
    }
}
