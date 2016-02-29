package com.pospa.ptracker.service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

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
                scanner.close();
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
            StringTokenizer st = new StringTokenizer(line);
            String currencyCode = st.nextToken();
            BigDecimal amount = new BigDecimal(st.nextToken());
            Payment payment = new Payment(currencyCode, amount);
            PaymentValidator paymentValidator = new PaymentValidator(payment);
            if (paymentValidator.isValid()) {
                return payment;
            }
        } catch (NumberFormatException nfe) {
            System.err.println("Wrong amount input");
        } catch (NoSuchElementException nsee) {
            System.err.println("Wrong input, probably missing amount");
        }
        return null;
    }
}
