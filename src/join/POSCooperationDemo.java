package join;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class POSCooperationDemo {
    private static Account account = new Account();

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new DepositTask());
        executor.execute(new WithdrawTask());
        executor.shutdown();
    }

    public static class DepositTask implements Runnable{
        @Override
        public void run(){
            try {
                while (true){
                    int amount = (int)(Math.random() * 10)+ 1;
                    account.deposit(amount);
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static class WithdrawTask implements Runnable{
        @Override
        public void run(){
            while (true){
                int amount = (int)(Math.random()* 10)+ 1;
                account.withdraw(amount);
            }
        }
    }

    public static class Account {
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

}
