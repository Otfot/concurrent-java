package threadbasicknowledge.sixstates;

/**
 *
 * 展示线程的 三种状态 NEW、RUNNABLE、TERMINATEd
 * @author otfot
 * @date 2021/05/10
 */
public class NewRunnableTerminated implements Runnable{

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new NewRunnableTerminated());

        System.out.println(t.getState());
        t.start();
        System.out.println(t.getState());
        Thread.sleep(4);
        System.out.println(t.getState());
        t.join();
        System.out.println(t.getState());

    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }
}
