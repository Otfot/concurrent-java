package threadbasicknowledge.createthread;

/**
 * 实现 Runnable 接口实现线程
 * @author otfot
 * @date 2021/05/09
 */
public class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Implements Runnable interface.");
    }

    public static void main(String[] args) {
        Thread t = new Thread(new MyRunnable());
        t.start();
    }
}
