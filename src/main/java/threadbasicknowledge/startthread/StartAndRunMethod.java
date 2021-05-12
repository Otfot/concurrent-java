package threadbasicknowledge.startthread;

/**
 * 使用 start 和 run 方法启动
 * @author otfot
 * @date 2021/05/09
 */
public class StartAndRunMethod {
    public static void main(String[] args) {
        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName());
        };
        r.run();

        new Thread(r).start();
    }
}
