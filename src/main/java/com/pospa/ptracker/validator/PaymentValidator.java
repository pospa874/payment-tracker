package com.pospa.ptracker.validator;

import static java.util.regex.Pattern.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.pospa.ptracker.model.Payment;

public class PaymentValidator {

    private List<String> errorsList = new ArrayList<>(5);

    private Payment payment;

    public PaymentValidator(Payment payment) {
        this.payment = payment;
    }

    public boolean isValid() {
        validate(payment);
        return errorsList.isEmpty();
    }

    private void validate(Payment payment) {
        checkNotNull(payment.getCurrencyCode(), "currencyCode may not be null");
        checkNotNull(payment.getAmount(), "amount may not be null");
        checkPattern(payment.getCurrencyCode(), compile("^[A-Z]{3}$"),
                "Invalid currency code. Allowed may be any uppercase 3 letter code");
        errorsList.forEach(System.err::println);
    }

    private void checkNotNull(Object object, String error) {
        if (object == null) {
            errorsList.add(error);
        }
    }

    private void checkPattern(String value, Pattern pattern, String error) {
        if (value != null) {
            if (!pattern.matcher(value).matches()) {
                errorsList.add(error);
            }
        }

    }
}
