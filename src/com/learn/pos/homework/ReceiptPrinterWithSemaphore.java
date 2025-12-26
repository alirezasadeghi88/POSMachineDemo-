package com.learn.pos.homework;

import java.util.concurrent.Semaphore;

public class ReceiptPrinterWithSemaphore {
    private final Semaphore semaphore = new Semaphore(1);

    public void printReceipt(String threadName){
        try {
            semaphore.acquire();
            System.out.println("[" + threadName + "] Accessing printer...");
            PosSemaphoreDemo.sleep(500);
            System.out.println("[" + threadName + "] Receipt printed.");
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }finally {
            semaphore.release();
        }
    }
}
