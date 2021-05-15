package threadbasicknowledge.safethread;

import java.util.LinkedList;

/**
 * 通过工厂模式修复
 *
 * @author otfot
 * @date 2021/05/13
 */
public class ObserverModeFixed {

    // 在构造函数中进行初始化
    int count;
    EventListener listener;

    public ObserverModeFixed() {
        listener = new EventListener() {
            @Override
            public void onEvent(Event e) {
                // 匿名内部类拿到外部 count ，此时 count 还没有初始化

                System.out.println("监听者收到了 event" + count);
            }
        };

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



    public static ObserverModeFixed getInstance(Source source) {
        ObserverModeFixed o = new ObserverModeFixed();
        source.registerListener(o.listener);

        return o;
    }

    interface Event {}

    public static void main(String[] args) {

        Source source = new Source();
        ObserverModeFixed omf =  ObserverModeFixed.getInstance(source);

        new Thread(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            source.eventCome(new Event(){});
        }).start();


    }


}
