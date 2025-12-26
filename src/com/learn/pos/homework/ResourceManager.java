package com.learn.pos.homework;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ResourceManager {
    private final Lock bankLock = new ReentrantLock();
    private final Lock printLock = new ReentrantLock();

    public void processTransaction(String threadName){
        bankLock.lock();
        try {
            System.out.println("[" + threadName + "] Acquired Bank connection...");
            PosDeadlockDemo.sleep(300);

            printLock.lock();
            try {
                System.out.println("[" + threadName + "] Acquired Bank connection...");
                PosDeadlockDemo.sleep(300);
                System.out.println("[" + threadName + "] Transaction processed and receipt printed.");
            }finally {
                printLock.unlock();
            }

        }finally {
            bankLock.unlock();
        }
    }
}
