import java.util.Map;
import java.util.HashMap;

public class OOMTest {
	public static void main(String[] args) {
		Map<Long, Long> map = new HashMap<>();
		long i = 0;
		while (true) {
			map.put(Long.valueOf(i), Long.valueOf(i));
			i++;
			//System.out.println(i);
			//打印很慢
		}
	}
}