package com.pospa.ptracker.repository;

import static java.math.BigDecimal.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.pospa.ptracker.model.Payment;

public class Storage implements Repository {

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
            if (ZERO.compareTo(amount) != 0) {
                allCurrencies.add(new Payment(currency, amount));
            }
        });
        return allCurrencies;
    }

    @Override
    public Optional<Payment> get(String currencyCode) {
        if (storageMap.containsKey(currencyCode)) {
            BigDecimal amount = storageMap.get(currencyCode);
            return Optional.of(new Payment(currencyCode, amount));
        }
        return Optional.empty();
    }
}
