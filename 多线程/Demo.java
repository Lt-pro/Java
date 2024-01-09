import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;

public class Demo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 初始化一个巨大数组，可以想象为我们需要处理的业务数据，上万甚至上亿条记录
        int[] a = new int[360000];
        // 随机放入100内随机数
        rand(a);
        long beginTime = System.currentTimeMillis();
        long b = 0L;
        // 传统单线程计算，10000*360000次运算，模拟对业务数据的处理过程
        for (int k = 0; k < 10000; k++) {
            for (int i : a) {
                b += i;
            }
        }
        System.out.println("总和：" + b);
        System.out.println("单独计算耗时：" + (System.currentTimeMillis() - beginTime) + "毫秒");
        System.out.println("_______________________________________________________");

        // 使用线程池计算
        beginTime = System.currentTimeMillis();
        // 定义线程池，各参数含义可以看一下官方文档说明
        ExecutorService pool = new ThreadPoolExecutor(4, 8, 6,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(5), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        // 将业务数据切割为4份，分为4个线程处理，实际使用中可能不清楚数据量，用for循环就好
        // 此处子线程可用runnable或callable，如果需要回调信息，则必须使用callable，返回值在自定义的线程类中编写
        Callable<Long> runnable = new SumThread(a, 0, 90000);
        Callable<Long> runnable1 = new SumThread(a, 90000, 180000);
        Callable<Long> runnable2 = new SumThread(a, 180000, 270000);
        Callable<Long> runnable3 = new SumThread(a, 270000, 360000);
        System.out.println("准备启动");
        // 四个线程初始化完成，提交给线程池
        Future<Long> num = pool.submit(runnable);
        Future<Long> num1 = pool.submit(runnable1);
        Future<Long> num2 = pool.submit(runnable2);
        Future<Long> num3 = pool.submit(runnable3);
        System.out.println("启动完成");
        // 调用callable的get()方法，从各子线程获取返回值，由于callable.get()方法特性，子线程运行完毕后才会调用这一行
        System.out.println("总和：" + (num.get() + num1.get() + num2.get() + num3.get()));
        long endTime = System.currentTimeMillis();
        System.out.println("线程池总耗时：" + (endTime - beginTime) + "毫秒");

        try {
            // 关闭线程池，已提交的任务继续执行，不再接收新的任务
            pool.shutdown();
            // 等待所有的任务都结束（实时判断是否全完成），若所有任务都已完成，则返回true，若超时未完成，则返回false
            if (!pool.awaitTermination(6, TimeUnit.SECONDS)) {
                // 超时的时候向线程池中所有的线程发出中断(interrupted)。
                pool.shutdownNow();
            }
        } finally {
            // 最终确保线程池被关闭
            if (!pool.isTerminated()) {
                pool.shutdownNow();
            }
        }
    }

    // 向数组中填充100内随机数
    private static void rand(int[] a) {
        Random random = new Random();
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt(100);
        }
    }
}

// 编写自定义线程类，可以实现runnable或callable，如需调用返回值，则必须实现callable
class SumThread implements Callable<Long> {
    // 存放的业务数据
    private final int[] a;
    // 定义一个返回值，可按需求修改
    private long result;

    // 初始化线程实例时，从业务数据中根据下标取出 一小段数据
    public SumThread(int[] b, int start, int end) {
        a = Arrays.copyOfRange(b, start, end);
        System.out.println("初始化一个线程实例");
    }

    // 执行方法，模拟对业务数据的处理过程，运行完成后 return 指定的返回值
    @Override
    public Long call() throws Exception {
        System.out.println("启动一个线程");
        // a = rand1(a);
        long beginTime = System.currentTimeMillis();
        long b = 0L;
        for (int k = 0; k < 10000; k++) {
            for (int j = 0; j < a.length; j++) {
                b += a[j];
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("线程耗时：" + (endTime - beginTime) + "毫秒");
        System.out.println("线程计算结果：" + b);
        System.out.println("结束一个线程");
        result = b;
        return result;
    }
}
