# 排序与lambda表达式

## 一、lambda表达式

lambda表达式创建一个函数的实例，Java中只有一个抽象方法的接口等效于函数指针，lambda表达式等效于实现抽象方法的一个对象，接口变量可以引用lambda表达式，并通过调用抽象方法执行lambda表达式的结果。

lambda表达式的一般语法如下：

`(形参1, 形参2, ...)->{方法体}`

`(int a, int b)->{return b - a;}`是一个lambda表达式
- 方法体只有一条语句时可以忽略花括号，有返回值时还需要去掉return。如`(int a, int b)->b - a;`
- 形参类型可以忽略，如`(a, b)->b - a;`
- 参数列表只有一个元素时，可省略花括号，但空参数列表不可。如`a->System.out.println(a);`, `()->System.out.println("Hello world");`

示例代码：

```Java
// 使用 Lambda 表达式实现一个简单的加法方法
interface MathOperation {
    int operate(int a, int b);
}

public class LambdaExample {
    public static void main(String[] args) {
        // Lambda 表达式作为参数传递给方法
        MathOperation addition = (int a, int b) -> a + b;

        // 调用方法并使用 Lambda 表达式进行加法运算
        int result = operate(10, 5, addition);
        System.out.println("加法结果: " + result);
    }

    public static int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operate(a, b);
    }
}
```

## 二、Arrays.sort方法

Arrays.sort()方法支持对数组类型元素进行升序排序，对于对象数组还支持自定义排序。
自定义排序有两种实现方法，如果是自定义类型可实现Comparab接口并重写compareTo方法；如果不是可以传入Comparator对象，对于自定义类型也可使用该方法。

### 升序排序示例

对整型数组升序排序:

```java
import java.util.Arrays;

public class SortExample {
    public static void main(String[] args) {
        int[] numbers = {5, 2, 9, 1, 5, 6};
        
        // 对整型数组进行排序
        Arrays.sort(numbers);
        
        // 输出排序后的数组
        System.out.println("排序后的数组：");
        for (int number : numbers) {
            System.out.print(number + " ");
        }
    }
}
```

对String数组升序排序：

```java
import java.util.Arrays;

public class SortExample {
    public static void main(String[] args) {
        String[] names = {"Alice", "John", "Emma", "Bob", "David"};
        
        // 对字符串数组进行排序
        Arrays.sort(names);
        
        // 输出排序后的数组
        System.out.println("排序后的数组：");
        for (String name : names) {
            System.out.print(name + " ");
        }
    }
}
```

### 使用Comparable接口排序

```Java
import java.util.Arrays;

class Person implements Comparable<Person> {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(Person otherPerson) {
        // 根据年龄排序
        return Integer.compare(this.age, otherPerson.age);
    }
}

public class SortExample {
    public static void main(String[] args) {
        Person[] persons = {
                new Person("Alice", 25),
                new Person("John", 20),
                new Person("Emma", 30)
        };

        // 对自定义对象数组进行排序（按年龄）
        Arrays.sort(persons);

        // 输出排序后的数组
        System.out.println("按年龄排序后的数组：");
        for (Person person : persons) {
            System.out.println(person.getName() + " - " + person.getAge());
        }
    }
}
```

### 使用Comparator进行排序

对整型数组降序排序：
```Java
import java.util.Arrays;
import java.util.Collections;

public class SortExample {
    public static void main(String[] args) {
        Integer[] numbers = {5, 2, 9, 1, 5, 6};

        // 对整型数组进行降序排序
        //Arrays.sort(numbers, Collections.reverseOrder());
        Arrays.sort(numbers, (a, b) -> b - a);

        // 输出降序排序后的数组
        System.out.println("降序排序后的数组：");
        for (int number : numbers) {
            System.out.print(number + " ");
        }
    }
}
```

int[]与Integer[]相互转换
```Java
import java.util.Arrays;

public class ArrayConversionExample {
    public static void main(String[] args) {
        int[] intArray = {1, 2, 3, 4, 5};

        // int[] 转换为 Integer[]
        Integer[] integerArray = Arrays.stream(intArray).boxed().toArray(Integer[]::new);

        // 输出转换后的 Integer[] 数组
        System.out.println("转换后的 Integer[] 数组：");
        for (Integer num : integerArray) {
            System.out.print(num + " ");
        }
    }
}
```

```Java
import java.util.Arrays;

public class ArrayConversionExample {
    public static void main(String[] args) {
        Integer[] integerArray = {1, 2, 3, 4, 5};

        // Integer[] 转换为 int[]
        int[] intArray = Arrays.stream(integerArray).mapToInt(Integer::intValue).toArray();

        // 输出转换后的 int[] 数组
        System.out.println("转换后的 int[] 数组：");
        for (int num : intArray) {
            System.out.print(num + " ");
        }
    }
}
```

## 三、对整数数组排序并返回在原始数组的下标

使用Comparable接口: 
```Java
import java.util.Arrays;
import java.util.Comparator;

class IndexedNum implements Comparable<IndexedNum> {
    private int index;
    private int value;

    public IndexedNum(int index, int value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(IndexedNum other) {
        return Integer.compare(this.value, other.value);
    }
}

public class SortWithIndices {
    public static void main(String[] args) {
        int[] nums = {7, 2, 5, 1, 9};

        // 创建 IndexedNum 对象数组，存储值和对应的索引
        IndexedNum[] indexedNums = new IndexedNum[nums.length];
        for (int i = 0; i < nums.length; i++) {
            indexedNums[i] = new IndexedNum(i, nums[i]);
        }

        // 对 IndexedNum 数组进行排序
        Arrays.sort(indexedNums);

        // 输出排序后的结果以及对应原始下标
        System.out.println("排序后的数组和对应原始下标：");
        for (IndexedNum indexedNum : indexedNums) {
            System.out.println("值：" + indexedNum.getValue() + "，原始下标：" + indexedNum.getIndex());
        }
    }
}
```

使用Comparator接口：

- 显式使用Comparator:
    ```Java
    import java.util.Arrays;
    import java.util.Comparator;

    class IndexedNum {
        private int index;
        private int value;

        public IndexedNum(int index, int value) {
            this.index = index;
            this.value = value;
        }

        public int getIndex() {
            return index;
        }

        public int getValue() {
            return value;
        }
    }

    public class SortWithIndices {
        public static void main(String[] args) {
            int[] nums = {7, 2, 5, 1, 9};

            // 创建 IndexedNum 对象数组，存储值和对应的索引
            IndexedNum[] indexedNums = new IndexedNum[nums.length];
            for (int i = 0; i < nums.length; i++) {
                indexedNums[i] = new IndexedNum(i, nums[i]);
            }

            // 使用自定义的 Comparator 对象对 IndexedNum 数组进行排序
            Comparator<IndexedNum> customComparator = Comparator.comparingInt(IndexedNum::getValue);
            Arrays.sort(indexedNums, customComparator);

            // 输出排序后的结果以及对应原始下标
            System.out.println("排序后的数组和对应原始下标：");
            for (IndexedNum indexedNum : indexedNums) {
                System.out.println("值：" + indexedNum.getValue() + "，原始下标：" + indexedNum.getIndex());
            }
        }
    }

    ```

- 隐式使用Comparator:
    ```Java
    import java.util.Arrays;

    class IndexedNum {
        private int index;
        private int value;

        public IndexedNum(int index, int value) {
            this.index = index;
            this.value = value;
        }

        public int getIndex() {
            return index;
        }

        public int getValue() {
            return value;
        }
    }

    public class SortWithIndices {
        public static void main(String[] args) {
            int[] nums = {7, 2, 5, 1, 9};

            // 创建 IndexedNum 对象数组，存储值和对应的索引
            IndexedNum[] indexedNums = new IndexedNum[nums.length];
            for (int i = 0; i < nums.length; i++) {
                indexedNums[i] = new IndexedNum(i, nums[i]);
            }

            // 使用 lambda 表达式定义比较器进行排序
            Arrays.sort(indexedNums, (num1, num2) -> Integer.compare(num1.getValue(), num2.getValue()));

            // 输出排序后的结果以及对应原始下标
            System.out.println("排序后的数组和对应原始下标：");
            for (IndexedNum indexedNum : indexedNums) {
                System.out.println("值：" + indexedNum.getValue() + "，原始下标：" + indexedNum.getIndex());
            }
        }
    }
    ```

## 四、ChatGPT源文件

https://chat.openai.com/share/c96c0ff2-f640-499d-809f-ae1e5649e19f