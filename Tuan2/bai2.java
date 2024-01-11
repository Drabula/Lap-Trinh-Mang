package tuan2;

public class bai2 implements Runnable {
    public void run() {
        try {

            for (int i = 0; i < 5; i++) {
                System.out.println("Luong dang chay: " + Thread.currentThread().getId() + ", Buoc: = " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int numThreads = 3;
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(new bai2());
            threads[i].start();
        }
    }
}