package threadbasicknowledge.threadobjectclassmethod;

/**
 * 主线程 join 等待多个线程
 * @author otfot
 * @date 2021/05/11
 */
public class JoinMultiThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t4 = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t1.join();
        System.out.println("1" + t1.getState());
        System.out.println("2" + t2.getState());
        System.out.println("3" + t3.getState());
        System.out.println("4" + t4.getState());
        t2.join();
        System.out.println("1" + t1.getState());
        System.out.println("2" + t2.getState());
        System.out.println("3" + t3.getState());
        System.out.println("4" + t4.getState());
        t3.join();
        System.out.println("1" + t1.getState());
        System.out.println("2" + t2.getState());
        System.out.println("3" + t3.getState());
        System.out.println("4" + t4.getState());
        t4.join();
        System.out.println("1" + t1.getState());
        System.out.println("2" + t2.getState());
        System.out.println("3" + t3.getState());
        System.out.println("4" + t4.getState());
        System.out.println("runn");
    }
}
