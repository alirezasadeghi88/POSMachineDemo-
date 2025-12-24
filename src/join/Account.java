package join;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private static Lock lock = new ReentrantLock();
    private static Condition newDeposit = lock.newCondition();
    private int balance = 0;

    public int getBalance(){
        return balance;
    }

    public void withdraw(int amount){
        lock.lock();
        try {
            while (balance < amount){
                System.out.println("\t\t\tمنتظر واریز جدید...");
                newDeposit.await();
            }
            balance -= amount;
            System.out.println("\t\tبرداشت " + amount +"موجودی :"  + getBalance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }
    }

    public void deposit(int amount){
        lock.lock();
        try {
            balance += amount;
            System.out.println("واریز " + amount + " | موجودی: " + getBalance());
            newDeposit.signalAll();
        }finally {
            lock.unlock();
        }
    }
}
