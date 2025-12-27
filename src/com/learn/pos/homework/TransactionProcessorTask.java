package com.learn.pos.homework;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class TransactionProcessorTask extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 3;
    private List<String> transactions;
    private int start;
    private int end;

    public TransactionProcessorTask(List<String> transactions,int start,int end){
        this.transactions = transactions;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if ((end - start) <= THRESHOLD){
            int count = 0;
            for (int i = start; i < end; i++) {
                System.out.println("[THREAD] Processing " + transactions.get(i));
                PosForkJoinDemo.sleep(200);
                count++;
            }
            return count;
        }else {
            int mid = (start + end) / 2;
            TransactionProcessorTask leftTask = new TransactionProcessorTask(transactions,start,mid);
            TransactionProcessorTask rightTask = new TransactionProcessorTask(transactions,mid,end);

            leftTask.fork();
            int rightResult = rightTask.compute();
            int leftResult = leftTask.join();

            return leftResult + rightResult;
        }
    }
}
