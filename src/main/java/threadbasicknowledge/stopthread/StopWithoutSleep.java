package threadbasicknowledge.stopthread;

/**
 * run 方法没有 wait 或 sleep 方法时，停止线程
 *
 * @author otfot
 * @date 2021/05/09
 */
public class StopWithoutSleep implements Runnable {


    @Override
    public void run() {
        int num = 0;
        while (!Thread.currentThread().isInterrupted() && num < Integer.MAX_VALUE / 2) {
            if (num % 10000 == 0) {
                System.out.println(num + " is 10000 倍数");
            }
            num++;

        }

        System.out.println("Task is end.");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new StopWithoutSleep());
        t.start();

        System.out.println(Thread.currentThread().getName());
        // 主线程休眠
        Thread.sleep(2000);
        // 在主线程调用其他线程的 interrupt 方法，会中断 t 线程
        t.interrupt();

    }
}
