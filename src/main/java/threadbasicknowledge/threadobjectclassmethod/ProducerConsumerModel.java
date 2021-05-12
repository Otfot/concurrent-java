package threadbasicknowledge.threadobjectclassmethod;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 生产者消费者模型
 * @author otfot
 * @date 2021/05/10
 */
public class ProducerConsumerModel {

    class Producer implements Runnable {
        EventStorage storage;

        Producer(EventStorage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            while(true) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                storage.put(new Date());
            }
        }
    }

    class Consumer implements Runnable{
        EventStorage e;

        Consumer(EventStorage e) {
            this.e = e;
        }

        @Override
        public void run() {
            while(true) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException interruptedException) {
//                    interruptedException.printStackTrace();
//                }
                e.take();
            }

        }
    }


    class EventStorage {
        private final int MAX_SIZE = 10;
        private LinkedList<Date> storage;

        EventStorage() {
            storage = new LinkedList<>();
        }

        public synchronized void put(Date date) {
            while(storage.size() == MAX_SIZE) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            storage.add(date);
            System.out.println(Thread.currentThread().getName() + "生产了 1 个产品");
            notify();
        }

        public synchronized void take() {
            while(storage.size() == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("拿到了" + storage.poll() + ", 现在剩" + storage.size());
            notify();
        }
    }

    public static void main(String[] args) {
        ProducerConsumerModel pcm = new ProducerConsumerModel();
        EventStorage e = pcm.new EventStorage();
        Thread p = new Thread(pcm.new Producer(e));
        Thread c = new Thread(pcm.new Consumer(e));

        Thread p2 = new Thread(pcm.new Producer(e));


        p.start();
        c.start();
//        p2.start();

    }
}
