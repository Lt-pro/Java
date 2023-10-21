# 一、内容概述
1. Java基础语法
2. 面向对象
3. API
4. 字符串
5. 集合
6. 拼图游戏

# 二、基础语法
1. Java入门
2. 小概念
3. IDEA和运算符
4. 判断和循环
5. 方法
6. 数组
7. 斯坦福大学练习题

# 三、 下载并安装JDK
官网链接: http://www.oracle.com  
安装建议: 安装路径不要包含中文、空格、特殊符号，一键安装  
长期支持版本: Java8、Java17(稳定版本)  

安装目录解释
1. bin
    各种工具命令, 重要的有javac、java
2. conf
    相关配置文件
3. include
    一些平台特定的头文件
4. jmods
    各种模块
5. legal
    各模块的授权文档
6. lib
    工具的一些补充JAR包

# 四、 HellowWorld案例
1. 记事本编写程序
    Java源文件扩展名为.java
    ```Java
    public class HelloWorld {

	public static void main(String[] args) {
		System.out.print("Hello,World!");
	}

    }
    ```
    tip: 文件资源管理器——显示——勾选文件扩展名。显示文件扩展名  
    tip: 不建议`System.out.println()`输出中文

2. 编译文件
    .java文件转换为.class文件
    `javac name.java`   
    `javac -encoding utf-8 name.java`编译含汉字.java文件  
    tip: name可以是相对路径也可以是绝对路径
3. 运行程序
    `java name`
    tip: 无需后缀名，文件名和类名相同

# 五、 常见问题
1. 标点符号
    必须使用英文标点符号
2. 大小写问题
    Java区分大小写


# 六、 Notepad++
这是一个高级记事本，可以显示行号、特殊词高亮显示  
  
中文字符: 设置——首选项——新建——编码——UTF-8
