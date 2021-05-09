package threadbasicknowledge.createthread.wrong;

/**
 * 使用匿名内部类创建线程
 * @author otfot
 * @date 2021/05/09
 */
public class AnonymousInnerClass {

    public static void main(String[] args) {
        new Thread () {
            @Override
            public void run() {
                System.out.println("Anonymous 1");
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous 2");
            }
        }).start();
    }

}
