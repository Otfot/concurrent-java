package threadbasicknowledge.createthread;

/**
 * 通过继承 Thread 创建线程
 * @author otfot
 * @date 2021/05/09
 */
public class MyThread  extends Thread {

    @Override
    public void run() {
        System.out.println("Extends thread class.");
    }

    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();
    }
}
