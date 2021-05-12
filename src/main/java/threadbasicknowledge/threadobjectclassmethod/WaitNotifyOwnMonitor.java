package threadbasicknowledge.threadobjectclassmethod;

/**
 * wait 只释放当前的锁
 * @author otfot
 * @date 2021/05/10
 */
public class WaitNotifyOwnMonitor {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        Runnable r = () -> {
            synchronized (lock1) {
                try {
                    lock2.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();
    }
}
