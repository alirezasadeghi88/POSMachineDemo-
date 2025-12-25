package project;


public class PosOverview {

    public static void main(String[] args) {
                System.out.println("[MAIN] POS started: beginning transaction...");

                Thread cardReaderThread = new Thread(() -> {
                        System.out.println("[CARD] Reading card...");
                        sleep(300);
                        System.out.println("[CARD] Card read OK. Extracting PAN, expiry...");
                });

                Thread bankAuthThread = new Thread(() -> {
                        System.out.println("[BANK] Sending auth request to bank...");
                        sleep(800);
                        System.out.println("[BANK] Bank auth approved. Auth code: 123456");
                });

                Thread receiptPrinterThread = new Thread(() -> {
                       System.out.println("[PRINT] Preparing receipt...");
                        sleep(200);
                        System.out.println("[PRINT] Printing merchant copy...");
                       sleep(400);
                        System.out.println("[PRINT] Printing customer copy...");
                });

                Thread inventoryUpdateThread = new Thread(() -> {
                        System.out.println("[INV] Updating inventory for SKU-987...");
                        sleep(500);
                        System.out.println("[INV] Inventory updated: -1 item, new stock=24");
                });

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

        private static void sleep(long millis) {
        try {
                        Thread.sleep(millis);
        } catch (InterruptedException e) {
                        System.out.println("[THREAD] Sleep interrupted: " + e.getMessage());
                        Thread.currentThread().interrupt();
        }
    }
}

