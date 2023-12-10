import java.util.BitSet;
import java.util.function.Function;

public class BloomFilter<T> {
	private final int size;
	private final BitSet bitSet;
	private final Function<T, Integer>[] hashFunctions;
	
	private static final int[] SEEDS = new int[]{3, 13, 46, 71, 91, 134};
	
	public BloomFilter(int size) {
		this.size = size;
		this.bitSet = new BitSet(size);
		//@SuppressWarnings("unchecked")
		this.hashFunctions = new Function[]{
			value -> {
				int h;
				return (value == null) ? 0: Math.abs(SEEDS[0] * (size - 1) & ( (h = value.hashCode()) ^ (h >>>16) ));
			},
			value -> {
				int h;
				return (value == null) ? 0: Math.abs(SEEDS[1] * (size - 1) & ( (h = value.hashCode()) ^ (h >>>16) ));
			},
			value -> {
				int h;
				return (value == null) ? 0: Math.abs(SEEDS[2] * (size - 1) & ( (h = value.hashCode()) ^ (h >>>16) ));
			},
			value -> {
				int h;
				return (value == null) ? 0: Math.abs(SEEDS[3] * (size - 1) & ( (h = value.hashCode()) ^ (h >>>16) ));
			},
			value -> {
				int h;
				return (value == null) ? 0: Math.abs(SEEDS[4] * (size - 1) & ( (h = value.hashCode()) ^ (h >>>16) ));
			},
			value -> {
				int h;
				return (value == null) ? 0: Math.abs(SEEDS[5] * (size - 1) & ( (h = value.hashCode()) ^ (h >>>16) ));
			}
		};
	}
	
	public void add(T element) {
		for (Function<T, Integer> hashFunction : hashFunctions) {
			bitSet.set(hashFunction.apply(element), true);
		}
	}
	
	public boolean mightContain(T element) {
		for (Function<T, Integer> hashFunction : hashFunctions) {
			if (!bitSet.get(hashFunction.apply(element))) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
        int size = 1000; // 设置布隆过滤器的大小

        BloomFilter<String> bloomFilter = new BloomFilter<>(size);

        // 添加元素到布隆过滤器
        bloomFilter.add("apple");
        bloomFilter.add("orange");
        bloomFilter.add("banana");

        // 检查元素是否可能存在于布隆过滤器中
        System.out.println(bloomFilter.mightContain("apple"));   // 输出 true
        System.out.println(bloomFilter.mightContain("grapes"));  // 输出 false
    }
}