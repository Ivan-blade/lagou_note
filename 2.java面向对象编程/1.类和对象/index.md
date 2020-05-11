### 类和对象
#### 对象和面向对象的概念
+ 面对对象的概念
    + 一切皆对象
    + 面向对象指以属性和行为的观点去分析现实生活中的事物
#### 面向对象编程的概念
+ 面向对象编程的概念
    + 面向对象编程指先以面向对象的思想进行分析，然后使用面向对象的语言进行表达的过程
#### 类和对象的概念
+ 描述
    + 对象主要指现实生活中客观存在的实体，在java语言中对象体现为内存空间中的一块存储区域
    + 类简单来说就是分类，是对具有相同特征和行为的多个对象共性的抽象描述，在java语言中体现为一种引用数据类型，里面包含了描述特征/属性的成员变量以及描述行为的成员方法
    + 类是构建对象的模板，对象的数据结构由定义它的类决定
#### 类和对象以及引用的定义
+ 类的定义
    + 格式
        ```java
            class 类名{
                类体;
            }
        ```
    + 注意
        
        + 通常情况下，当类名由多个单词组成时要求每个单词首字母大写
+ 成员变量的定义
    + 格式
        ```java
            class 类名 {
                数据类型 成员变量名 = 初始值
            }
        ```
    + 注意
        
        + 当成员变量由多个单词组成时，通常要求第一个单词首字母小写从第二个单词起每个单词的首字母大写
+ 对象创建
    + 格式
        ```java
            new 类名();
        ```
    + 注意
        + 当一个类定义完毕之后，可以使用new关键字，来创建该类的对象，这个过程叫做类的实例化
        + 创建对象的本质就是在内存空间的堆区申请一块内存区域，用于存放该对象独有特征信息
+ 引用的定义
    + 基本概念
        + 使用引用数据类型（java两大数据类型，基本数据类型和引用数据类型，见java语言基础）定义的变量叫做引用型变量，简称"引用"
        + 引用变量主要用于记录对象在堆区中的内存地址信息便于下次访问
    + 语法格式
        ```java
            类名 引用变量名
            引用变量名.成员变量名
        ```
+ 解读
    ```java
        Person person = new Person();
        // 声明Person类型的引用指向Person类型的对象
    ```
#### person类的定义
+ 案例
    + 编程实现person类
        ```java
            public class Person {

                String name;

                int age;

                public static void main(String[] args) {

                    // 声明person类型引用，指向person类型对象
                    Person person = new Person();

                    // 打印对象属性默认值
                    System.out.println("我是" + person.name + "今年" + person.age);

                    // 修改成员变量
                    person.name = "华纱不揭";
                    person.age = 21;
                    // 日。。。都二十一了

                    System.out.println("我是" + person.name + "今年" + person.age);
                }
            }
        ```
#### person类代码的执行流程和内存分析
+ 以上流程执行描述
    + class类定义被存放在方法区中
    + <a href="https://blog.csdn.net/terstdfhuc/article/details/86526047">Java内存图以及堆、栈、常量区、静态区、方法区的区别</a>
    + main方法启动之后Person person在栈区中创建变量
    + new Person()，在堆区中申请内存空间
    + 将堆区中地址赋值给栈区中相应的变量
#### point类的定义
+ 案例
    + 编程实现point类的定义，特征有：横纵坐标（整数），要求在main方法中声明Point类型的引用指向Point对象并打印特征，然后将横坐标修改为3和5后再次打印

    ```java
        public class Point {

            int row;
            int col;

            public static void main(String[] args) {

                Point point = new Point();

                point.row = 3;
                point.col = 5;

                System.out.println("row is "+point.row+" col is "+point.col);
            }

        }
    ```
#### 成员方法和格式和详解
+ 描述
    + 结构
        ```java
            class 类名{
                返回值类型 成员方法名（形参列表） {
                    成员方法体；
                }
            }
        ```
    + 注意
        
        + 当成员方法名由多个参数组成时，要求从第二个单词起每个单词的首字母大写
+ 返回值类型详解
    + 返回值主要从方法体内返回到方法体外的数据内容
    + 返回值类型主要指返回值的数据类型，可以是基本类型，也可以是引用数据类型
    + 在方法体中使用return关键字可以返回具体的数据内容并结束当前方法
+ 形参列表的详解
    + 形式参数主要是将方法体外的数据内容代入到方法体内部
    + 形式参数列表主要是指多个形式参数组成的列表。
+ 方法块
    + 成员方法主要用于编写方法功能的语句
    + 成员方法可以实现代码的重用，简化代码
#### person类中无参返回值成员方法的定义
+ 示例
    ```java
        package temp;

        public class Person {

            String name;

            int age;

            // 成员变量和成员函数都是类的内部成员，因此可以直接访问成员变量不需要加引用前缀（person.name里person代表引用前缀）
            public void show() {
                System.out.println("我是" + name + "今年" + age);
            }
        }
    ```
#### person类中无参无返回值成员方法的调用
+ 描述
    + 结构
        + 引用变量名.成员方法名（实参列表）
    + 实际参数列表主要用于对形式参数列表进行初始化操作，因此这个参数的个数，类型以及顺序都要完全一致
    + 实际参数可以传递直接量，变量，表达式，方法调用
+ 示例
    ```java
        public static void main(String[] args) {

            // 声明person类型引用，指向person类型对象
            Person person = new Person();

            // 打印对象属性默认值
            System.out.println("我是" + person.name + "今年" + person.age);

            // 修改成员变量
            person.name = "华纱不揭";
            person.age = 21;
            // 日。。。都二十一了

            person.show();
        }
    ```
#### point类中无参无返回值成员方法的定义
+ 示例
    ```java
        public class Point {

            int row;
            int col;

            public void show() {
                System.out.println("row is "+row+" col is "+ col);
            }
        }
    ```
#### person类中有参无返回值成员方法的使用
+ 示例
    ```java
        // 类体内
        // 修改姓名
        public void setName(String name) {
            this.name = name;
        }

        // 修改年龄
        public void setAge(int age) {
            this.age = age;
        }
        // 主函数内
        Person person = new Person();
        person.setName("华纱不揭");
        person.setAge(21);
    ```
#### person类中多个形参成员方法的使用
+ 示例
    ```java
        public void setAll (String name,int age) {
            this.name = name;
            this.age = age;
        }
    ```
#### point类中有参无返回值成员方法的使用
+ 与person类似
#### person类中可变长参数的使用
+ 结构
    
    + 返回值类型 方法名(参数类型... 参数名)
+ 说明
    + 方法参数部分指定类型的参数个数是可以改变的也就是0-n个
    + 一个方法的形参列表中最多只能声明一个可变长形参，并且放到参数列表的末尾
+ 示例
    ```java
        // 可变长参数使用
        public void showAll(String... arrs) {
            for(int i = 0;i < arrs.length;i++) {
                System.out.print(arrs[i]+" ");
            }
        }

        person.showAll("兵","贵","神","速");
    ```
#### point类中可变长参数的使用
+ 类似person
#### person类中无参有返回值参数的使用
+ 示例
    ```java
        // 获取姓名并返回
        public String getName() {
            return name;
        }
        // 获取年龄并返回
        public int getAge() {
            return age;
        }
    ```
#### point类中无参有返回值方法的使用
+ 你懂的
    + 不，我不懂。。。懒人
#### 方法的传参过程
+ 待描述结构
    ```java
        int max(int a,int b) {};

        int a = 5, b = 6;
        int res = m.max(a,b);
    ```
+ 过程描述
    + 为main方法中为变量a,b,res分配内存空间
    + 调用max方法为max方法中的a,b分配内存空间
    + 将实参变量的数值赋值到形参变量的内存空间中
    + max方法运行完毕后，形参变量空间释放
    + main方法中的res变量获取到max变量的返回值
    + main方法结束之后释放相关资源
#### 参数传递的注意事项
+ 基本数据类型的变量作为方法的参数传递时，形参变量数值的改变通常不会影响到实参参数变量的数值，因为两个变量有各自独立的内存空间
+ 引用数据类型的变量作为方法的参数传递时，形参变量指向内容的改变会影响到实参指向内容的数值，因为两个变量指向同一块内存空间
+ 当引用数据类型的变量作为方法的参数传递时，若形参变量改变指向后再改变指定的内容通常不会影响到实参变量指向内容的改变，因为两个变量指向不同的空间
+ 示例
    ```java
        package temp;

        import java.util.*;
        public class test {
            

            void show1(int ia) {
                // 修改并输出形参值
                ia = 200;
                System.out.println(ia); // 200
            }

            void show2(int[] arr1) {
                arr1[0] = 200;
                System.out.println(arr1[0]); // 200
            }
            public static void main(String[] args) {

                test t = new test();
                
                int ib = 10;
                t.show1(ib);
                // 输出基本数据类型实参值发现形参改变后，实参并没有改变
                System.out.println(ib);

                int[] arr2 = new int[] {10,20};
                t.show2(arr2);
                // 输出引用数据类型实参后，实参已经改变
                System.out.println(arr2[0]);
            }   
        }
    ```
+ 对于引用数据类型方法的修改
    ```java
        // 类体
        void show2(int[] arr1) {
        arr1 = new int[2];
        arr1[0] = 200;
        System.out.println(arr1[0]); // 200

        // 主函数
        int[] arr2 = new int[] {10,20};
        t.show2(arr2);  // 200
        // 输出引用数据类型实参后，实参已经改变
        System.out.println(arr2[0]); // 10
    }
    ```
+ 说明
    + 形参和实参是两个不同的值，改变基本数据类型形参不会影响到实参
    + 改变引用数据类型的内容，底层也遵循上面的原理，但是我们要分清我们是改变了引用数据类型的地址，还是改变了引用数据类型指向地址中的数据内容，前者不会影响实参，后者因为形参和实参都指向同一个内存地址，修改了形参指向内存地址的值也就是修改实参指向内存地址的值
+ 内存结构之栈区
    + 栈用于存放程序运行过程中所有的局部变量，一个运行的java程序从开始到结束会有多次方法调用
    + JVM会为每一个方法的调用在栈中分配一个对应的空间，这个空间称为该方法的参数，局部变量等数据
    + 一个方法调用完成之后，对应的栈帧将被清除
+ 传参的相关概念
    + 参数分为形参和实参，定义方法的参数叫形参，调用方法时传递的参数叫做实参
    + 调用方法时采用值传递将实参传递给给形参，方法内部事实上在用形参
    + 所谓值传递就是当参数是基本类型时传递参数的值，比如实参i = 10，真实传参时，把10赋给了形参，当参数是对象时，传递对象的值是对象的地址
#### 总结