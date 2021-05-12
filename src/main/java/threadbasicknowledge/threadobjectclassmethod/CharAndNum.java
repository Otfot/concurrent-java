package threadbasicknowledge.threadobjectclassmethod;

import java.util.ArrayList;

/**
 * 字符与数字，交替打印
 * @author otfot
 * @date 2021/05/10
 */
public class CharAndNum {

    private static final Object lock = new Object();
    private static int count = 1;
    private static char c = 'A';


    public static void main(String[] args) {
        Runnable r = () -> {
            while(c <= 'Z') {
                synchronized (lock) {
                    System.out.print(c++);
                    lock.notify();
                    if (c <= 'Z') {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        };

        Runnable r2 = () -> {
            while(count <= 26) {
                synchronized (lock) {
                    System.out.print(count++);
                    lock.notify();
                    if (count <= 26) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();


    }
}
