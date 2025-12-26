package com.learn.pos.homework;

public class PurchaseTask implements Runnable{
    private InventoryManager manager;
    private String taskName;

    public PurchaseTask(InventoryManager manager,String taskName){
        this.manager = manager;
        this.taskName = taskName;
    }

    @Override
    public void run() {
        manager.reduceStock(taskName);
    }
}
