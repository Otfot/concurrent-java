package threadbasicknowledge.safethread;

import java.util.HashMap;

/**
 * 在构造函数中新建线程，很危险
 * @author otfot
 * @date 2021/05/12
 */
public class ThreadInConstructor {

    private HashMap<String, String> state;

    public ThreadInConstructor() {
        // 构造函数中启动线程就表示构造函数执行完成，不会等待线程执行完
        new Thread(() ->{

            state = new HashMap<>();
            state.put("1", "zhou");
            state.put("2", "zhou");
            state.put("3", "zhou");
            state.put("4", "zhou");

        }).start();
    }

    public static void main(String[] args) {
        ThreadInConstructor t = new ThreadInConstructor();
        System.out.println(t.state.get("1"));
    }

}
