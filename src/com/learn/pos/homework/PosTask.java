package com.learn.pos.homework;

public class PosTask implements Runnable{
    private String taskName;

    public PosTask(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println("[" + taskName + "] Running...");
        PosThreadLifeCycleDemo.sleep(500); // شبیه‌سازی کار
        System.out.println("[" + taskName + "] Finished.");
    }
}
