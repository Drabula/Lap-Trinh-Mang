package tuan2;

public class bai1 extends Thread {
    @Override
    public void run() {

        System.out.println("Luong dang chay");
    }

    public static void main(String[] args) {
        bai1 thread = new bai1();
        thread.start();
    }
}