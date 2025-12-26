package com.learn.pos.homework;

import java.util.concurrent.BlockingQueue;

public class BankAuthConsumerBQ implements Runnable{
    private BlockingQueue<String>queue;

    public BankAuthConsumerBQ(BlockingQueue<String>queue){
        this.queue=queue;
    }

    @Override
    public void run(){
        for (int i = 0; i < 3; i++) {
            try {
                String card = queue.take();
                System.out.println("[BankAuth] Consumed card data: " + card);
                PosBlockingQueueDemo.sleep(800);
                System.out.println("[BankAuth] Approved card: " + card);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}
