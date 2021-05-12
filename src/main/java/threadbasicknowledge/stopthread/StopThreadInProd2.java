package threadbasicknowledge.stopthread;

/**
 * 线程中断最佳实践2，恢复设置中断 使上层可以检测到中断
 * @author otfot
 * @date 2021/05/09
 */
public class StopThreadInProd2 implements Runnable {

    @Override
    public void run() {
        while(true) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupted");
            }
            reInterrupt();
        }
    }

    private void reInterrupt() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            //捕获 然后重新恢复中断
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new StopThreadInProd2());
        t.start();
        Thread.sleep(2000);
        t.interrupt();
    }
}
