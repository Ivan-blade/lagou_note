package temp;

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