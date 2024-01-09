import java.util.concurrent.*;

public class FibonacciThreadPoolExecutor {

    public static void main(String[] args) {
        final int n = 20; // 计算斐波那契数列的项数

        // 创建单线程的 ThreadPoolExecutor
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        long startTimeSingle = System.currentTimeMillis();
        Future<Long> resultSingle = singleThreadExecutor.submit(() -> calculateFibonacciSingleThread(n));
        long endTimeSingle = System.currentTimeMillis();
        long singleThreadTime = endTimeSingle - startTimeSingle;

        // 创建多线程的 ThreadPoolExecutor
        int numThreads = Runtime.getRuntime().availableProcessors(); // 获取处理器核心数
        ThreadPoolExecutor multiThreadExecutor = new ThreadPoolExecutor(
                numThreads, numThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>()
        );
        long startTimeMulti = System.currentTimeMillis();
        Future<Long> resultMulti = multiThreadExecutor.submit(() -> calculateFibonacciMultiThread(n));
        long endTimeMulti = System.currentTimeMillis();
        long multiThreadTime = endTimeMulti - startTimeMulti;

        try {
            // 获取计算结果和耗时
            System.out.println("Single Thread Result: " + resultSingle.get());
            System.out.println("Single Thread Time: " + singleThreadTime + "ms");

            System.out.println("\nMulti Thread Result: " + resultMulti.get());
            System.out.println("Multi Thread Time: " + multiThreadTime + "ms");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // 关闭线程池
            singleThreadExecutor.shutdown();
            multiThreadExecutor.shutdown();
        }
    }

    // 单线程计算斐波那契数列
    public static long calculateFibonacciSingleThread(int n) {
        if (n <= 1) {
            return n;
        } else {
            return calculateFibonacciSingleThread(n - 1) + calculateFibonacciSingleThread(n - 2);
        }
    }

    // 多线程计算斐波那契数列
    public static long calculateFibonacciMultiThread(int n) {
        if (n <= 1) {
            return n;
        } else {
            ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
            Future<Long> fib1 = pool.submit(() -> calculateFibonacciMultiThread(n - 1));
            Future<Long> fib2 = pool.submit(() -> calculateFibonacciMultiThread(n - 2));
            try {
                long result = fib2.get() + fib1.get();
                pool.shutdown();
                return result;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                pool.shutdown();
                return -1;
            }
        }
    }
}
