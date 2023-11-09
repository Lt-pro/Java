import java.io.*;
import java.util.logging.*;
import java.lang.Math;

// 两个日志记录器，分别记录正弦函数和二次函数的值
public class TwoFileLogger {
	private static final Logger sinLogger = Logger.getLogger("sinLogger");
	private static final Logger quadraticLogger = Logger.getLogger("quadraticLogger");
	
	// sinLogger.setLevel(Level.OFF);
	// quadraticLogger.setLevel(Level.OFF);
	public static void main(String[] args) throws IOException{
		sinLogger.setUseParentHandlers(false);
		var sinHandler = new FileHandler("./java%u.log");
		sinHandler.setFormatter(new SimpleFormatter());
		sinLogger.addHandler(sinHandler);
		
		quadraticLogger.setUseParentHandlers(false);
		var quadraticHandler = new FileHandler("./java%u.log");
		quadraticHandler.setFormatter(new SimpleFormatter());
		quadraticLogger.addHandler(quadraticHandler);
		
		for (int i=0; i<20; i++) {
			sinLogger.info("the "+i+"-th value of sin(i) = " + Math.sin(i));
			quadraticLogger.info("the "+i+"-th value of i^2 = " + i*i);
		}
		
	}
	
}