package com.learn.pos.homework2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PosThreadPoolDemo {
    public static void main(String[] args) {
        System.out.println("[MAIN] POS started: beginning transaction...");

        ExecutorService executor = Executors.newFixedThreadPool(4);

        executor.execute(new CardReaderTask());
        executor.execute(new BankAuthTask());
        executor.execute(new ReceiptPrinterTask());
        executor.execute(new InventoryUpdateTask());

        executor.shutdown();

        System.out.println("[MAIN] Transaction submitted to thread pool. Waiting for completion...");
    }
}
