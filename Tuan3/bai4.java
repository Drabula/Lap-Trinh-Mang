public class bai4 {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // Công việc của tiến trình treo 1
                System.out.println("Tien trinh treo 1 dang chay");
                // Khi công việc hoàn thành, tiến trình sẽ treo
                try {
                    Thread.sleep(Long.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    // Công việc của tiến trình treo 2
                    System.out.println("Tien trinh treo 2 dang chay");
                }
            }
        });

        // Khởi chạy tiến trình treo 1 và tiến trình treo 2
        thread1.start();
        thread2.start();
    }
}