package temp;

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