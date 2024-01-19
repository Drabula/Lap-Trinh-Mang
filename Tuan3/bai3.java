public class bai3 {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    // Công việc của tiến trình treo 1
                    System.out.println("Tien trinh treo 1 dang chay");
                    
                }
            }
        });
        
        // Khởi chạy tiến trình treo 1
        thread1.start();
    }
    
}