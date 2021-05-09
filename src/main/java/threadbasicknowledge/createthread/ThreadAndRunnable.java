package threadbasicknowledge.createthread;

/**
 * 同时使用 Thread 和 Runnable
 * @author otfot
 * @date 2021/05/09
 */
public class ThreadAndRunnable {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Running Runnable.");
            }
        }) {

            @Override
            public void run() {
                System.out.println("Running thread.");
            }
        }.start();
    }
}
