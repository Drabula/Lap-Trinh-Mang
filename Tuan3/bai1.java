public class bai1 {
    private static Object lock = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    // Công việc của luồng 1
                    System.out.println("Luong 1 dang chay");
                    // Hoàn thành công việc của luồng 1
                }
            }
        });

        // Khởi chạy luồng 1
        thread1.start();
    }
}