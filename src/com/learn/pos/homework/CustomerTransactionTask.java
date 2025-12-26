package com.learn.pos.homework;

public class CustomerTransactionTask implements Runnable{
    private ResourceManager manager;
    private String taskName;

    public CustomerTransactionTask(ResourceManager manager, String taskName) {
        this.manager = manager;
        this.taskName = taskName;
    }

    @Override
    public void run() {
        manager.processTransaction(taskName);
    }
}
