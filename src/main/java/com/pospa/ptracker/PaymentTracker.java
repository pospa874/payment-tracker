package com.pospa.ptracker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.pospa.ptracker.persistence.IPersistence;
import com.pospa.ptracker.persistence.Storage;
import com.pospa.ptracker.service.ScanningService;
import com.pospa.ptracker.service.ScheduledExecutorServiceImpl;
import com.pospa.ptracker.service.ShutdownExecutorsService;

public class PaymentTracker {

    public static void main(String[] args) {
        System.out.println("Payment tracker started");

        ScheduledExecutorServiceImpl scheduledExecutorService = ScheduledExecutorServiceImpl.getInstance();
        IPersistence storage = new Storage();
        Runnable task = () -> storage.getAll().forEach(System.out::println);
        scheduledExecutorService.runScheduledTask(task, 1, 5, TimeUnit.SECONDS);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        final Future<?> future = executorService.submit(new ScanningService());
        while (!future.isDone()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        new ShutdownExecutorsService(executorService).shutdown();
    }
}
