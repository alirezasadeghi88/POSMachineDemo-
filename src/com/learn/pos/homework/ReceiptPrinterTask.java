package com.learn.pos.homework;

import project.PosRunnableDemo;

public class ReceiptPrinterTask implements Runnable{
    @Override
    public void run() {
        System.out.println("[PRINT] Preparing receipt...");
        PosRunnableDemo.sleep(300);
        System.out.println("[PRINT] Printing merchant copy...");
        PosRunnableDemo.sleep(300);
        System.out.println("[PRINT] Printing customer copy...");
    }
}
