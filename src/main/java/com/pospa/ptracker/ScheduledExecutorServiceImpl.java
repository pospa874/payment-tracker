package com.pospa.ptracker;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceImpl {

    ScheduledExecutorService scheduledExecutorService;

    private static ScheduledExecutorServiceImpl instance = new ScheduledExecutorServiceImpl();

    private ScheduledExecutorServiceImpl() {
        this.scheduledExecutorService = Executors.newScheduledThreadPool(1);
    }
    
    public static ScheduledExecutorServiceImpl getInstance() {
        return instance;
    }

    public void runScheduledTask(Runnable task, long delay, long period, TimeUnit timeUnit) {
        scheduledExecutorService.scheduleAtFixedRate(task, delay, period, timeUnit);
    }

    public void shutdown() {
        try {
            scheduledExecutorService.shutdown();
            scheduledExecutorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("Unable to shutdown thread pool");
        } finally {
            if (!scheduledExecutorService.isTerminated()) {
                System.err.println("Force shutdown non-finished tasks");
            }
            scheduledExecutorService.shutdownNow();
            System.out.println("Shutdown finished");
        }
    }
}
