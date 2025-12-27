package com.learn.pos.homework;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class PosForkJoinDemo {
    public static void main(String[] args) {
        System.out.println("[MAIN] POS started: Fork/Join demo...");

        List<String> transactions = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            transactions.add("Transaction-" + i);
        }

        ForkJoinPool pool = new ForkJoinPool();

        TransactionProcessorTask task = new TransactionProcessorTask(transactions,0,transactions.size());
        int processedCount = pool.invoke(task);

        System.out.println("[MAIN] Total transactions processed: " + processedCount);
    }
    static void sleep(long millis){
        try {
            Thread.sleep(millis);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
