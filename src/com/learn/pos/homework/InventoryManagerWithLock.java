package com.learn.pos.homework;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class InventoryManagerWithLock {
    private int stock = 10;
    private final Lock lock = new ReentrantLock();

    public void reduceStok(String threadName) {
        lock.lock();
        try {
            if (stock > 0){
                System.out.println("[" + threadName + "] Reducing stock...");
                PosLockDemo.sleep(200);
                stock--;
                System.out.println("[" + threadName + "] Stock reduced. Current stock = " + stock);
            }else {
                System.out.println("[" + threadName + "] No stock left!");
            }
        }finally {
            lock.unlock();
        }
    }
}
