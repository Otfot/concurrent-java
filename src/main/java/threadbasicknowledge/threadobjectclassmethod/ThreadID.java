package threadbasicknowledge.threadobjectclassmethod;

/**
 * 验证 main 线程 id 为 0， 且自己创建的线程 id 不是 1
 * @author otfot
 * @date 2021/05/12
 */
public class ThreadID {

    public static void main(String[] args) {
        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName() + " : " +Thread.currentThread().getId());
        };

        System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getId());
        for (int i = 0; i < 5; i++) {
            new Thread(r).start();
        }
    }
}
