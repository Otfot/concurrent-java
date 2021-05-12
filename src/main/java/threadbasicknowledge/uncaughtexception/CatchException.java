package threadbasicknowledge.uncaughtexception;

/**
 * 演示使用 handler
 * @author otfot
 * @date 2021/05/12
 */
public class CatchException {

    public static void main(String[] args) {
        Runnable r = () -> {
            throw new RuntimeException();
        };

        Thread.setDefaultUncaughtExceptionHandler(new GlobalUncaughtExceptionHandler());


        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();
    }
}
