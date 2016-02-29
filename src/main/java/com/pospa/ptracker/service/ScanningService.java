package com.pospa.ptracker.service;

import java.math.BigDecimal;
import java.util.Scanner;

import com.pospa.ptracker.model.Payment;
import com.pospa.ptracker.persistence.IPersistence;
import com.pospa.ptracker.persistence.Storage;
import com.pospa.ptracker.validator.PaymentValidator;

public class ScanningService implements Runnable {

    private Scanner scanner = new Scanner(System.in);

    private String line = "";

    private final static String EXIT = "quit";

    private IPersistence persistence = new Storage();

    @Override
    public void run() {
        while (scanner.hasNext()) {
            line = scanner.nextLine();
            if (EXIT.equals(line.trim())) {
                ScheduledExecutorServiceImpl.getInstance().shutdown();
                break;
            }
            Payment payment = parsePayment(line.trim());
            if (payment != null) {
                persistence.persist(payment);
            }
        }
    }

    private Payment parsePayment(String line) {
        try {
            String currencyCode = line.substring(0, 3).trim();
            BigDecimal amount = new BigDecimal(line.substring(3).trim());
            Payment payment = new Payment(currencyCode, amount);
            PaymentValidator paymentValidator = new PaymentValidator(payment);
            if (paymentValidator.isValid()) {
                return payment;
            }
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
        return null;
    }
}
