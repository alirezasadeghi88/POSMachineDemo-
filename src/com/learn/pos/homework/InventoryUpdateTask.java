package com.learn.pos.homework;

import project.PosRunnableDemo;

public class InventoryUpdateTask implements Runnable{
    @Override
    public void run() {
        System.out.println("[INV] Updating inventory for SKU-987...");
        PosRunnableDemo.sleep(500);
        System.out.println("[INV] Inventory updated: -1 item, new stock=24");
    }
}
