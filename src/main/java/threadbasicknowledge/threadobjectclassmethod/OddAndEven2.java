package threadbasicknowledge.threadobjectclassmethod;

/**
 * 奇偶交替打印 使用 wait notify
 *
 * @author otfot
 * @date 2021/05/10
 */
public class OddAndEven2 {

    private static final Object lock = new Object();
    private static int count = 0;

    public static void main(String[] args) {
        Runnable r = () -> {
            while (count < 100) {
                synchronized (lock) {

                    System.out.println(Thread.currentThread().getName() + " : " + count++);
                    lock.notify();
                    try {
                        // 如果任务没有完成，就让出，不加判断会导致 wait 里会有等待队列
                        if (count < 100) {
                            lock.wait();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        Runnable r2 = () -> {
            while (count < 100) {
                synchronized (lock) {

                    System.out.println(Thread.currentThread().getName() + " : " + count++);
                     lock.notify();
                    try {
                        if (count < 100) {
                            lock.wait();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };


        Thread t1 = new Thread(r, "偶数");
        Thread t2 = new Thread(r2, "奇数");
        t1.start();
        t2.start();
    }
}
