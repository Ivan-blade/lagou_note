package temp;


public class Singleton{

    // 使用static创建共有对象，用于返回需要的一个对象，private让用户不能将该对象赋值为null从而清空
    private static Singleton singleton = new Singleton();

    // 私有化构造方法，使外部不能随便创建对象
    private Singleton () {};

    public static Singleton getInstance () {
        return singleton;
    }
}