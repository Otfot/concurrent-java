package threadbasicknowledge.stopthread;

/**
 * 使用 stop 停止方法，会导致线程突然终止，没有办法完成一个基本单位的操作
 * @author otfot
 * @date 2021/05/09
 */
public class StopThread implements Runnable {


    @Override
    public void run() {
        // 模拟 5 个连队，每队 10 人，每人发放 10 颗子弹
        for (int i = 0; i < 5; i++) {

            for (int j = 0; j < 10; j++) {
                // 模拟领子弹
                try {
                    Thread.sleep(10);
                    System.out.println(j);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(i+1 +" 连队领取完成。");
        }
    }

    public static void main(String[] args) {
        Thread t = new Thread(new StopThread());
        t.start();
        // stop 方法会释放所有锁
        t.stop();

    }
}
