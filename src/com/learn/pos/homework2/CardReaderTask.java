package com.learn.pos.homework2;

public class CardReaderTask implements Runnable{

    @Override
    public void run() {
        System.out.println("[CARD] Reading card...");
        PosRunnableDemo.sleep(300);
        System.out.println("[CARD] Card read OK. Extracting PAN, expiry...");
    }
}
