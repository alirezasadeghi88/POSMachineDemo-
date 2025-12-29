package com.learn.pos.homework2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CardBuffer {
    private String cardData;
    private boolean available = false;
    private final Lock lock = new ReentrantLock();
    private final Condition dataAvailable = lock.newCondition();
    private final Condition bufferEmpty = lock.newCondition();

    public void produce(String data, String threadName) {
        lock.lock();
        try {
            while (available) {
                bufferEmpty.await();
            }
            cardData = data;
            available = true;
            System.out.println("[" + threadName + "] Produced card data: " + data);
            dataAvailable.signal();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public String consume(String threadName) {
        lock.lock();
        try {
            while (!available) {
                dataAvailable.await();
            }
            String data = cardData;
            available = false;
            System.out.println("[" + threadName + "] Consumed card data: " + data);
            bufferEmpty.signal();
            return data;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } finally {
            lock.unlock();
        }
    }
}