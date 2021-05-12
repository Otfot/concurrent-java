package threadbasicknowledge.threadobjectclassmethod;

/**
 * 奇偶交替打印 只使用 synchronized
 * @author otfot
 * @date 2021/05/10
 */
public class OddAndEven {
    private static final Object lock = new Object();
    private static int count = 0;

    public static void main(String[] args) {
        Runnable r = () -> {
            while(count < 100) {
                synchronized (lock) {
                    if ((count & 1) == 0) {
                        System.out.println(Thread.currentThread().getName() + " : " + count++);
                    }

                }
            }
        };

        Runnable r2 = () -> {
            while(count < 100) {
                synchronized (lock) {
                    if ((count & 1) != 0) {
                        System.out.println(Thread.currentThread().getName() + " : " + count++);
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
