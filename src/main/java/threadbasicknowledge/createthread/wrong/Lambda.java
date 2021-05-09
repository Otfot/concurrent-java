package threadbasicknowledge.createthread.wrong;

/**
 * 使用 lambda 创建线程
 * @author otfot
 * @date 2021/05/09
 */
public class Lambda {
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        }).start();
    }
}
