package threadbasicknowledge.threadobjectclassmethod;

/**
 * Join 原理
 * @author otfot
 * @date 2021/05/11
 */
public class JoinPrinciple {


    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            try {
                System.out.println("子线程运行");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t.start();
        // 子线程执行完自动唤醒主线程
        synchronized (t) {
            // 阻塞的是当前运行的线程
            t.wait();
        }

        System.out.println(t.getState());
    }
}
