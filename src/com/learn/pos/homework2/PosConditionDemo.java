package com.learn.pos.homework2;

public class PosConditionDemo {
    public static void main(String[] args) {
        System.out.println("[MAIN] POS started: testing Condition communication...");

        BankApprovalManager manager = new BankApprovalManager();

        Thread bankThread = new Thread(new BankAuthTaskWithCondition(manager, "BankAuth"));
        Thread printThread = new Thread(new ReceiptPrinterTaskWithCondition(manager, "ReceiptPrinter"));

        printThread.start();
        bankThread.start();

        try {
            bankThread.join();
            printThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("[MAIN] Transaction finished. Receipt printed after bank approval.");

    }
    static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
