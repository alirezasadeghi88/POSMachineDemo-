package com.learn.pos.homework2;

public class ReceiptPrinterTaskWithCondition implements Runnable{
    private BankApprovalManager manager;
    private String taskName;

    public ReceiptPrinterTaskWithCondition(BankApprovalManager manager, String taskName) {
        this.manager = manager;
        this.taskName = taskName;
    }

    @Override
    public void run() {
        manager.approveBank(taskName);
    }
}
