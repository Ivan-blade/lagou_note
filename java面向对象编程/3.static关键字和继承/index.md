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
    ```
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
    ```
        public static void main(String[] args) {}
    ```
+ 参数使用举例
    + 写入方法
        ```
            public static void main(String[] args) {
                System.out.println(Arrays.toString(args));
            }
        ```
    + 传递数值
        ```
            // 终端输入命令
            java Test.java luna saber
        ```
#### Singleton和SingletonTest类的实现
+ 案例
    + 编程呢实现Singleton类的封装
    + 编写SingletomTest类对Singleton类进行测试，要求main方法中能得到且只能得到该类的一个对象
+ Singleton.java
    ```
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
    ```
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
            ```
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
        ``` 
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
    ```
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

#### Dog和DogTest的实现

#### 构造块和静态代码块的考点

#### 权限修饰符和包的定义

#### final修饰类的方法和作用

#### final修饰成员变量的作用