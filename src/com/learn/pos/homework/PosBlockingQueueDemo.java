package com.learn.pos.homework;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PosBlockingQueueDemo {
    public static void main(String[] args) {
        System.out.println("[MAIN] POS started: BlockingQueue demo...");

        BlockingQueue<String> cradQueue = new ArrayBlockingQueue<>(2);

        Thread producer = new Thread(new CardReaderProducerBQ(cradQueue));
        Thread consumer = new Thread(new BankAuthConsumerBQ(cradQueue));

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
    static void sleep(long millis){
        try {
            Thread.sleep(millis);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
