package com.pospa.test;

import java.util.List;
import javax.validation.Valid;

public interface IPersistence {

    void persist(@Valid Payment payment);

    List<Payment> getAll();

    Payment get(String currencyCode);
}
