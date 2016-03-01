package com.pospa.ptracker.validator;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.pospa.ptracker.model.Payment;

public class PaymentValidatorTest {
        
    private PaymentValidator paymentValidator;

    @Test
    public void testIsValid() {
        //given
        Payment payment = new Payment("CZK", BigDecimal.TEN);
        paymentValidator = new PaymentValidator(payment);
        
        //when
        boolean result = paymentValidator.isValid();
        
        //then
        assertTrue(result);
    }

    @Test
    public void testInvalidCurrencyCodePattern() {
        //given
        Payment payment = new Payment("CZLK", BigDecimal.TEN);
        paymentValidator = new PaymentValidator(payment);

        //when
        boolean result = paymentValidator.isValid();

        //then
        assertFalse(result);
    }

    @Test
    public void testInvalidCurrencyCodeNotNull() {
        //given
        Payment payment = new Payment(null, BigDecimal.TEN);
        paymentValidator = new PaymentValidator(payment);

        //when
        boolean result = paymentValidator.isValid();

        //then
        assertFalse(result);
    }

    @Test
    public void testInvalidAmountNotNull() {
        //given
        Payment payment = new Payment("USD", null);
        paymentValidator = new PaymentValidator(payment);

        //when
        boolean result = paymentValidator.isValid();

        //then
        assertFalse(result);
    }
}