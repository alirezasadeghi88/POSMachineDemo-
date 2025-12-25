package project;


class CardReaderTask implements Runnable {
    @Override
    public void run() {
                System.out.println("[CARD] Reading card...");
                 PosRunnableDemo.sleep(300);
                System.out.println("[CARD] Card read OK. Extracting PAN, expiry...");
    }
}

class BankAuthTask implements Runnable {
    @Override
    public void run() {
                System.out.println("[BANK] Sending auth request to bank...");
                PosRunnableDemo.sleep(800);
                System.out.println("[BANK] Bank auth approved. Auth code: 123456");
    }
}


class ReceiptPrinterTask implements Runnable {
    @Override
    public void run() {
                System.out.println("[PRINT] Preparing receipt...");
                PosRunnableDemo.sleep(200);
                System.out.println("[PRINT] Printing merchant copy...");
                PosRunnableDemo.sleep(400);
                System.out.println("[PRINT] Printing customer copy...");
    }
}


class InventoryUpdateTask implements Runnable {
    @Override
    public void run() {
                System.out.println("[INV] Updating inventory for SKU-987...");
                PosRunnableDemo.sleep(500);
                System.out.println("[INV] Inventory updated: -1 item, new stock=24");
    }
}

public class PosRunnableDemo {
    public static void main(String[] args) {
                System.out.println("[MAIN] POS started: beginning transaction...");

                Thread cardReaderThread = new Thread(new CardReaderTask());
                Thread bankAuthThread = new Thread(new BankAuthTask());
                Thread receiptPrinterThread = new Thread(new ReceiptPrinterTask());
                Thread inventoryUpdateThread = new Thread(new InventoryUpdateTask());

                cardReaderThread.start();
                bankAuthThread.start();
                receiptPrinterThread.start();
                inventoryUpdateThread.start();

                try {
                 bankAuthThread.join();
                 receiptPrinterThread.join();
                } catch (InterruptedException e) {
                    System.out.println("[MAIN] Interrupted while waiting: " + e.getMessage());
                    Thread.currentThread().interrupt();
                }

                System.out.println("[MAIN] Transaction closed. Background tasks may still finish...");

                try {
                    cardReaderThread.join();
                    inventoryUpdateThread.join();
                } catch (InterruptedException e) {
                    System.out.println("[MAIN] Interrupted while finalizing: " + e.getMessage());
                    Thread.currentThread().interrupt();
                }
                System.out.println("[MAIN] POS ready for next transaction.");
    }

        static void sleep(long millis) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                System.out.println("[THREAD] Sleep interrupted: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
}

