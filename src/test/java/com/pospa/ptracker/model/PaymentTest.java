package com.pospa.ptracker.model;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class PaymentTest {

    @Test
    public void testToString() {
        //given
        Payment payment = new Payment("CZK", BigDecimal.valueOf(123L));

        //when
        String result = payment.toString();

        //then
        Assert.assertEquals("CZK 123", result);
    }
}