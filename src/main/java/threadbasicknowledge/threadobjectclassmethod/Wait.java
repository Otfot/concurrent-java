package threadbasicknowledge.threadobjectclassmethod;

/**
 * 展示 wait、notify，证明 wait 释放锁
 * @author otfot
 * @date 2021/05/10
 */
public class Wait {

    public static final Object lock = new Object();

    static class Thread1 extends Thread {
        @Override
        public void run()  {
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
                lock.notify();
                System.out.println("线程" + Thread.currentThread().getName() + "调用了 notify.");
            }
        }


    }

    public static void main(String[] args) throws InterruptedException {
        Thread1 t = new Thread1();
        t.start();
        Thread2 t2 = new Thread2();
        Thread.sleep(10);
        t2.start();
    }
}
