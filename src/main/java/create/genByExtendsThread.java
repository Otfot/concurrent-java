package create;

public class genByExtendsThread extends Thread {

    @Override
    public void run() {
        System.out.println("Run thread by extends.");
        try {
            sleep(10000);
            System.out.println("The extends thread end.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        genByExtendsThread itbe = new genByExtendsThread();

        itbe.setDaemon(true);
        itbe.start();
        itbe.join();
    }

}
