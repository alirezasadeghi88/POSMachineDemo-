package com.learn.pos.homework2;

public class PosLockDemo {
    public static void main(String[] args) {
        System.out.println("[MAIN] POS started: testing Lock-based inventory...");

        InventoryManagerWithLock manager = new InventoryManagerWithLock();

        Thread t1 = new Thread(new PurchaseTaskWithLock(manager, "Customer-1"));
        Thread t2 = new Thread(new PurchaseTaskWithLock(manager, "Customer-2"));
        Thread t3 = new Thread(new PurchaseTaskWithLock(manager, "Customer-3"));
        Thread t4 = new Thread(new PurchaseTaskWithLock(manager, "Customer-4"));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("[MAIN] Transaction finished. Final stock checked.");

    }
    static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
