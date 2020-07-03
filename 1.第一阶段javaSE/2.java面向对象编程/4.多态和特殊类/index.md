### 多态和特殊类
#### 多态的概念和语法格式
+ 多态的概念
    
    + 同一种事物的多种形态
+ 多态的语法格式
    ```java
        父类类型 引用变量名 = new 子类类型();

        Shape s = new Rect();
        s.show();
    ```
+ 案例（详细见task09）
    + 编程实现Shape类的封装，特征：横纵坐标，要求提供打印所有特征的方法
        ```java
            public class Shape {

                private int x;

                private int y;

                public Shape() {
                }

                public Shape(int x, int y) {
                    setX(x);
                    setY(y);
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public void show() {
                    System.out.println("x = "+ x + " y = " + y);
                }
            }

        ```
    + 编程实现Rect类的封装并继承自Shape类，特征有：长度和宽度
        ```java
            public class Rect extends Shape{

                int length;

                int width;

                public Rect() {
                }

                public Rect(int x, int y, int length, int width) {
                    super(x, y);
                    this.length = length;
                    this.width = width;
                }

                @Override
                public void show() {
                    super.show();
                    System.out.println("length: "+ length +" width: "+ width);
                }

                public int getLength() {
                    return length;
                }

                public void setLength(int length) {
                    this.length = length;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }
            }

        ```
    + 编程实现ShapeRectTest类，在main方法中分别创建Shape和Rect类型对象并打印特征
        ```java
            public class TestMain {

                public static void main(String[] args) {

                    // 声明shape类型引用指向shape类型的对象
                    // rect类中重写了show方法，调用shape类中方法
                    Shape shape = new Shape(1,2);
                    shape.show();

                    // 声明rect类型引用指向rect类型的对象
                    Rect rect = new Rect(1,2,10,20);
                    // 当Rect类中没有重写show方法时，调用shape类中的show方法
                    // rect类中重写了show方法，调用rect类中方法
                    rect.show();

                    // 声明shape类型引用指向rect类型的对象
                    Shape re = new Rect(1,2,10,20);
                    // 当Rect类中没有重写show方法时，调用shape类中的show方法
                    // rect类中重写了show方法，调用rect类中方法
                    // show方法在编译阶段会调用Shape父类方法，运行阶段调用的是Rect类中的方法
                    // 所以注释掉Shape类中的show方法，re.show()会报错但是rect.show()不会报错
                    re.show();
                }
            }

        ```
#### 多态特点
+ 当父类类型的引用指向子类类型的对象时
    + 父类类型的引用可以直接调用父类独有的方法
    + 父类类型的引用不能直接调用子类独有的方法
    + 对于父子类都有的非静态方法来说，编译阶段调用父类，运行阶段调用子类方法
    + 对于父子类都有的静态方法来说，两个阶段都调用父类
#### 引用数据类型之间的转换方式
+ 针对以上特点
    + 父类强制使用子类方法的方式
        + 将父类对象强制转换为子类对象
            ```
                ((Rect) re).getLength();
            ```
+ 引用数据类型转换方式
    + 自动转和强制转换
    + 自动转小转大（子类转父类），强转大转小（父类转子类，也叫显式类型转换）
    + 引用数据类型之间的转换必须发生在父子之间否则会报错
    + 如果强制转换的目标类型并不是该引用真正指向的数据类型时则编译通过，运行阶段发生类型转换异常
        ```java
            Circle c = (Circle) re; 
            // 会报错，因为re本身指向rect方向
        ```
    + 为了避免上述错误的发生，应该在强转之前进行判断，格式如下
        ```java
            if(引用变量 instanceof 数据类型)
            // 判断引用变量指向的对象是否为后面的数据类型
        ```
#### 多态的实际意义
+ 通过参数传递形成多态
    ```java
    public class TestBack {

        // 自定义成员成员方法，打印矩形特征
        public static void draw(Rect r) {
            r.show();
        }
        // 自定义成员方法打印圆形特征
        public static void draw(Circle c) {
            c.show();
        }
        public static void draw(Shape s) {
            s.show();
        }
        // 自定义成员方法打印矩形或圆形特征
        public static void main(String[] args) {
            draw(new Circle(1,2,3));
        }
    }

    ```
+ 直接在方法中使用抽象类的引用指向子类类型的对象
    ```java
        Abstract ab = new SubAbstract(); // SubAbstract类继承了Abstract类
        sb.show();
        // 此处调用的show方法是在SubAbstract中实现的
    ```
+ 接口类型的引用指向实现类型的对象形成多态
#### 抽象方法和抽象类的概念
+ 概念
    + 抽象方法主要指不能具体实现的方法并且使用abstract关键字修饰，也就是没有方法体
    + 抽象类主要指不能具体实现的类并且使用abstract关键字修饰，也就是不能创建对象
+ 注意
    
    + 抽象类不能new对象，因为不让开发者调用方法体，因为方法体未被定义，调用没有意义
+ 结构
    ```java
        访问权限 abstract 返回值类型 方法名(形参列表)
    ```
+ 抽象类和抽象方法的联系
    + 抽象类可以有成员变量，构造方法和成员方法
        + 抽象类中的构造方法虽然自己不能调用但是可以让继承类调用
    + 抽象类中可以没有抽象方法，也可以有抽象方法
    + 拥有抽象方法的类必须是抽象类，因此真正意义上的抽象类应该是具有抽象方法并且使用abstract关键字修饰的类
#### 抽象类的实际意义
+ 抽象类的实际意义不在于创建对象，而在于被继承
    + 在继承抽象类时必须实现抽象类中的方法，否则就自声明为抽象类
#### 开发经验
+ 在开发中推荐使用多态，此时父类类型引用直接调用的所有方法一定是父类中拥有的方法，若以后更换子类时，只需要将new关键字后面的子类类型修改而其他地方无序改变立刻生效从而提高了代码的可维护性和可扩展性
#### 抽象类应用
+ 案例

#### 考点
+ 抽象类是否能被final修饰
    + 不能，final修饰的类是不能被继承的
+ 抽象类中的抽象方法能否被private修饰
    + 不能，因为抽象方法本身需要在继承类中重写，private关键字只允许类中调用，抽象方法类中没有意义
+ 抽象类中的抽象方法能否被static修饰
    + 不能，抽象类不能new对象的原因就是防止调用抽象方法，static修饰之后用户可以直接通过类名调用方法违背初衷
+ 抽象类中的抽象方法能否被final修饰
    + 不能，因为被final修饰的方法可以被继承不能被重写，抽象方法必须被重写才有意义
#### 接口概念
+ 概念
    + 比抽象类还抽象的类，体现在
        + 接口中只能有常量
        + 所有方法都为抽象方法（jdk1.9开始允许接口中出现私有方法）
    + 定义类的关键字是class，定义接口的关键字是interface
    + 继承接口使用implements关键字
    + 接口中的方法默认是public abstract修饰，不用额外添加访问控制字符串(以下代码省略这部分内容，不过建议实际开发时加上增加可读性)
#### 接口意义
+ 一个类可以继承多个接口
    ```java
        public class Gold implements Metal, Money{
            ...
        }
    ```
#### 类和接口的关系
+ 案例
    + 编程实现runner接口，提供一个描述奔跑的抽象方法
        ``` java
            public interface Runner {

                void run();
            }
        ```
    + 编程实现hunter接口继承runner接口，提供一个描述捕猎行为的抽象方法
        ```java
            public interface Hunter extends Runner{

                void hunt();
            }
        ```
    + 编程实现man类实现hunter接口重写抽象方法，在main方法中使用多态方式测试
        ```java
            public class Man implements Hunter{

                @Override
                public void hunt() {
                    System.out.println("hunting");
                }

                @Override
                public void run() {
                    System.out.println("running!");
                }

                public static void main(String[] args) {
                    Runner man = new Man();
                    ((Man) man).hunt();
                    man.run();
                }
            }
        ```
+ 联系
    + 类和类之间extends继承关系支持单继承
    + 类和接口之间implements表实现支持多实现
    + 接口和接口之间使用extends关键字表示继承支持多继承
#### 抽象类和接口的主要区别
+ 主要区别
    + 定义抽象类的关键字是abstract class定义接口的关键字是interface
    + 继承抽象类的关键字是extends，实现接口类的关键字是implements
    + 继承抽象类支持单继承，而实现接口支持多实现
    + 抽象类中可以有构造方法，但是接口中没有构造方法
    + 抽象类中可以有成员变量但是接口中只有常量
    + 抽象类中可以有成员方法但是在接口只能有抽象方法
    + 抽象类中增加方法时子类不需要重写但是接口中增加方法时实现类需要重写（java8以前的版本）
    + java8新特性，接口中允许出现非抽象方法和静态方法，但是非抽象方法需要使用default关键字修饰
        ```java
            public interface Hunter extends Runner{

                void hunt();

                default void show(){}
                
                static void test(){}
            }
        ```
    + java9新特性，允许出现私有方法（主要用于减少接口内冗余代码）