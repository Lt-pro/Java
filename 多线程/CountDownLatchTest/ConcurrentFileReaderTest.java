import java.io.FileWriter;
import java.io.IOException;

public class ConcurrentFileReaderTest {

    public static void main(String[] args) {
        // 生成测试文件
        generateTestFile("./testfile.txt");

        // 运行并发读取文件的程序
        ConcurrentFileReader.main(args);
    }

    private static void generateTestFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // 添加一些测试数据到文件中
            writer.write("Line 1\n");
            writer.write("Line 2\n");
            writer.write("Line 3\n");
            writer.write("Line 4\n");
            writer.write("Line 5\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
