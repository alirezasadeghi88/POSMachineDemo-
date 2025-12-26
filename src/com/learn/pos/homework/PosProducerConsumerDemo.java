package com.learn.pos.homework;

public class PosProducerConsumerDemo {
    public static void main(String[] args) {
        System.out.println("[MAIN] POS started: Producer–Consumer demo...");

        CardBuffer buffer = new CardBuffer();

        Thread producer = new Thread(new CardReaderProducer(buffer));
        Thread consumer = new Thread(new BankAuthConsumer(buffer));

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        }catch (InterruptedException e){
            Thread.currentThread().start();
        }
        System.out.println("[MAIN] Transaction finished. All cards processed.");
    }
    static void sleep(long millis){
        try {
            Thread.sleep(millis);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
