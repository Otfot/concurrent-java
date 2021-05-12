package threadbasicknowledge.stopthread.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 线程被阻塞无法 通过 volatile boolean 中断
 * 生产者、消费者，生产者 阻塞队列满了后 生产者就被阻塞
 * @author otfot
 * @date 2021/05/09
 */
public class WrongVolatileCantStop {


    public static void main(String[] args) throws InterruptedException {
        WrongVolatileCantStop w = new WrongVolatileCantStop();
        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);
        Producer p = new Producer(storage);
        Consumer c = new Consumer(storage);


        Thread pro = new Thread(p);
        pro.start();
        Thread.sleep(1000);

        while(c.needMoreNums()) {
            c.storage.take();
            System.out.println("消费");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要，生产者需要停止");
        // 无法停止，因为此刻生产者 run 方法中被阻塞在 storage.put 处
        // 无法到达 while 判断也就无法停止
        p.canceled = true;
    }
}


class Producer implements  Runnable {
    BlockingQueue storage;
     volatile boolean canceled = false;

    public Producer(BlockingQueue storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (num < 10000 && !canceled) {
                if (num % 100 == 0) {
                    System.out.println(num + "放入仓库");
                    storage.put(num);
                }
                num++;
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer {
    BlockingQueue storage;

    public Consumer(BlockingQueue storage) {
        this.storage = storage;
    }

    public boolean needMoreNums() {
        if (Math.random() > 0.95) {
            return false;
        }

        return true;
    }
}
