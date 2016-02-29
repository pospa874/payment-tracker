package com.pospa.ptracker.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class ShutdownExecutorsService {
    
    private ExecutorService executorService;

    public ShutdownExecutorsService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void shutdown() {
        try {
            executorService.shutdown();
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("Unable to shutdown thread pool");
        } finally {
            if (!executorService.isTerminated()) {
                System.err.println("Force shutdown non-finished tasks");
            }
            executorService.shutdownNow();
            System.out.println("Shutdown finished");
        }
    }

}
