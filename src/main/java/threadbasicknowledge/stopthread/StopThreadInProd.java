package threadbasicknowledge.stopthread;

/**
 * 停止线程的最佳实践 1 在低层抛出中断，在高层才处理中断
 * @author otfot
 * @date 2021/05/09
 */
public class StopThreadInProd implements Runnable {


    @Override
    public void run() {

        try {
            // 有可能主任务是循环，如果 在方法 throwInMethod 直接捕获异常程序不会停止导致无法查看到异常
            while(true) {
                throwInMethod();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void throwInMethod() throws InterruptedException {
        Thread.sleep(2000);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new StopThreadInProd());
        t.start();
        Thread.sleep(1000);
        t.interrupt();

    }
}
