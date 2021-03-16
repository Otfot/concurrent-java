package create;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class genByThreadPoolExecute {

    public static void main(String[] args) {
        ExecutorService pool2 = Executors.newFixedThreadPool(10);

        for(int i = 0; i < 10; i++) {
            pool2.execute(
                () -> System.out.println(Thread.currentThread().getName() + "is running.")
            );
        }

    }
}
