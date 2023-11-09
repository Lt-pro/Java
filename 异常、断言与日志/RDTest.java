// 读取非空文件字符并打印
import java.io.File;
import java.io.IOException;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;

public class RDTest {
	public static void main(String[] args) throws IOException{
		File f = new File("./message.txt");
		if (!f.exists()) f.createNewFile();
		FileWriter fw = new FileWriter(f);
		fw.write("This is a message to throw IOException.");
		fw.flush();
		fw.close();
		
		readData(f);
	}

	// 输入一个合法文本文件对象
	public static void readData(File file) throws EOFException, FileNotFoundException, IOException {
		FileReader fr = new FileReader(file);
		int c;
		while (true) {
			c = fr.read();
			if (c!=-1) 
				System.out.println((char)c);
			else
				throw new EOFException();
		}
	}
}