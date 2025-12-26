package com.learn.pos.homework;

public class PosRunnableDemo {
    public static void main(String[] args) {
        System.out.println("[MAIN] POS started: beginning transaction...");

        Thread cardReaderThread = new Thread(new CardReaderTask());
        Thread bankAuthThread = new Thread(new BankAuthTask());
        Thread receiptPrinterThread = new Thread(new ReceiptPrinterTask());
        Thread inventoryUpdateThread = new Thread(new InventoryUpdateTask());

        cardReaderThread.start();
        bankAuthThread.start();
        receiptPrinterThread.start();
        inventoryUpdateThread.start();

        try {
            bankAuthThread.join();
            receiptPrinterThread.join();
        } catch (InterruptedException e) {
            System.out.println("[MAIN] Interrupted while waiting: " + e.getMessage());
            Thread.currentThread().interrupt();
        }

        System.out.println("[MAIN] Transaction closed. Background tasks may still finish...");

        try {
            cardReaderThread.join();
            inventoryUpdateThread.join();
        } catch (InterruptedException e) {
            System.out.println("[MAIN] Interrupted while finalizing: " + e.getMessage());
            Thread.currentThread().interrupt();
        }

        System.out.println("[MAIN] POS ready for next transaction.");
    }

     static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println("[THREAD] Sleep interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
