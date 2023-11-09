import java.io.*;
import java.util.logging.*;


public class LoggingTest {
	private static final Logger localLogger = Logger.getLogger("loggingtest");
	
	
	public static void main(String[] args) {
		// 配置文件使用本地文件
		// java -Djava.util.logging.config.file=loggingtest.properties LoggingTest.java
		
		// 全局日志记录器
		Logger.getGlobal().info("Global: File->Open menu item selected");
		
		// 禁用全局日志记录器
		Logger.getGlobal().setLevel(Level.OFF);
		
		// 本地日志记录器记录INFO及以上级别消息
		localLogger.log(Level.FINE, "log record with level FINE");
		localLogger.log(Level.CONFIG, "log record with level CONFIG");
		localLogger.log(Level.INFO, "log record with level INFO");
		localLogger.log(Level.WARNING, "log record with level WARNING");
		localLogger.log(Level.SEVERE, "log record with level SEVER");
		
		
		// 本地日志记录器记录文件不存在异常
		File f = new File("This file is not existing");
		try {
			FileReader fr = new FileReader(f);
		} catch (IOException e){
			localLogger.log(Level.WARNING, "This file is not existing", e);
		}
		
		// throwing记录一条FINER级别的消息和一条以THROW开头的消息
		// 抛出但"不处理"触发
		try {
			var e = new IOException("new IOException()");
			localLogger.throwing("LoggingTest", "main", e);
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Logger test is ending");
	}
}