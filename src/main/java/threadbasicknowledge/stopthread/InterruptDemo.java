package threadbasicknowledge.stopthread;

/**
 * 测试 interrupt 相关方法
 * @author otfot
 * @date 2021/05/09
 */
public class InterruptDemo {
    public static void main(String[] args) {
        Runnable r = () -> {
            while(true) {
                System.out.println("hello");
            }

        };


        Thread t = new Thread(r);
        t.start();
        System.out.println(t.isInterrupted());
        System.out.println();
        System.out.println(Thread.interrupted());
        System.out.println(Thread.currentThread().isInterrupted());
    }
}
