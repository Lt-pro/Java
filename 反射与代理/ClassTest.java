import java.util.Random;
import java.lang.ReflectiveOperationException;

public class ClassTest {
	public static void main(String[] args) throws ReflectiveOperationException{
		{
			String str = "ClassTest";
			Class cl = str.getClass();
			System.out.println(str.getClass().getName() + " " + cl.getName());
		}
		
		{
			// 使用字符串获得Class对象，该方法会抛出异常
			String className = "java.util.Random";
			Class cl = Class.forName(className);
			System.out.println(cl.getName());
		}
		
		{
			// 使用字节码获得Class对象，Class对象保存的信息可能是属于类也可能不属于类
			Class cl1 = Random.class;
			Class cl2 = int.class;
			Class cl3 = Double[].class;
		}
		
		{
			// 虚拟机每个类型管理唯一一个Class对象
			Class cl = Random.class;
			if (cl == new Random().getClass()) {
				System.out.println("Random.class is unique");
			}
		}
		
		{
			// 使用Class对象构造类的实例
			Class cl = Random.class;
			Object obj = cl.getConstructor().newInstance();
			System.out.println("A instance constructed by Class Object: " + obj);
		}
		// Class对象可以获取类所关联的资源
	}
}