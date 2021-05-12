package threadbasicknowledge.uncaughtexception;

/**
 * 全局线程异常处理器
 * @author otfot
 * @date 2021/05/12
 */
public class GlobalUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(t.getName() + " 出现了异常 ：");
        System.out.println(e);
    }
}
