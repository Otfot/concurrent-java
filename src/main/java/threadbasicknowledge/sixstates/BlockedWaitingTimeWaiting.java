package threadbasicknowledge.sixstates;

import java.sql.SQLOutput;

/**
 * 带有三种线程状态， BLOCKED、WAITING、TIMEDWAITING
 * @author otfot
 * @date 2021/05/10
 */
public class BlockedWaitingTimeWaiting implements Runnable{

    public static void main(String[] args) throws InterruptedException {
        BlockedWaitingTimeWaiting r = new BlockedWaitingTimeWaiting();
        Thread t1 = new Thread(r);
        t1.start();
        Thread t2 = new Thread(r);
        t2.start();
        System.out.println("t1:" + t1.getState());

        System.out.println("t2:" + t2.getState());
        Thread.sleep(1000);
        System.out.println("t1:" + t1.getState());
        System.out.println("t2:" + t2.getState());
        Thread.sleep(1000);
        System.out.println("t2:" + t2.getState());

    }

    @Override
    public void run() {
        syn();
    }

    private synchronized void syn() {
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
