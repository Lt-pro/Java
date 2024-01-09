import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelComputingExample {

    public static void main(String[] args) {
        // 创建一个固定大小的线程池
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // 模拟一组任务，这里计算每个数字的立方和
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<SumCalculator> tasks = new ArrayList<>();
		
		// 竞争会降低多核处理的的效率
        for (Integer number : numbers) {
            tasks.add(new SumCalculator(number));
        }

        try {
            // 提交任务给线程池执行
            for (var task : tasks) {
				executor.execute(task);
			}

            // 关闭线程池
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 计算所有任务的结果总和
        int totalSum = 0;
        for (SumCalculator task : tasks) {
            totalSum += task.getResult();
        }

        System.out.println("Total sum of cubes: " + totalSum);
    }
}

// 计算数字立方的任务
class SumCalculator implements Runnable {
    private final int number;
    private int result;

    public SumCalculator(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        // 执行计算任务，这里是计算数字的立方
        result = number * number * number;
        System.out.println("Calculated cube of " + number);
    }

    public int getResult() {
        return result;
    }
}
