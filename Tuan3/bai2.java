public class bai2 {
    private static Object lock = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // Công việc của luồng 1
                System.out.println("Luong 1 dang chay");
                // Hoàn thành công việc của luồng 1
                synchronized (lock) {
                    lock.notify(); // Thông báo cho luồng 2 biết rằng luồng 1 đã hoàn thành công việc
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait(); // Chờ nhận thông báo từ luồng 1
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // Công việc của luồng 2
                    System.out.println("Luong 2 dang chay");
                }
            }
        });

        // Khởi chạy luồng 1 và luồng 2
        thread1.start();
        thread2.start();
    }
}