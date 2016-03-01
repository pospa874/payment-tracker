package com.pospa.ptracker.repository;

import java.util.List;
import java.util.Optional;

import com.pospa.ptracker.model.Payment;

public interface Repository {

    void persist(Payment payment);

    List<Payment> getAll();

    Optional<Payment> get(String currencyCode);
}
