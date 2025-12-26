package com.learn.pos.homework;

public class PosSemaphoreDemo {
    public static void main(String[] args) {
        System.out.println("[MAIN] POS started: testing Semaphore for printer...");

        ReceiptPrinterWithSemaphore printer = new ReceiptPrinterWithSemaphore();

        Thread t1 = new Thread(new CustomerPrintTask(printer, "Customer-1"));
        Thread t2 = new Thread(new CustomerPrintTask(printer, "Customer-2"));
        Thread t3 = new Thread(new CustomerPrintTask(printer, "Customer-3"));

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("[MAIN] All receipts printed safely with Semaphore.");
    }
    static void sleep(long millis){
        try {
            Thread.sleep(millis);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
