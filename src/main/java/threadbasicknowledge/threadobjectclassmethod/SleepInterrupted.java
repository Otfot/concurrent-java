package threadbasicknowledge.threadobjectclassmethod;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 每 1 秒打印当前时间
 * @author otfot
 * @date 2021/05/11
 */
public class SleepInterrupted implements Runnable {


    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(new Date());
            try {
                // 写法更优雅
                TimeUnit.HOURS.sleep(2);
                TimeUnit.MINUTES.sleep(20);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
//                System.out.println(Thread.currentThread().isInterrupted());
            }
        }
    }

    public static void main(String[] args) {
        SleepInterrupted r = new SleepInterrupted();
        Thread t = new Thread(r);
        t.start();
        t.interrupt();
        System.out.println(t.isInterrupted());
    }
}
