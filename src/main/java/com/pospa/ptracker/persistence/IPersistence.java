package com.pospa.ptracker.persistence;

import java.util.List;
import java.util.Optional;

import com.pospa.ptracker.model.Payment;

public interface IPersistence {

    void persist(Payment payment);

    List<Payment> getAll();

    Optional<Payment> get(String currencyCode);
}
