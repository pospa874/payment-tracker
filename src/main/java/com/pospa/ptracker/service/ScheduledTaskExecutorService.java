package com.pospa.ptracker.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledTaskExecutorService {

    private ScheduledExecutorService scheduledExecutorService;

    private static ScheduledTaskExecutorService instance = new ScheduledTaskExecutorService();

    private ScheduledTaskExecutorService() {
        this.scheduledExecutorService = Executors.newScheduledThreadPool(1);
    }
    
    public static ScheduledTaskExecutorService getInstance() {
        return instance;
    }

    public void runScheduledTask(Runnable task, long delay, long period, TimeUnit timeUnit) {
        scheduledExecutorService.scheduleAtFixedRate(task, delay, period, timeUnit);
    }

    public void shutdown() {
        new ShutdownExecutorsService(scheduledExecutorService).shutdown();
    }
}
