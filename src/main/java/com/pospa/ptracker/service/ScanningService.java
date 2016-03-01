package com.pospa.ptracker.service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.pospa.ptracker.model.Payment;
import com.pospa.ptracker.repository.Repository;
import com.pospa.ptracker.repository.Storage;
import com.pospa.ptracker.validator.PaymentValidator;

public class ScanningService implements Runnable {

    private Scanner scanner = new Scanner(System.in);

    private String line = "";

    private final static String EXIT = "quit";

    private Repository persistence = new Storage();
    
    @Override
    public void run() {
        while (scanner.hasNext()) {
            line = scanner.nextLine();
            if (EXIT.equals(line.trim())) {
                break;
            }
            Optional<Payment> payment = parsePayment(line.trim());
            payment.ifPresent(persistence::persist);
        }
        scanner.close();
        ScheduledTaskExecutorService.getInstance().shutdown();
    }

    private Optional<Payment> parsePayment(String line) {
        try {
            StringTokenizer st = new StringTokenizer(line);
            String currencyCode = st.nextToken();
            BigDecimal amount = new BigDecimal(st.nextToken());
            Payment payment = new Payment(currencyCode, amount);
            PaymentValidator paymentValidator = new PaymentValidator(payment);
            if (paymentValidator.isValid()) {
                return Optional.of(payment);
            }
        } catch (NumberFormatException nfe) {
            System.err.println("Wrong amount input");
        } catch (NoSuchElementException nsee) {
            System.err.println("Wrong input, probably missing amount");
        }
        return Optional.empty();
    }
}
