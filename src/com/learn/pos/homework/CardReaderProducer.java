package com.learn.pos.homework;

public class CardReaderProducer implements Runnable{
    private CardBuffer buffer;

    public CardReaderProducer(CardBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run(){
        String[] cards = {"Card-1111", "Card-2222", "Card-3333"};
        for (String card : cards) {
            PosProducerConsumerDemo.sleep(500);
            buffer.produce(card,"CardReader");
        }
    }
}
