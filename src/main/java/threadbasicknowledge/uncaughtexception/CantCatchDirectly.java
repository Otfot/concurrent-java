package threadbasicknowledge.uncaughtexception;

/**
 * 不加 try catch 抛出 4 个异常
 * 加了 try catch 抛出 1 个 ，其他线程停止运行
 * @author otfot
 * @date 2021/05/12
 */
public class CantCatchDirectly {

    public static void main(String[] args) {
        Runnable r = () -> {
            throw new RuntimeException();
        };

        // try catch 只能捕获当前线程中发生的异常，如果 线程创建或启动出现异常，可以捕获，
        // 但线程运行后就无法捕获其他线程抛出的异常
        try {
            new Thread(r).start();
            new Thread(r).start();
            new Thread(r).start();
            new Thread(r).start();
        } catch (RuntimeException e) {
            System.out.println("Catch exception!");
        }

    }
}
