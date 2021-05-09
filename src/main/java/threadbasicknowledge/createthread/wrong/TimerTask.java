package threadbasicknowledge.createthread.wrong;

import java.util.Timer;

/**
 * 通过定时器创建线程
 * @author otfot
 * @date 2021/05/09
 */
public class TimerTask {

    public static void main(String[] args) {
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new java.util.TimerTask() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }, 1000, 1000);
    }
}
