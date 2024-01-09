import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SingleThreadComputing {

    public static void main(String[] args) {
        int dataSize = 10_000_000; // 数据规模

        // 创建单线程 ExecutorService
        ExecutorService executor = Executors.newSingleThreadExecutor();

        List<SumCalculator> tasks = new ArrayList<>();

        // 生成数据集
        for (int i = 1; i <= dataSize; i++) {
            tasks.add(new SumCalculator(i));
        }

        try {
            // 提交任务给单线程执行
            List<Future<Integer>> futures = new ArrayList<>();
            for (SumCalculator task : tasks) {
                futures.add(executor.submit(task));
            }

            // 关闭单线程 ExecutorService
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
