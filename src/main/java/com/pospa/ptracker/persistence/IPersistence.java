package com.pospa.ptracker.persistence;

import java.util.List;

import com.pospa.ptracker.model.Payment;

public interface IPersistence {

    void persist(Payment payment);

    List<Payment> getAll();

    Payment get(String currencyCode);
}
