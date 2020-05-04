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
+ 基本概念
    + 若在构造函数中出现this关键字，代表当前正在构造的对象
    + 若在成员函数中出现this关键字，代表当前正在调用的对象
        ```
            public class Test {

                Test() {
                    System.out.println("构造方法:"+this);
                }
                void show() {
                    System.out.println("成员函数: "+this);
                }
                public static void main(String[] args) {
                    Test test = new Test();
                    test.show();
                }

            }
        ```
    + this关键字本质上就是当前类类型的引用变量
#### this关键字的工作原理
+ 描述
    + 在构造方法和成员方法中访问成员变量时，编译器会加上this前缀，当不同的对象调用同一个方法时，由于调用对象不同this关键字也不同从而通过this关键字访问的结果也不同
#### this关键字的使用方式
+ 使用方式一
    + 当局部变量与成员变量重名时，如果想要使用成员变量需要在成员变量前加上this
    ```
        Person(String name,int age) {
            this.name = name;
            this.age = age;
        }
    ```
+ 使用方式二
    + this除了可以调用成员变量和成员方法外还可以作为方法的返回值
        ```
            Person getPerson() {
                return this;
            }
        ```
+ 使用方式三
    + 构造函数中第一行this(),可以调用其他构造函数
#### 引用变量的注意事项
+ 引用变量必须指向一个地址（值不为null），否则会报空指针异常
+ 引用类型变量用于存放对象的地址，可以给引用类型赋值为null，表示不指向任何对象
+ 当某个引用类型变量为null时无法对对象实施访问，此时如果通过引用访问成员变量或调用方法会产生NullPointerException
#### 阶乘的计算方式
+ 循环(递推)
+ 递归
    ```
        public int fun(int n) {
            if(1 == n) return 1;
            return n*fun(n-1);
        }
    ```
#### 递归的原理
+ 使用递归必须有递归的规律以及推出条件
+ 使用递归必须使问题简单化而不是复杂化
+ 若递归影响到性能则用递推取代
#### 费氏数列递归实现
+ 递归
    ```
        public class Test {

            public static void main(String[] args) {
                System.out.println(fun1(55));
            }

            public static int fun1(int n) {
                if(1 == n || 2 == n) return 1;
                return fun1(n-1)+fun1(n-2);
            }

        }
        // 反应会很慢
    ```
+ 递推
    ```
        public static void main(String[] args) {
            System.out.println(fun1(4));
        }

        public static int fun1(int n) {
            int i = 1;
            int j = 1;
            while(n-2 > 0) {
                j = i + j;
                i = j - i;
                n--;
            }
            return j;
        }
    ```
#### 代码拆分实现
+ 描述
    + 在主函数中创建需要的功能类的对象，然后通过该对象去调用该功能类下的方法
    + 比如想要调用Fun类下的fun1方法，在主函数中写下
        ```
            Fun f = new Fun();
            f.fun1();
        ```
#### 封装的概念
+ 通常情况下可以在测试类中给成员变量赋值一些合乎语法规范但是不符合现实的数值
+ 为了避免上述情况发生需要对成员变量进行密封包装处理来隐藏成员变量的细节以及保证成员变量数值的合理性，该机制叫做封装
#### 封装实现
+ 三步走
    + 私有化成员变量加上private关键字（加上该关键字后，成员变量只能在定义该变量中的类里访问，不能从外部直接访问，但是如果类内定义了可以调用该变量的公有方法，那么可以从外部调用该方法完成间接访问）
    + 提供公有的get和set方法，并且在方法体内进行合理值的判断
    + 在构造方法中调用set方法进行合理值判断
+ 示例
    ```
        private int id;
        private String name;
        public Student() {};
        public Student(int id,String name) {
            setId(id);
            setName(name);
        }

        public setId(int id) {
            if(id > 0) this.id = id;
            else System.out.println("this id is invalid!");
        }

        public setName(String name) {
            this.name = name;
        }
    ```
#### 学生信息录入javaBean
+ 案例
    + 提示用户输入班级的学生人数以及美国学生的信息（学号姓名）并打印
    + 学生类person 
        ```
            public class Person {

                private int id;

                private String name;

                public void setId(int id) {
                    if(id > 0) this.id = id;
                    else System.out.println("this id is invalid!");
                }

                public int getId() {
                    return id;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getName() {
                    return name;
                }
            }
        ```
    + 主函数
        ```
            import java.util.*;
            public class Test {

                public static void main(String[] args) {
                    
                    Person[] persons;

                    Scanner sc = new Scanner(System.in);
                    System.out.println("please input the num of student: ");
                    int n = sc.nextInt();
                    persons = new Person[n];

                    int num = 0;
                    while(num < n) {
                        Person temp = new Person();
                        System.out.println("please input the id of student: ");
                        temp.setId(sc.nextInt());
                        System.out.println("please input the name of student: ");
                        temp.setName(sc.next());
                        persons[num++] = temp;
                    }

                    for(int i = 0;i < persons.length;i++) {
                        System.out.println("第"+(i+1)+"个学生的学号是: "+persons[i].getId()+"姓名是: "+persons[i].getName());
                    }
                }

            }
        ```
+ javaBean概念
    + javaBean是一种Java语言写成的组件，其他Java类可以通过反射机制和操作这些JavaBean的属性
    + JavaBean就是符合以下标准的Java类
        + 类是公共的
        + 有一个无参构造函数
        + 有属性并且有对应的get，set方法