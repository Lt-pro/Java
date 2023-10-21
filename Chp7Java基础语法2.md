# 一、数据类型
1. 数据类型分类
    * 基本数据类型
    * 引用数据类型

2. 基本数据类型
    * 分类
        |   数据类型|   关键字  |   取值范围    |内存占用|
        |-------|   ------      |-------       |--------|
        |整形   |   byte        |-128-127      |1       |
        |       |   short       |-32768-32767  |2       |
        |       |int(default)   |-2147483648-2147483647(十位数)|4|
        |       |long           |19位数         |    8  |
        |浮点型 |float          |-3.401298e-38——3.402823e38|4|
        |       |double(default)|-4.90000e-324——1.797693e+308|8|
        |字符型 |char           |0-65535        |   2   |
        |布尔型 |boolean        |true,false     |   1   |

    * 注意事项
        + 若定义long类型变量，数据值后面加一个L/l做后缀
        + 若定义float类型变量，数据值后面加一个F/f做后缀
    * 示例
        ```Java
        public class ValueDemo{
        public static void main(String[] args){
            //byte
            byte b = 10;
            System.out.println(b);

            //short
            short s = 20;
            System.out.println(b);

            //int
            int i = 30;
            System.out.println(i);

            //long
            long n = 999999999999L;
            System.out.println(n);
            System.out.println("tom\t18");

            //double
            double d = 3.14;
            System.out.println(d);

            //float
            float f = 3.14e40F;
            System.out.println(f);

            //char
            char c = '中';
            System.out.println(c);

            //boolean
            boolean flag = true;
            System.out.println(flag);
        }
        }
        ```

# 二、标识符
1. 概念
给*类、变量、方法/函数*的起的名字

2. 标识符命名规则——硬性要求
    * 字母、数字、下划线(_)和美元($)组成
    * 不能以数字开头
    * 不能是关键字
    * 区分大小写

3. 标识符命名规则——软性要求
    * 小驼峰命名法: 方法/函数，变量
        + 标识符是一个单词时，*全部小写*  eg: name
        + 标识符由多个单词组成时，*第一个单词首字母小写，其它单词首字母大写*  eg: firstName
    * 大驼峰命名法: 类名
        + 标识符是一个单词时，*首字母大写* eg: Name
        + 标识符由多个单词组成时，*每个单词首字母大写* eg: FirstName
    * 见名知意


# 三、键盘录入
Java使用Scanner类作为键盘输入的接口，一般步骤如下: 
```Java
// 导包 
import java.util.Scanner;
// 创建对象
Scanner sc = new Scanner(System.in);
// 接受输入(让键盘写数据赋给某个变量)
int i = sc.nextInt();   //.nextInt()接受一个整数输入
```

# 内容小结
* 数据类型、数据类型分类、基本数据类型
* 标识符、标识符命名规则、规范
* 键盘录入
* 补充：计算机中的数据存储与表示