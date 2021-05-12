package threadbasicknowledge.threadobjectclassmethod;

/**
 * 注意语句输出顺序
 * @author otfot
 * @date 2021/05/11
 */
public class Join {

    public static void main(String[] args) throws InterruptedException {
            Thread t = new Thread(() ->{
                System.out.println(Thread.currentThread().getName() + "执行");
            });

        Thread t2 = new Thread(() ->{
            System.out.println(Thread.currentThread().getName() + "执行");
        });

        t.start();
        t2.start();
        t.join();
        t2.join();
        System.out.println(Thread.currentThread().getName() + "执行");
    }
}
