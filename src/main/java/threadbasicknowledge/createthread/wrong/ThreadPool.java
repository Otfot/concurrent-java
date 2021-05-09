package threadbasicknowledge.createthread.wrong;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 实现线程池创建线程，非创建线程本质
 * @author otfot
 * @date 2021/05/09
 */
public class ThreadPool {

    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            es.submit(new Task());
        }

    }
}

class Task implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " wake up.");
    }
}
