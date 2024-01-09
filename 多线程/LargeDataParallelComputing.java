import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class LargeDataParallelComputing {

    public static void main(String[] args) {
        int dataSize = 10_000_000; // 数据规模
        int numThreads = 4; // 线程池大小

        // 创建 ThreadPoolExecutor
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numThreads);

        List<SumCalculator> tasks = new ArrayList<>();

        // 生成数据集
        for (int i = 1; i <= dataSize; i++) {
            tasks.add(new SumCalculator(i));
        }

        try {
            // 提交任务给线程池执行
            List<Future<Integer>> futures = executor.invokeAll(tasks);

            // 关闭线程池
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);

            // 计算所有任务的结果总和
            long totalSum = 0;
            for (Future<Integer> future : futures) {
                totalSum += future.get();
            }

            System.out.println("Total sum of cubes: " + totalSum);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

// 计算数字立方的任务
class SumCalculator implements Callable<Integer> {
    private final int number;

    public SumCalculator(int number) {
        this.number = number;
    }

    @Override
    public Integer call() {
        // 执行计算任务，这里是计算数字的立方
        return number * number * number;
    }
}
