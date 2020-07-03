+ 什么是匿名内部类
  
+ 匿名内部类 ：是内部类的简化写法。它的本质是一个 带具体实现的父类或者父接口的匿名的子类对象。
  
+ 什么是多态
  
+ 同一种事物的多种形态，父类型的引用指向子类型的对象
  
+ 多态的意义
  + 可替换性（substitutability）。多态对已存在代码具有可替换性。例如，多态对圆Circle类工作，对其他任何圆形几何体，如圆环也同样工作。
  + 可扩充性（extensibility）。多态对代码具有可扩充性。增加新的子类不影响已存在类的多态性、继承性，以及其他特性的运行和操作。实际上新加子类更容易获得多态功能。例如，在实现了圆锥、半圆锥以及半球体的多态基础上，很容易增添球体类的多态性。
  + 接口性（interface-ability）。多态是超类通过方法签名，向子类提供了一个共同接口，由子类来完善或者覆盖它而实现的。
  + 灵活性（flexibility）。它在应用中体现了灵活多样的操作，提高了使用效率。
  + 简化性（simplicity）。多态简化对应用软件的代码编写和修改过程，尤其在处理大量对象的运算和操作时，这个特点尤为突出和重要。

+ 形成多态的条件？形成多态的方式有哪些？

  + 条件

    + 要有继承
    + 父类对象引用子类对象
    +  要有方法的重写

  + 方式

    + 通过参数传递形成多态

      ```java
        public static void draw(Father fa) {
            fa.show();
        }
        draw(new Son1(1,2));
        draw(new Son2(3));
      ```

    + 通过返回类型形成多态

      ```java
      public Father test(int num) {
        if(num == 1) return new Son1();
      else if(num == 2) return new Son2();
        else return new Son3();
    }
      ```
    
    + 在方法体中直接使用多态的语法格式：父类类型 引用变量名 = new 子类类型();
    
      ```java
      Father fa = new Son1();
      Father fa = new Son2();
    ```
      
      

+ 抽象类关键词修饰

  + 抽象类是否能被final修饰

    + 不能，final修饰的类是不能被继承的，而抽象类的意义就在于被继承
  + 抽象类中的抽象方法能否被private修饰

    + 不能，因为抽象方法本身需要在继承类中重写，private关键字只允许类中调用，抽象方法类中没有意义
  + 抽象类中的抽象方法能否被static修饰

    + 不能，抽象类不能new对象的原因就是防止调用抽象方法（因为抽象方法没有具体实现），static修饰之后用户可以直接通过类名调用方法违背初衷
  + 抽象类中的抽象方法能否被final修饰

    + 不能，因为被final修饰的方法可以被继承不能被重写，抽象方法必须被重写才有意义