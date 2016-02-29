package com.pospa.ptracker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.validation.ValidationException;

import com.pospa.ptracker.persistence.IPersistence;
import com.pospa.ptracker.persistence.Storage;
import com.pospa.ptracker.service.ScanningService;
import com.pospa.ptracker.service.ScheduledExecutorServiceImpl;

public class PaymentTracker {

    public static void main(String[] args) throws ValidationException {
        System.out.println("Payment tracker started");

        ScheduledExecutorServiceImpl scheduledExecutorService = ScheduledExecutorServiceImpl.getInstance();
        IPersistence storage = new Storage();
        Runnable task = () -> storage.getAll().forEach(System.out::println);
        scheduledExecutorService.runScheduledTask(task, 1, 5, TimeUnit.SECONDS);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new ScanningService());
        executorService.shutdown();
    }
}
