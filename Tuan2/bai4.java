package tuan2;


    public class bai4 extends Thread {
        private int[] arr;
        private int startIndex;
        private int endIndex;
        private int sum;
    
        public bai4(int[] arr, int startIndex, int endIndex) {
            this.arr = arr;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }
    
        public int getSum() {
            return sum;
        }
    
        public void run() {
            for (int i = startIndex; i <= endIndex; i++) {
                sum += arr[i];
            }
        }
    
        public static void main(String[] args) {
            int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            int numThreads = 4;
            int chunkSize = arr.length / numThreads;
    
            bai4[] threads = new bai4[numThreads];
            for (int i = 0; i < numThreads; i++) {
                int startIndex = i * chunkSize;
                int endIndex = (i == numThreads - 1) ? arr.length - 1 : (i + 1) * chunkSize - 1;
                threads[i] = new bai4(arr, startIndex, endIndex);
                threads[i].start();
            }
    
            int totalSum = 0;
            for (int i = 0; i < numThreads; i++) {
                try {
                    threads[i].join();
                    totalSum += threads[i].getSum();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    
            System.out.println("Tong cua mang la: " + totalSum);
        }
    }


