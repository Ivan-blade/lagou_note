### 新特性

#### java8的概述和函数式接口的概念

+ 概述

  + java8是一个java语言的一个重要版本，该版本在2014年发布，是java5以来革命性的版本，这个版本包含语言，编译器，库，工具，jvm等新特性

+ 函数式接口

  + 函数式接口是指只包含一个抽象方法的接口：如java.lang.Runnable，java.util.Comparator接口等

  + java8提供@FunctionalInterface注解来定义函数式接口，若定义的接口不符合函数式的规范便会报错

  + java8中增加了java.util.function包，该包包含了常用的函数式接口，具体如下：

    | 接口名称      | 方法声明          | 功能介绍                     |
    | ------------- | ----------------- | ---------------------------- |
    | Consumer<T>   | void accept(T t)  | 根据指定的参数执行操作       |
    | Supplier<T>   | T get()           | 得到一个返回值               |
    | Function<T,R> | R apply(T t)      | 根据指定的参数执行操作并返回 |
    | Predicate<T>  | boolean test(T t) | 判断指定的参数是否满足条件   |

+ Lambda表达式

  +  Lambda表达式是实例化函数式接口的重要方式，使用Lambda表达式可以是代码变的更加简洁紧凑

  + Lambda表达式：参数列表，箭头符号 -> 和方法体组成，而方法体可以是表达式也可以是语句块
  + 语法格式：（参数列表） -> {方法体；} ——其中（），参数类型，{}，return关键字可以省略 

#### 匿名内部类实现函数式接口

+ demo

  ```java
  public class FunctionalInterface {
  
      public static void main(String[] args) {
  
          Runnable runnable = new Runnable() {
              @Override
              public void run() {
                  System.out.println("我是无参无返回值的方法");
              }
          };
          runnable.run();
  
          Consumer consumer = new Consumer() {
              @Override
              public void accept(Object o) {
                  System.out.println("有参无返回值");
              }
          };
          consumer.accept("???");
  
          Supplier supplier = new Supplier() {
              @Override
              public Object get() {
                  return "无参有返回值";
              }
          };
          System.out.println(supplier.get());
  
          Function function = new Function() {
              @Override
              public Object apply(Object o) {
                  return o;
              }
          };
          System.out.println(function.apply("有参有返回值"));
  
          Comparator comparator = new Comparator() {
              @Override
              public int compare(Object o1, Object o2) {
                  return 0;
              }
          };
          System.out.println(comparator.compare(10, 20));
      }
  }
  
  ```

  

#### lambda表达式实现函数式接口

+ demo

  ```java
  public class FunctionalInterface {
  
      public static void main(String[] args) {
  
          Runnable runnable = () -> System.out.println("无参无返回值");
          runnable.run();
  
          // 如果参数列表中参数只有一个时，小括号可以省略
          Consumer consumer = o -> System.out.println("有参无返回值");
          consumer.accept("???");
  
          // return可以省略
          Supplier supplier = () -> "无参有返回值";
          System.out.println(supplier.get());
  
          Function function = obj -> obj;
          System.out.println(function.apply("有参有返回值"));
  
          // 参数类型可以省略
          Comparator comparator = (o1, o2) -> 0;
          System.out.println(comparator.compare(10, 20));
      }
  }
  ```

  

#### 方法引用实现函数式接口的方式

+ 概述

  + 方法引用主要通过方法的名字来指向一个方法而不需要为方法引用提供方法体，该方法的调用交给函数式接口执行
  + 方法引用使用一对冒号 ：：将类名或对象与方法名进行连接，通常使用方式如下
    + 对象的非静态方法引用 ObjectName :: MethodName
    + 类的静态方法引用 ClassName :: StaticMethodName
    + 类的非静态引用 ClassName :: MethodName
    + 构造器的引用 ClassName :: new
    + 数组的引用 TypeName[] :: new

+ demo

  ```java
  public class MethodReferenceTest {
  
      public static void main(String[] args) {
  
          Person person = new Person("zhangfei",30);
  
          // 匿名内部类
          /*Runnable runnable = new Runnable() {
              @Override
              public void run() {
                  person.show();
              }
          };*/
          // lambda表达式
          /*Runnable runnable = () -> person.show();*/
  
  
          // 使用方法引用的方式调用person对象中的show方法
          Runnable runnable = person::show;
          runnable.run();
  
          // 使用匿名内部类的方式通过函数式接口Consumer中的方法来实现Person类中setName()方法的调用
          /*Consumer<String> consumer = new Consumer<String>() {
              @Override
              public void accept(String s) {
                  person.setName(s);
              }
          };
          consumer.accept("luna");*/
  
          // lambda表达式
          /*Consumer<String> consumer = s -> person.setName(s);
          consumer.accept("luna");*/
  
          // consumer方法引用
          Consumer<String> consumer = person::setName;
          consumer.accept("luna");
  
          // 通过supplier调用getName()方法
          Supplier<String> supplier = person::getName;
          supplier.get();
  
          // function调用Integer.parseInt()
          Function<String,Integer> function = Integer::parseInt;
          function.apply("1234");
  
          // comparator调用Integer.compara()
          Comparator<Integer> comparator = Integer::compare;
          comparator.compare(10,11);
  
          // 通过类名调用非静态方法
          // 其中一个参数对象作为调用对象来调用方法时，可以采用上述方式
          // 匿名内部类写法
          /*Comparator<Integer> comparator1 = new Comparator<Integer>() {
              @Override
              public int compare(Integer o1, Integer o2) {
                  return o1.compareTo(o2);
              }
          };*/
          // 方法调用写法，因为这边是一个对象作为参数另一个对象调用方法所以跨域使用类名::方法
          Comparator<Integer> comparator1 = Integer::compare;
          comparator1.compare(12,13);
  
          // 匿名内部类的方式通过supplier函数式接口创建Person类型的对象并返回
          /*Supplier<Person> supplier1 = new Supplier<Person>() {
              @Override
              public Person get() {
                  return new Person();
              }
          };
          System.out.println(supplier1.get());*/
  
          // lambda表达式创建对象
          /*Supplier<Person> supplier1 = () -> new Person();*/
  
          // 方法调用创建对象
          /*Supplier<Person> supplier1 = Person::new;
          System.out.println(supplier1.get());*/
  
          // 匿名内部类采用有参方式创建对象
          /*BiFunction<String,Integer,Person> biFunction = new BiFunction<String, Integer, Person>() {
              @Override
              public Person apply(String s, Integer integer) {
                  return new Person(s,integer);
              }
          };
          biFunction.apply("luna",16);*/
  
          // lambda表达式
          /*BiFunction<String,Integer,Person> biFunction = (s,i) -> new Person(s,i);
          System.out.println(biFunction.apply("luna",16));*/
  
          // 方法调用创建对象
          /*BiFunction<String,Integer,Person> biFunction = Person::new;
          System.out.println(biFunction.apply("luna",16));*/
  
          // 使用匿名内部类的方式通过Function函数式接口创建指定数量的的Person类型的对象数组并返回
          /*Function<Integer,Person[]> function1 = new Function<Integer, Person[]>() {
              @Override
              public Person[] apply(Integer integer) {
                  return new Person[integer];
              }
          };
          Person[] apply = function1.apply(3);
          System.out.println(Arrays.toString(apply));*/
  
          // lambda表达式
          /*Function<Integer,Person[]> function1 = i -> new Person[i];
           * function1.apply(5);*/
  
          // 方法调用
          /*Function<Integer,Person[]> function1 = Person[]::new;
          function1.apply(5);*/
      }
  }
  
  ```

  

#### 方法引用实现函数式接口的方式二

#### 方法引用实现函数式接口的方式三

#### 方法引用实现函数式接口的方式四

#### 方法引用实现函数式接口的方式五

#### 方法引用实现函数式接口的方式六

+ 如demo所示

#### 使用List集合实现元素的过滤

+ 案例

  + 准备一个List集合并放入Person类型的对象，将集合中所有成年人过滤出来放到另一个集合中并打印出来

+ demo

  ```java
  public class ListPersonTest {
  
      public static void main(String[] args) {
  
          // 1.准备一个List集合放入Person对象并打印
          List<Person> list = new ArrayList<>();
          list.add(new Person("luna",16));
          list.add(new Person("sakura",16));
          list.add(new Person("ivan",22));
          list.add(new Person("blade",22));
          // 2.将List集合中所有的成年人过滤出来放入另一个集合中并打印
          List<Person> list1 = new ArrayList<>();
          for(Person p : list) {
              if(p.getAge() > 18) list1.add(p);
          }
          System.out.println(list1);
      }
  }
  ```


#### Stream流实现集合元素的过滤和打印
+ 基本概念

  + java.util.stream.Stream接口是对集合功能的增强，可以对集合元素进行复杂的查找，过滤，筛选操作
  + Stream接口借助lambda表达式极大的提高编程效率和程序可读性，同时它提供串行和并行两种模式进行汇聚操作，并发模式能够充分利用多核处理器的优势

+ 使用步骤

  + 创建Stream，通过一个数据源获取一个流
  + 转换Stream，每次转换返回一个新的Stream对象
  + 对Stream进行聚合操作并产生结果

+ 创建方式

  + 方式一：通过调用集合的默认方法来获取流，如default Stream<E> stream()
  + 方式二：通过数组工具类中的静态方法获取流，如static IntStream stream(int[] array)
  + 方式三：通过Stream接口的静态方法获取流，如static<T> Stream<T>  of(T... values)
  + 方式四：通过Stream接口的静态方法来获取流，static<T> Stream<T> generate(Supplier<? extends T>s)
  
+ 中间操作

  | 方法声明                                         | 功能介绍                   |
  | ------------------------------------------------ | -------------------------- |
  | Stream<T> filter(Preducate<? super T> predicate) | 返回一个包含匹配元素的流   |
  | Stream<T> disctinct()                            | 返回不包含重复元素的流     |
  | Stream<T> limit(long maxSize)                    | 返回不超过给定元素数量的流 |
  | Stream<T> skip(long n)                           | 返回丢弃前n个元素后的流    |

+ 常用方法

  <img src="../../images/List-Stream.png">

+ demo

  ```java
  public class ListPersonTest {
  
      public static void main(String[] args) {
  
          // 1.准备一个List集合放入Person对象并打印
          List<Person> list = new ArrayList<>();
          list.add(new Person("luna",16));
          list.add(new Person("sakura",16));
          list.add(new Person("ivan",22));
          list.add(new Person("blade",22));
          // 2.将List集合中所有的成年人过滤出来放入另一个集合中并打印
          /*List<Person> list1 = new ArrayList<>();
          for(Person p : list) {
              if(p.getAge() > 18) list1.add(p);
          }*/
          /*System.out.println(list1);*/
  
          // 使用Stream实现上述功能
          list.stream().filter(new Predicate<Person>() {
              @Override
              public boolean test(Person person) {
                  return person.getAge() >= 18;
              }
          }).forEach(new Consumer<Person>() {
              @Override
              public void accept(Person person) {
                  System.out.println(person);
              }
          });
          // lambda表达式
           list.stream().filter(person -> person.getAge() >= 18).forEach((person) -> System.out.println(person));
          // 方法调用
          list.stream().filter(person -> person.getAge() >= 18).forEach(System.out::println);
      }
  }
  
  ```

#### Stream流实现集合元素的切片和映射

+ 

#### Stream流实现集合元素的排序

#### Stream流实现集合元素的匹配和查找

#### Stream流实现集合元素的规约和收集

#### Optional类的概念和使用

#### 模块化的概念和使用

#### 钻石操作符的使用升级

#### 集合工厂方法的使用

#### InputStream类的增强

#### 局部变量类型的判断

#### 简化的编译运行和String类中的新增方法



