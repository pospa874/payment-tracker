package com.pospa.test;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Payment {
    
    @NotNull
    @Pattern(regexp = "^[A-Z]{3}$", message = "Invalid currency code. Allowed may be any uppercase 3 letter code")
    private final String currencyCode;

    @NotNull
    private final BigDecimal amount;

    public Payment(String currencyCode, BigDecimal amount) {
        this.currencyCode = currencyCode;
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(currencyCode).append(" ").append(amount).append(System.lineSeparator());
        return stringBuilder.toString();
    }
}
