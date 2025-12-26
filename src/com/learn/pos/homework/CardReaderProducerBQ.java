package com.learn.pos.homework;

import java.util.concurrent.BlockingQueue;

public class CardReaderProducerBQ implements Runnable{
    private BlockingQueue<String>queue;

    public CardReaderProducerBQ(BlockingQueue<String>queue){
        this.queue=queue;
    }

    @Override
    public void run(){
        String[] cards = {"Card-1111", "Card-2222", "Card-3333"};
        for (String card : cards){
            PosBlockingQueueDemo.sleep(500);
            try {
                queue.put(card);
                System.out.println("[CardReader] Produced card data: " + card);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
