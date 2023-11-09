import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.EOFException;


public class FTest {
	public static void main(String[] arg) throws IOException{
		File f = new File("./file.txt");
		if (f.exists()) {
			f.createNewFile();
			try (FileWriter fw = new FileWriter(f)) {
				fw.write("This is a message to throw IOException.");
			}
		}
		
		int c;
		try (FileReader fr = new FileReader(f)) {
			while ((c=fr.read()) != -1) 
				System.out.println((char)c);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		f.delete();
	}
}