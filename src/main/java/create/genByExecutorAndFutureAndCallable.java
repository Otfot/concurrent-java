package create;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class genByExecutorAndFutureAndCallable implements Callable<String> {

    private String name;

    public genByExecutorAndFutureAndCallable(String name) {
        this.name = name;
    }


    @Override
    public String call() {
        return name;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        List<Future> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Callable<String> c = new genByExecutorAndFutureAndCallable(i + " ");
            // 提交到线程池中
            Future future = pool.submit(c);
            // 并将获取的 future 对象 保存 以便结束后拿取结果
            list.add(future);

        }
        // 关闭线程池，等待线程结束
        pool.shutdown();

        for (Future future : list) {
            // get 方法抛出 ExecutorException 异常
            System.out.println("get the result from thread:" + future.get().toString());
        }
    }

}
