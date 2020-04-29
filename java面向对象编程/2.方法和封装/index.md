### 方法和封装
#### 构造方法的概念和使用
+ 结构
    ```
        class 类名 {
            类名（形参列表） {
                构造方法体;
            }
        }
    ```
+ 默认构造方法
    + 当一个类中没有定义构造方法时，编译器会自动添加一个无参构造方法，叫做默认/缺省构造方法，例如：Person() {}
    + 若类中出现了构造方法，则编译器不再提供任何形式的构造方法
#### 构造方法的作用
+ 案例
    ```
        public class Person {

            String name;

            int age;

            Person(String name,int age) {
                this.name = name;
                this.age = age;
            }

            public static void main(String[] args) {

                // 声明person类型引用，指向person类型对象
                Person person = new Person("华纱不揭",21);

                // 打印对象属性默认值
                System.out.println("我是" + person.name + "今年" + person.age);
        }
        }
    ```
+ 描述
    + 使用new关键字创建对象时会自动调用构造方法实现成员变量初始化工作
#### Point类的定义
+ 同上
#### 重载的概念和体现形式
+ 概念
    + 方法名称相同参数列表不同这样的方法之间构成重载关系
+ 示例
    ```
        public class Test {

            void show() {
                System.out.println("have no value");
            }

            void show(String val) {
                System.out.println("you have input "+ val);
            }
            public static void main(String[] args) {
                Test test = new Test();
                test.show();
                test.show("whoami");
            }

        }
    ```
+ 体现形式
    + 参数的个数不同，顺序不同，类型不同与返回值类型和形参变量名无关，但返回值类型最好相同
    + 判断方法能否重载的核心：调用方法时能否加以区分
#### 重载的实际意义
+ 描述
    + 重载的实际意义在于调用者只需要记住一个方法名六可以调用各种不同的版本，来实现各种不同的功能
    + 如java.io.PrintStream类中的println方法
#### this关键字的基本概念
#### this关键字的工作原理
#### this关键字的使用方式
#### 引用变量的注意事项
#### 阶乘的计算方式
#### 递归的原理
#### 费氏数列递归实现
#### 代码拆分实现
#### 封装的概念
#### 封装实现
#### 学生信息录入javaBean