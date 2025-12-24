package com.learn.pos;

public class POSMachineDemo {
    public static void main(String[] args) {

        Runnable checkBalance = new PrintTask("بررسی موجودی حساب",2);

        Runnable sendSMS = new PrintTask("ارسال پیامک تراکنش",2);

        Runnable logTransaction = new LogTask(2);

        Thread thread1 = new Thread(checkBalance);
        Thread thread2 = new Thread(sendSMS);
        Thread thread3 = new Thread(logTransaction);

        thread1.run();
        thread2.run();
        thread3.run();
    }
}
