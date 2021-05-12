package threadbasicknowledge.threadobjectclassmethod;

/**
 * 三个线程，两个阻塞，一个使用 notifyAll 唤醒
 * @author otfot
 * @date 2021/05/10
 */
public class WaitNotifyAll {
    public static final Object lock = new Object();
    static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("线程" + Thread.currentThread().getName() + " 释放了锁");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程 " + Thread.currentThread().getName() + "获取了锁");
            }
        }
    }

    static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("线程" + Thread.currentThread().getName() + " 释放了锁");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程 " + Thread.currentThread().getName() + "获取了锁");
            }
        }
    }
    static class Thread3 extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                lock.notifyAll();
                System.out.println("线程" + Thread.currentThread().getName() + "调用了 notify.");
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Thread1 t = new Thread1();
        Thread t2 = new Thread2();
        Thread3 t3 = new Thread3();

        t.start();
        t2.start();
        Thread.sleep(100);
        t3.start();
    }
}
