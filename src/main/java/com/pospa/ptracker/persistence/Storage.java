package com.pospa.ptracker.persistence;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.pospa.ptracker.model.Payment;

public class Storage implements IPersistence {

    private static ConcurrentMap<String, BigDecimal> storageMap = new ConcurrentHashMap<>(200);

    @Override
    public void persist(Payment payment) {
        BigDecimal storeAmount = payment.getAmount();
        final String currencyCode = payment.getCurrencyCode();
        if (storageMap.containsKey(currencyCode)) {
            BigDecimal amountBefore = storageMap.get(currencyCode);
            storeAmount = amountBefore.add(storeAmount);
        }
        storageMap.put(currencyCode, storeAmount);
    }

    @Override
    public List<Payment> getAll() {
        List<Payment> allCurrencies = new ArrayList<>(storageMap.size());
        storageMap.forEach((currency, amount) -> {
            if (BigDecimal.ZERO.compareTo(amount) != 0) {
                allCurrencies.add(new Payment(currency, amount));
            }
        });
        return allCurrencies;
    }

    @Override
    public Payment get(String currencyCode) {
        if (storageMap.containsKey(currencyCode)) {
            BigDecimal amount = storageMap.get(currencyCode);
            return new Payment(currencyCode, amount);
        }
        return null;
    }
}
