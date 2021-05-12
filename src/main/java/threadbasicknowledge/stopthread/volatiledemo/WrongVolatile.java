package threadbasicknowledge.stopthread.volatiledemo;

/**
 * volatile boolean 来判断中断
 * @author otfot
 * @date 2021/05/09
 */
public class WrongVolatile implements  Runnable{

    private volatile boolean canceled = false;


    @Override
    public void run() {
        int num = 0;
        try {
            while (num < 10000 && !canceled) {
                if (num % 100 == 0) {
                    System.out.println(num);
                }
                num++;
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WrongVolatile w = new WrongVolatile();
        Thread t = new Thread(w);
        t.start();
        Thread.sleep(1000);
        w.canceled = true;
    }
}
