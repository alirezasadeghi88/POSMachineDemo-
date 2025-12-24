package join;

public class POSJoinDemo {// POSJoinDemo.java

        public static void main(String[] args) {
            // وظیفه چاپ رسید
            Runnable printReceipt = new PrintTask("چاپ رسید مشتری", 40);

            // وظیفه ثبت تراکنش‌ها
            Runnable logTransactions = new LogTask(50);

            // نخ‌ها
            Thread receiptThread = new Thread(printReceipt);
            Thread logThread = new Thread(logTransactions);

            // تعیین اولویت‌ها
            receiptThread.setPriority(Thread.MAX_PRIORITY); // رسید مهم‌تر است
            logThread.setPriority(Thread.NORM_PRIORITY);    // تراکنش اولویت معمولی دارد

            // اجرای نخ‌ها
            receiptThread.start();

            try {
                // logThread باید منتظر بماند تا receiptThread تمام شود
                receiptThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            logThread.start();
        }
    }




