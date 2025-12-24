package com.learn.pos;

public class POSMachineDemo {
    public static void main(String[] args) {

        Runnable checkBalance = new PrintTask("بررسی موجودی حساب",30);

        Runnable sendSMS = new PrintTask("ارسال پیامک تراکنش",30);

        Runnable logTransaction = new LogTask(50);

        Thread thread1 = new Thread(checkBalance);
        Thread thread2 = new Thread(sendSMS);
        Thread thread3 = new Thread(logTransaction);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
