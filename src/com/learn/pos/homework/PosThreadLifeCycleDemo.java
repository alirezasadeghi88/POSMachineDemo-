package com.learn.pos.homework;

public class PosThreadLifeCycleDemo {
    public static void main(String[] args) {
        System.out.println("[MAIN] POS started: Thread Life Cycle demo...");

        Thread cardReaderThread = new Thread(new PosTask("CardReader"));
        System.out.println("[MAIN] State after creation (NEW): " + cardReaderThread.getState());

        cardReaderThread.start();
        System.out.println("[MAIN] State after start (RUNNABLE): " + cardReaderThread.getState());

        PosThreadLifeCycleDemo.sleep(200);
        System.out.println("[MAIN] State during sleep (TIMED_WAITING): " + cardReaderThread.getState());

        try {
            cardReaderThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("[MAIN] State after completion (TERMINATED): " + cardReaderThread.getState());

        System.out.println("[MAIN] Thread Life Cycle demo finished.");

    }
    static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
