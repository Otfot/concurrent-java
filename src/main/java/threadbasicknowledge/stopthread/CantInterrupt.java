package threadbasicknowledge.stopthread;

/**
 * 如果 while 里面放置 try/catch 会导致中断失效
 * @author otfot
 * @date 2021/05/09
 */
public class CantInterrupt {

    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            int num = 0;

            // 由于 sleep 重置中断位，所以 Thread.currentThread().isInterrupted() 无效
            while(num <=1000 && !Thread.currentThread().isInterrupted()) {
                if (num % 10 == 0) {
                    System.out.println(num + " 是 10 的倍数");
                }
                num++;

                try {
                    // sleep 响应中断后会清除中断位
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t = new Thread(r);
        t.start();
        Thread.sleep(5000);
        t.interrupt();
    }
}
