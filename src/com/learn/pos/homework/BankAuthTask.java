package com.learn.pos.homework;

import project.PosRunnableDemo;

public class BankAuthTask implements Runnable{
    @Override
    public void run() {
        System.out.println("[BANK] Sending auth request to bank...");
        PosRunnableDemo.sleep(300);
        System.out.println("[BANK] Bank auth approved. Auth code: 123456");
    }
}
