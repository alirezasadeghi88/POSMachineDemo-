package com.learn.pos.homework;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankApprovalManager {
    private boolean approved = false;
    private final Lock lock = new ReentrantLock();
    private final Condition bankApprovedCondition = lock.newCondition();

    public void approveBank(String threadName){
        lock.lock();
        try {
            System.out.println("[" + threadName + "]Sending auth request to bank...");
            PosConditionDemo.sleep(800);
            approved = true;
            System.out.println("[" + threadName + "] Bank auth approved!");
            bankApprovedCondition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void printReceipt(String threadName){
        lock.lock();
        try {
            while (!approved){
                System.out.println("[" + threadName + "] Waiting for bank approval...");
                bankApprovedCondition.await();
            }
            System.out.println("[" + threadName + "] Printing receipt...");
            PosConditionDemo.sleep(500);
            System.out.println("[" + threadName + "] Receipt printed successfully.");
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }finally {
            lock.unlock();
        }
    }
}
