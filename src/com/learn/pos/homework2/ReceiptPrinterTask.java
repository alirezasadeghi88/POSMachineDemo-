package com.learn.pos.homework2;

public class ReceiptPrinterTask implements Runnable{
    @Override
    public void run() {
        System.out.println("[PRINT] Preparing receipt...");
        PosRunnableDemo.sleep(200);
        System.out.println("[PRINT] Printing merchant copy...");
        PosRunnableDemo.sleep(400);
        System.out.println("[PRINT] Printing customer copy...");
    }
}
