package threadbasicknowledge.safethread;

/**
 * 通过观察者模式，展示线程安全问题，可能导致 构造函数还没有初始化完成，其变量就被其他线程使用
 * @author otfot
 * @date 2021/05/12
 */
public class ObserverMode {

    // 在构造函数中进行初始化
    int count;
    public ObserverMode(Source source) {
        source.registerListener(new EventListener() {
            @Override
            public void onEvent(Event e ) {
                // 匿名内部类拿到外部 count ，此时 count 还没有初始化

                System.out.println("监听者收到了 event" + count);
            }
        });

        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }
        System.out.println();
        count = 100;
    }

    static class Source {
        private EventListener listener;

        void registerListener(EventListener listener) {
            this.listener = listener;
        }

        void eventCome(Event e) {
            if (listener != null) {
                listener.onEvent(e);
            } else {
                System.out.println("未初始化完成");
            }
        }
    }

    interface EventListener {
        void onEvent(Event e);
    }

    interface Event {}

    public static void main(String[] args) {
        Source s = new Source();


        new Thread(() -> {
            try {
                Thread.sleep(68);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            s.eventCome(new Event() {
            });
        }).start();

        ObserverMode o = new ObserverMode(s);
    }
}
