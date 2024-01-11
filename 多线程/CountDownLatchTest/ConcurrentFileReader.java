import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentFileReader {

    public static void main(String[] args) {
        // 指定文件路径
        String filePath = "./file.txt";

        // 定义线程数量
        int threadCount = 3;

        // 创建 CountDownLatch，设置计数器为线程数量
        CountDownLatch latch = new CountDownLatch(threadCount);

        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < threadCount; i++) {
            // 提交任务给线程池
            executorService.submit(new FileReaderTask(filePath, latch));
        }

        try {
            // 等待所有线程完成
            latch.await();

            // 所有线程完成后执行合并结果的操作
            System.out.println("All threads have completed reading the file. Perform further processing here.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭线程池
            executorService.shutdown();
        }
    }

    static class FileReaderTask implements Runnable {

        private final String filePath;
        private final CountDownLatch latch;

        public FileReaderTask(String filePath, CountDownLatch latch) {
            this.filePath = filePath;
            this.latch = latch;
        }

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // 模拟处理每一行数据的操作
                    processLine(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 当线程完成任务后，减少 CountDownLatch 的计数器
                latch.countDown();
            }
        }

        private void processLine(String line) {
            // 模拟处理每一行数据的具体操作
            System.out.println(Thread.currentThread().getName() + " processed line: " + line);
        }
    }
}
