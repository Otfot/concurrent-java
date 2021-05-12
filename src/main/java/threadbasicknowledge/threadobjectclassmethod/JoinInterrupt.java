package threadbasicknowledge.threadobjectclassmethod;

/**
 * 展示 join 被中断
 * @author otfot
 * @date 2021/05/11
 */
public class JoinInterrupt {

    public static void main(String[] args) throws InterruptedException {
        Thread main = Thread.currentThread();

        Thread t1 = new Thread(() -> {
            try {
//                main.interrupt();
                Thread.sleep(1000);

                System.out.println("main :" + main.getState());
//                Thread.sleep(5000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子线程" + Thread.currentThread().getState());
            System.out.println();

        });


        t1.start();

        // 主线程等待 t1 执行，所以中断表示的是 主线程
        try {
            t1.join();
        } catch (InterruptedException e) {
            // 主线程中断，应该通知等待的子线程也中断
//            System.out.println("main: " + Thread.currentThread().getState());

//            t1.interrupt();
            e.printStackTrace();
        }
        Thread.sleep(10);
        System.out.println("子线程" + t1.getState());

    }
}
