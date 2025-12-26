package com.learn.pos.homework;

public class CustomerPrintTask implements Runnable{
    private ReceiptPrinterWithSemaphore printer;
    private String taskName;

    public CustomerPrintTask(ReceiptPrinterWithSemaphore printer,String taskName){
        this.printer = printer;
        this.taskName = taskName;
    }

    @Override
    public void run(){
        printer.printReceipt(taskName);
    }
}
