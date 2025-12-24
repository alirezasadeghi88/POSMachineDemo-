package join;

public class DepositTask implements Runnable{
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
