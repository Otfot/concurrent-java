package threadbasicknowledge.safethread;

import java.util.HashMap;

/**
 * 意外返回私有对象，导致对象可能被修改
 * @author otfot
 * @date 2021/05/13
 */
public class PrivateState {

    private HashMap<String, String> state;

    PrivateState() {
        state = new HashMap<>();
        state.put("1", "zhou");
        state.put("2", "zhou");
        state.put("3", "zhou");
        state.put("4", "zhou");
    }

    public HashMap<String, String> getState() {
        return state;
    }

    public HashMap<String, String> getStateImproved() {
        return new HashMap<>(state);
    }
}
