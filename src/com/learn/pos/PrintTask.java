package com.learn.pos;

public class PrintTask implements Runnable{
     private String message;
     private int times;

     public PrintTask(String smg,int t) {
         message = smg;
         times = t;
     }

     @Override
    public void run() {
         for (int i = 0; i <= times; i++) {
             System.out.println(message);
         }
     }
}
