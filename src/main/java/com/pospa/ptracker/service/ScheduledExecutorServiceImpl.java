package com.pospa.ptracker.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceImpl {

    private ScheduledExecutorService scheduledExecutorService;

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
        new ShutdownExecutorsService(scheduledExecutorService).shutdown();
    }
}
