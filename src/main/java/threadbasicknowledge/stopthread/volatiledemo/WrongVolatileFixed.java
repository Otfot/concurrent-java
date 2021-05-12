package threadbasicknowledge.stopthread.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 使用中断方法解决 volatile 的缺陷
 * @author otfot
 * @date 2021/05/09
 */
public class WrongVolatileFixed {
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
                while (num < 10000 && !Thread.currentThread().isInterrupted()) {
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


    public static void main(String[] args) throws InterruptedException {
        WrongVolatileFixed w = new WrongVolatileFixed();
        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);
        // 内部类需要先创建 外部类，再创建内部类
        Producer p = w.new Producer(storage);
        Consumer c = w.new Consumer(storage);


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
        pro.interrupt();
    }
}






