package create;

public class genByImplRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Run thread by Runnable.");
    }


    public static void main(String[] args) {
        genByImplRunnable itbi = new genByImplRunnable();

        Thread t = new Thread(itbi);
        t.start();
    }
}
