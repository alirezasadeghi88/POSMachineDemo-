package com.learn.pos.homework2;

public class PurchaseTaskWithLock implements Runnable{
    private InventoryManagerWithLock manager;
    private String taskName;

    public PurchaseTaskWithLock(InventoryManagerWithLock manager, String taskName) {
        this.manager = manager;
        this.taskName = taskName;
    }

    @Override
    public void run() {
        manager.reduceStock(taskName);
    }
}
