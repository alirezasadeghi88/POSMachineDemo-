package com.learn.pos.homework;

public class PosThreadControlDemo {
    public static void main(String[] args) {
        System.out.println("[MAIN] POS started: beginning transaction...");

        Thread cardReaderThread = new Thread(new CardReaderTask(), "CardReader");
        Thread bankAuthThread = new Thread(new BankAuthTask(), "BankAuth");
        Thread receiptPrinterThread = new Thread(new ReceiptPrinterTask(), "ReceiptPrinter");
        Thread inventoryUpdateThread = new Thread(new InventoryUpdateTask(), "InventoryUpdate");

        bankAuthThread.setPriority(Thread.MAX_PRIORITY);
        receiptPrinterThread.setPriority(Thread.NORM_PRIORITY);
        inventoryUpdateThread.setPriority(Thread.MIN_PRIORITY);

        cardReaderThread.start();
        bankAuthThread.start();
        receiptPrinterThread.start();
        inventoryUpdateThread.start();

        System.out.println("[MAIN] BankAuth alive? " + bankAuthThread.isAlive());
        System.out.println("[MAIN] ReceiptPrinter alive? " + receiptPrinterThread.isAlive());

        try {
            bankAuthThread.join();
            receiptPrinterThread.join();
        }catch (InterruptedException e) {
            System.out.println("[MAIN] Interrupted while waiting: " + e.getMessage());
            Thread.currentThread().interrupt();
        }

        if (inventoryUpdateThread.isAlive()) {
            System.out.println("[MAIN] Customer canceled transaction. Interrupting inventory update...");
            inventoryUpdateThread.interrupt();
        }

        System.out.println("[MAIN] Transaction closed. POS ready for next transaction.");
    }
}
