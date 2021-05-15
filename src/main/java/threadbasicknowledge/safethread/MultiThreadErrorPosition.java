package threadbasicknowledge.safethread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 定位并发错误出现的位置
 *
 * 考虑可见性、线程切换、线程步调是否一致，来分析并发问题的原因
 * @author otfot
 * @date 2021/05/12
 */
public class MultiThreadErrorPosition  implements  Runnable {

    int count;
    final boolean[] marked = new boolean[20001];
    private Object lock = new Object();
    private final AtomicInteger realCount = new AtomicInteger();
    private final AtomicInteger wrongCount = new AtomicInteger();
    private CyclicBarrier barrier = new CyclicBarrier(2);
    private CyclicBarrier barrier2 = new CyclicBarrier(2);


    public static void main(String[] args) throws InterruptedException {
        MultiThreadErrorPosition demo = new MultiThreadErrorPosition();
        Thread t1 = new Thread(demo);
        Thread t2 = new Thread(demo);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(demo.count);
        System.out.println(demo.realCount);
        System.out.println(demo.wrongCount);
    }

    @Override
    public void run() {
        // 是否有必要
        marked[0] = true;
        for (int i = 0; i < 10000; i++) {
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            // 2 两个线程必须先共同走到这，防止一个线程多执行一次循环
            count++;
            realCount.incrementAndGet();
            // 2 只使用 synchronzied 也会有问题, 当两个线程走到 sync 块，一个获取了锁将marked 置为了 true
            // 但它又执行了一个循环，导致 count++ ，当另一个线程获取锁时，它获取的 count 以及不是阻塞前的 count 了
            try {
                barrier2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            // 2 两个线程必须都走到这，防止修改 marked 之前，一个线程修改了 count 值
            synchronized (lock) {

                // 3 由于可见性，两个线程正常执行了 count++ 一个为 1 一个 为 2
                // 为 2 的线程进入了 synchronized 将 marked 置为 true ，释放锁后
                // 由于 释放锁先于加锁，当 2 的线程释放后， 1 的线程获取了 2 线程更新后的 count 值
                // 则进入同步块后就会导致 判断为覆盖
                if (marked[count] && marked[count-1]) {
                    wrongCount.incrementAndGet();
                    System.out.println(Thread.currentThread().getName() + "第" + i + "次执行被覆盖");
                } //1 假设两个线程都同时走到这里，就记录不了覆盖的情况, 要使用 synchronized
                else {
                    // 2 在置为 true 之前，如果进行了线程切换，另一个线程修改了 count 值，也会导致问题
                    marked[count] = true;
                }
            }

        }
    }
}
