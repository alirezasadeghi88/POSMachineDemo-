package com.learn.pos.homework2;

public class InventoryManager {
    private int stock = 10;

    public synchronized void reduceStock(String threadName){
        if (stock > 0) {
            System.out.println("[" + threadName + "] Reducing stock...");
            PosSyncDemo.sleep(200);
            stock--;
            System.out.println("[" + threadName + "] Stock reduced. Current stock = " + stock);
        }else {
            System.out.println("[" + threadName + "] No stock left!");
        }
    }
}
