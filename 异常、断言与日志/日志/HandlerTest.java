import java.io.*;
import java.util.logging.*;


public class HandlerTest {
	private static final Logger consoleLogger = Logger.getLogger("consoleLogger");
	private static final Logger fileLogger = Logger.getLogger("fileLogger");
	
	public static void main(String[] args) throws IOException{
		// 自定义记录器、处理器级别
		// 记录器会将记录发给父处理器、本地处理器，可打印的日志级别不低于记录器和处理器本身级别
		
		// 定义FINE级别的记录器，控制台处理器
		consoleLogger.setLevel(Level.FINE);
		consoleLogger.setUseParentHandlers(false);
		var handler = new ConsoleHandler();
		handler.setLevel(Level.FINE);
		consoleLogger.addHandler(handler);
		
		// 打印10条FINE级别消息
		for (int i=0; i<10; i++) {
			consoleLogger.fine("This is "+(i+1)+"-th a record with level fine");
		}
		
		// 定义FINE级别的记录器，文件处理器
		fileLogger.setLevel(Level.FINE);
		fileLogger.setUseParentHandlers(false);
		var fileHandler = new FileHandler("./java%u.log");
		fileHandler.setLevel(Level.FINE);
		fileHandler.setFormatter(new SimpleFormatter());
		fileLogger.addHandler(fileHandler);
		
		// 打印100条FINE级别消息
		for (int i=0; i<100; i++) {
			fileLogger.fine("This is "+(i+1)+"-th a record with level fine");
		}
	}
	
}