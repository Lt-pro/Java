import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {

    // 定义一个屏障点，当达到该点时，所有线程可以继续执行
    private static final int BARRIER_POINT = 3;

    public static void main(String[] args) {
        // 创建一个CyclicBarrier，传入参与线程的数量和屏障点
        CyclicBarrier cyclicBarrier = new CyclicBarrier(BARRIER_POINT, () -> {
            // 在所有线程达到屏障点时执行的任务
            System.out.println("All threads have reached the barrier. Let's continue!");
        });

        // 创建并启动多个线程
        for (int i = 0; i < BARRIER_POINT; i++) {
            Thread thread = new Thread(new Worker(i, cyclicBarrier));
            thread.start();
        }
    }

    static class Worker implements Runnable {

        private final int id;
        private final CyclicBarrier cyclicBarrier;

        public Worker(int id, CyclicBarrier cyclicBarrier) {
            this.id = id;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                System.out.println("Thread " + id + " is doing some work.");
                // 模拟线程执行一些工作
                Thread.sleep(1000 * id);

                System.out.println("Thread " + id + " has reached the barrier.");

                // 等待其他线程到达屏障点
                cyclicBarrier.await();

                // 所有线程都到达屏障点后执行的任务
                System.out.println("Thread " + id + " continues its work after the barrier.");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
