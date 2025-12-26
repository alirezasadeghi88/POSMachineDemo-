package com.learn.pos.homework;

public class PosDeadlockDemo {
    public static void main(String[] args) {
        System.out.println("[MAIN] POS started: testing Resource Ordering...");

        ResourceManager manager = new ResourceManager();

        Thread t1 = new Thread(new CustomerTransactionTask(manager, "Customer-1"));
        Thread t2 = new Thread(new CustomerTransactionTask(manager, "Customer-2"));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("[MAIN] All transactions finished safely without deadlock.");

    }
    static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
