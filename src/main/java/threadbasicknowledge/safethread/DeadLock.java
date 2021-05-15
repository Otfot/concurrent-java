package threadbasicknowledge.safethread;

import java.util.concurrent.TimeUnit;

/**
 * 展示死锁
 * @author otfot
 * @date 2021/05/12
 */
public class DeadLock {



    public static void main(String[] args) {
        final Object res1 = new Object();
        final Object res2 = new Object();

        Runnable r1 = ()  -> {
            synchronized (res1) {
                System.out.println(Thread.currentThread().getName() + "get res1");
                System.out.println(Thread.currentThread().getName() + "try acquire res2");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (res2) {
                    try {
                        TimeUnit.MINUTES.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Runnable r2 = ()  -> {
            synchronized (res2) {
                System.out.println(Thread.currentThread().getName() + "get res2");
                System.out.println(Thread.currentThread().getName() + "try acquire res1");

                synchronized (res1) {
                    try {
                        TimeUnit.MINUTES.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        new Thread(r1).start();
        new Thread(r2).start();


    }


}
