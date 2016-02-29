package com.pospa.ptracker.model;

import java.math.BigDecimal;

public class Payment {
    
    private final String currencyCode;
    
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
        return currencyCode + " " + amount;
    }
}
