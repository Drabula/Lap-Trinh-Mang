package tuan2;

public class bai3 {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("Luong moi duoc tao");
                Thread.sleep(1000);
                System.out.println("Trang thai hien tai: " + Thread.currentThread().getState());

                System.out.println("Bat dau chay luong");
                Thread.sleep(3000);
                System.out.println("Trang thai hien tai: " + Thread.currentThread().getState());

                System.out.println("wait 2 sec");
                Thread.sleep(2000);
                System.out.println("Trang thai hien tai: " + Thread.currentThread().getState());

                System.out.println("Ket thuc luong");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Trang thai ban dau: " + thread.getState());

        thread.start();
        System.out.println("Trang thai sau khu goi start: " + thread.getState());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Trang thai sau khi cho 0,5 sec: " + thread.getState());

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Trang thai sau khi ket thuc: " + thread.getState());
    }
}
