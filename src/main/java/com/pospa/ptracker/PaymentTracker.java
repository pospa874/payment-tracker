package com.pospa.ptracker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.pospa.ptracker.repository.Repository;
import com.pospa.ptracker.repository.Storage;
import com.pospa.ptracker.service.ScanningService;
import com.pospa.ptracker.service.ScheduledTaskExecutorService;
import com.pospa.ptracker.service.ShutdownExecutorsService;

public class PaymentTracker {

    public static void main(String[] args) {
        System.out.println("Payment tracker started. Insert currency code and amount:");

        ScheduledTaskExecutorService scheduledExecutorService = ScheduledTaskExecutorService.getInstance();
        Repository storage = new Storage();
        Runnable task = () -> storage.getAll().forEach(System.out::println);
        scheduledExecutorService.runScheduledTask(task, 1, 1, TimeUnit.MINUTES);

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
