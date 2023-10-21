# 一、注释和关键字
1. 注释
    * 注释概念: 在程序指定位置添加说明性文字，对代码进行解释
    * 分类
        + 单行注释: `//`
        + 多行注释: `/*  */`
        + 文档注释: `/**  */`
    * HelloWorld示例
        ```Java
        public class HelloWorld{
            //叫做main函数，表示程序的主入口
            public static void mian(String[] args){
                /*叫做输出语句(打印语句)
                会把小括号的内容输出(打印)
                */
               System.out.println("HelloWorld");
            }
        }
        ```
    * 注意事项
        + 注释的内容不参与编译也不参与运行
        + 注释不要要嵌套

2. 关键字
    * 概念: Java赋予特定含义的英文单词，大概有50多个
    * 特点
        + 字母全部小写
        + 常用代码编辑器会对关键字高亮显示
    * class关键字示例
        ```Java
        public class HelloWorld{
            //class: 用于定义/创建一个类，类是Java最基本的组成单元
        }
        ```

# 二、字面量和变量
1. 字面量
    * 概念: 数据在程序中的书写格式
    * 分类
        + 整数类型: `666, -88`
        + 小数类型: `13.14, -5.21`
        + 字符串类型: 双引号括起来的内容， `"HelloWorld, "黑马程序员""`
        + 字符类型: 单引号括起来的内容，只能有一个内容， `'A', '0', '我'`
        + 布尔类型: 只有两个值`true, false`
        + 空类型: 只有一个值`null`
    * 示例
        ```Java
        public class ValueDemo{
            public static void main(String[] args){
                //目标: 掌握常见数据的书写方式

                //整数
                System.out.println(666);
                System.out.println(666);

                //小数
                System.out.println(1.93);
                System.out.println(-3.71);

                //字符串
                System.out.println("黑马程序员");
                System.out.println("啊啊啊啊");

                //字符
                System.out.println('男');
                System.out.println('女');
                
                //布尔
                System.out.println(true);
                System.out.println(false);

                //空
                //细节: null不能直接打印，只能用字符串形式
                System.out.println("null");
            }
        }
        ```

    * 部分特殊字符
        + `\t`制表符: 在打印时把前面字符串的长度补齐到8或8的倍数。最少一个空格最多补8个空格
            ```Java
            public class ValueDemo{
            public static void main(String[] args){
                System.out.println("name\tage");
                System.out.println("tom\t18");
            }
            }
            ```
        + `\n`换行


2. 变量
    * 概念: 当某个数据经常发生改变时，使用变量存储。数据变化时，修改变量记录的值即可
    * Java变量定义格式
        >数据类型 变量名 = 数据值;
        >数据类型: 限定存储数据的类型
        >变量名: 存储空间的名字
        >数据值: 存储空间保存的数据值  
    * 变量的使用
        + 输出打印
            ```Java
            int a = 10;
            System.out.println(a);
            ```
        + 参与计算
            ```Java
            int a = 10;
            int b = 20;
            System.out.println(a+b);
            ```
        + 赋值
            ```Java
            int a = 10;
            System.out.println(a);
            int a = 20;
            System.out.println(a);
            ```
    * 注意事项
        + 变量名不可重复定义
        + 变量只能存储一个值
        + 一条语句可以定义多个变量
        + 变量使用前一定要赋值
        + 变量的作用域
        + 示例
            ```Java
            public class ValueDemo{
            public static void main(String[] args){
                // 错误示例
                int a = 10;
                int a = 20;
                System.out.println(a);

                //定义多个变量
                int b = 100, c = 200, d = 300;

                //变量使用前一定要赋值
                int e;
                //e = 138;
                //tip: 定义变量时直接赋值初始化
                System.out.println(a);
            }
            }
            ```

# 三、变量练习
tip: 类名和文件名保持一致
```Java
public class VariableTest{
    public static void main(String[] args){
        //一开始没有乘客
        int count = 0;
        //第一站上去一位乘客
        count = count + 1;
        //第二站上去两位乘客，下去一位乘客
        count = count + 2;
        count = count - 1;
        //第三站上去两位乘客，下去一位乘客
        count = count + 2;
        count = count - 1;
        //第四战下来一位乘客
        count = count - 1;
        //第五站上去一位乘客
        count = count + 1;
        System.out.println(count);
    }
}
```


# 四、计算机中的数据存储
计算机中的数据都是以二进制形式存储的，因为二进制足够简单  
进制是表示数的一种方法，不同进制可以互相转换
1. 数据存储形式
    * 文本: 数字、字母、汉字、其它字符
    * 图像
    * 语音
    * 其它，复合数据类型

2. 不同进制数字的写法
    * 十进制: 0-9组成，无前缀
    * 二进制: 0，1组成，0b开头. `0b0101`
    * 八进制: 0-7组成, 0开头. `017`
    * 十六进制: 0-f组成, 0x开头. `0xff`

3. 字符表示
    * ASCII码
        表示常用数字、英文字符、字符、其它不可打印字符，每一个字符与一个二进制数(ASCII码)唯一对应

    * 中文字符表示
        + gb2312: 简体中文汉字编码
        + BIG5: 台湾地区繁体字
        + GBK
        + Unicode: 国际标准字符集

4. 数字图像
一个数字图像由若干像素点构成，一个像素点从若干等级的亮度中取值。根据像素点表示的不同，常见的有灰度图、RGB图像

5. 语音
波形数据采样

# 内容小结
* 注释与关键字
* 字面量、字面量的写法
* 变量、变量的定义与使用