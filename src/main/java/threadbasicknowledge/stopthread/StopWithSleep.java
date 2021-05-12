package threadbasicknowledge.stopthread;

/**
 * 中断 run 方法中被阻塞的异常
 * @author otfot
 * @date 2021/05/09
 */
public class StopWithSleep {

    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            int num = 0;
            while(num <=300 && !Thread.currentThread().isInterrupted()) {
                if (num % 100 == 0) {
                    System.out.println(num + "是 100 的倍数。");
                }
                num++;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread t = new Thread(r);
        t.start();
        Thread.sleep(500);
        t.interrupt();
    }
}
