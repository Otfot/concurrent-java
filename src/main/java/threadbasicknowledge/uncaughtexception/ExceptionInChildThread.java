package threadbasicknowledge.uncaughtexception;

/**
 * 单线程 抛出异常，有异常堆栈
 * 多线程，子线程发生异常，有哪些不同
 * @author otfot
 * @date 2021/05/12
 */
public class ExceptionInChildThread {


    public static void main(String[] args) {
        Runnable r = () -> {
            throw new RuntimeException();
        };

        // 子线程有异常，主线程不会被中断，子线程异常信息会被大量日志掩盖，无法察觉
        new Thread(r).start();

        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }
}
