package com.learn.pos.homework;

public class BankAuthConsumer implements Runnable{
    private CardBuffer buffer;

    public BankAuthConsumer(CardBuffer buffer){
        this.buffer = buffer;
    }

    @Override
    public void run(){
        for (int i = 0; i < 3; i++) {
            String card = buffer.consume("BankAuth");
            PosProducerConsumerDemo.sleep(800);
            System.out.println("[BankAuth] Approved card: " + card);
        }
    }
}
