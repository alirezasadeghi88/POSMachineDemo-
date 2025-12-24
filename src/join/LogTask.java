package join;

public class LogTask implements Runnable{
    private int lastNum;

    public LogTask(int n) {
        lastNum = n;
    }

    @Override
    public void run(){
        for (int i = 1; i <= lastNum; i++) {
            System.out.println("ثبت تراکنش شماره " + i);
        }
    }
}
