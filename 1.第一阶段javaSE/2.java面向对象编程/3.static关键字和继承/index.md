### static关键字和继承
+ 基本概念
    + 还有static关键字修饰成员变量表示静态的含义，此时成员变量由对象层级提升为类层级，也就是整个类中只有一份且被所有对象共享，该成员变量随着类的加载准备就绪，与是否创建无关
    + static关键字修饰的成员可以使用引用.的方式访问，但推荐类名.的方式
#### people类和测试类的实现
#### static关键字的基本概念
+ 在非静态的成员方法中既可以使用静态成员变量也可以使用非静态成员变量（原因，静态成员被所有对象共享）
+ 静态成员方法中只能访问静态成员不能访问非静态成员（因为可能还没有创建对象）
    + 静态成员方法中没有this关键字，因为可以通过类名.的方式调用
    + 如果想要访问非静态成员需要在该方法内创建对象
+ 在开发中，只有属于类层级并且被所有对象共享的内容才可以使用static关键字修饰
#### static关键字的使用方式
+ static会随着类被加载被调用和对象创建无关
#### 构造块和静态代码块
+ 构造块在构造函数之前执行，对象被创建一次，构造块生成一次
    
    + 在类体中直接用{}括起来的代码块
+ 静态代码块在类加载时准备就绪，会先于构造块执行，即使创建了多个对象也只会调用一次
    
    + 在类体中用static修饰的{}
+ 示例
    ```java
        public class Test {
            {
                System.out.println("构造块");
            }

            static {
                System.out.println("静态代码块");
            }

            public Test() {
                System.out.println("构造函数体");
            }
        }
    ```
#### main方法的详解
+ 语法格式
    ```java
        public static void main(String[] args) {}
    ```
+ 参数使用举例
    + 写入方法
        ```java
            public static void main(String[] args) {
                System.out.println(Arrays.toString(args));
            }
        ```
    + 传递数值
        ```java
            // 终端输入命令
            java Test.java luna saber
        ```
#### Singleton和SingletonTest类的实现
+ 案例
    + 编程呢实现Singleton类的封装
    + 编写SingletomTest类对Singleton类进行测试，要求main方法中能得到且只能得到该类的一个对象
+ Singleton.java
    ```java
        public class Singleton{

            // 使用static创建共有对象，用于返回需要的一个对象，private让用户不能将该对象赋值为null从而清空
            private static Singleton singleton = new Singleton();

            // 私有化构造方法，使外部不能随便创建对象,创建对象的过程只能在内部完成
            private Singleton () {};

            public static Singleton getInstance () {
                return singleton;
            }
        }
    ```
+ SingletonTest.java
    ```java
        public class SingletonTest {

            public static void main(String[] args) {
                Singleton s1 = Singleton.getInstance();

        }
    ```
#### 单例设计模式
+ 在某些特殊场合一个类对外提供且只提供一个对象时这样的类叫做单例类，设计单例的流程和思想叫做单例设计模式
+ 实现流程
    + 私有化构造方法，使用private修饰构造方法
    + 声明本类类型的引用指向本类类型的对象，并使用private static修饰
    + 提供共有的get方法将对象返回，使用public static修饰
+ 使用场景
    + windows任务管理器
        + 虽然可以重复打开但是同时只能显示一个窗口
+ 单例设计模式分类
    + 懒汉式
        + 当用户调用get方法时创建对象
            ```java
                public class Singleton{

                    private static Singleton singleton = null;

                    private Singleton () {};

                    public static Singleton getInstance () {
                        if(null == singleton) singleton = new Singleton();
                        return singleton;
                    }
                }
            ```
    + 饿汉式（推荐--考虑到多线程的问题--上面的示例就是）
        
        + 类加载立刻创建对象
#### 继承的由来和概念
+ 概念
    
    + 当多个类之间有相同特征和行为时，可以将相同的内容提取出来组成一个公共类，让多个类吸收公共类中已有的特征和行为而在多个类型只需要编写自己独有特征和行为的机制叫做继承
+ 在java中extends关键字表示继承
    + 示例
        ``` java
            public class Worker extends Person{} // 表示Worker类继承Person类
        ```
    + 其中Person类叫做基类，父类，超类
    + 其他Worker类叫做派生类，子类，孩子类

#### 继承的意义
+ 使用继承提高了代码的复用性，可维护性以及扩展性是多态的前提
#### 继承的特点
+ 子类不能继承父类的构造方法和私有方法，但是私有成员变量可以被继承，不可以直接访问
+ 无论使用何种方式构造子类的对象时都会调用父类的无参构造方法，来初始化从父类中继承的成员变量，相当于在构造方法第一行加入super()的效果(super超类就是父类的构造函数，如果没有加编译器自动添加，作用在于初始化成员变量)
    + 如果想要调用父类的有参构造方法在super方法中增加相应对象就可以
+ 使用继承必须满足逻辑关系：子类 is a 父类，也就是不能滥用继承
+ java语言中只支持单继承不支持多继承，也就是说一个子类只能有一个父类但是一个父类可以有多个子类

#### 方法重写的概念和使用
+ 概念
    
    + 从父类中继承下来的方法不满足子类需求时，就需要在子类中重写一个和父类一样的方法来覆盖从父类中继承的方法该方式叫重写
+ 假设父类中已有show方法显示父类中所有的元素，想要在子类中调用show方法增加显示子类中特有的属性
    ```java
        @Override  // 注解，表示这是重写方法，如果增加了该注解但是没有重写会报错
        public void show() {
            super.show(); // 调用父类中的show方法
            System.out.println(sonFeature);
        }
    ```
#### 方法重写的原则
+ 要求方法名相同，参数列表相同，返回类型相同，java5之前父子重写方法类型必须一致，java5之后类型可以不一致
+ 父方法的访问权限必须比子方法大或者相同
+ 要求方法不能抛出更大的异常
#### Animal类的实现
+ 案例
    + Animal类的封装，特征：名字和毛色，提供打印所有特征的方法
        ```java
            public class Animal {

                private String name;

                private String color;

                public Animal() {};

                public Animal(String name,String color) {
                    setName(name);
                    setColor(color);
                }

                public void setName(String name) {
                    this.name = name;
                }

                public void setColor(String color) {
                    this.color = color;
                }

                public String getName() {
                    return name;
                }

                public String getColor() {
                    return color;
                }

                public void show () {
                    System.out.println("该动物名为："+name+" 毛色：" + color);
                }
            }
        ```
#### Dog和DogTest的实现
+ 封装Dog类继承Animal类，特征：牙齿数量，提供打印所有特征的方法
    ```java
        public class Dog extends Animal{

            private int num;

            public Dog () {};

            public Dog(String name, String color, int num) {
                super(name,color);
                setNum(num);
            }

            public void setNum(int num) {
                this.num = num;
            }

            public int num() {
                return num;
            }

            @Override
            public void show() {
                super.show();
                System.out.println("牙齿数量为："+ num);
            }
        }
    ```
+ 实现DogTest类，在main方法中使用无参构造函数和有参方法构造Dog类对象并打印
    ```java
        public class DogTest {

            public static void main(String[] args) {

                Dog dog1 = new Dog();
                dog1.show();
                Dog dog2 = new Dog("luna","blue",18);
                dog2.show();
            }
        }
    ```
#### 构造块和静态代码块的考点
+ 当父类和子类中都有构造块，静态代码块和构造块时，执行顺序
    + 父类先于子类加载（子类需要继承父类的特征所以必须让父类先加载），所以执行顺序如下
        + 父类静态代码块，父类构造块，父类构造方法体，子类静态代码块，子类构造块，子类构造方法体
#### 权限修饰符和包的定义
+ 常用访问控制符
    ```
        --------------------------------------------
        | 修饰符     | 本类 | 同包类 | 子类 | 其他类  |
        | public    | 允许 | 允许   | 允许 | 允许    |
        | protected | 允许 | 允许   | 允许 | 不允许  |
        | 默认       | 允许 | 允许  | 不允许 | 不允许 |
        | private   | 允许 | 不允许 | 不允许 | 不允许 |
        ---------------------------------------------
    ```
+ package由来
    
    + 定义类时需要指定类的名称，但是如果仅仅将类名作为唯一标识，不可避免的会出现明明冲突问题，这会给组件复用以及团队间的合作造成很大麻烦，所以package被用来解决命名问题
+ package定义格式
    + package 包名;
    + package 包名.包名.包名...包名;
+ package也可以实现项目管理，解决命名冲突以及权限控制问题的效果
+ 定义包的规范
    + 组织信息.项目名.模块信息.类名
    + org.apache.commons.lang.StringUtil
+ import
    + 引入包
    + 引入静态成员(java5以后)
        ```
            import static java.lang.System.out;
        ```
#### final修饰类的方法和作用
+ 基本概念
    
    + final本意为“最终的，不可改变的”，可以修饰类，成员变量以及成员方法
+ 使用方式
    + final关键字修饰该类表示该类不能被继承--防止类被烂继承
    + final关键字修饰成员方法表示该类不能被重写，但是可以被继承
    + final关键字修饰成员变量表示该变量必须被初始化且不能被修改
        + 初始化操作可以在类中完成
            ```java
                public class Test{
                    private final int cnt;

                    // { cnt = 2; }

                    public Test {
                        cnt = 2;
                    }
                }
            ```
#### final修饰成员变量的作用
+ 常量概念
    + 开发中很少使用final单独修饰成员变量，通常使用public static final关键字共同修饰成员变量来表达常量的含义，常量的命名规范要求是所有字母都大写，不同单词下划线连接