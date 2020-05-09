### 集合类库
#### 集合概述
+ 集合由来
    + 需要在java中记录单个数据内容，则声明一个变量
    + 需要在java中记录多个类型相同的数据内容时，声明一个一维数组
    + 需要在java中记录多个类型不同的数据内容时，创建一个对象
    + 需要在java中记录多个类型相同的对象数据时，创建一个对象数组
    + 需要在java中记录多个类型不同的对象数组时，创建一个集合
+ 集合框架结构
    + java中集合框架顶层框架层是：java.util.Collection集合和java.util.Map集合
    + 其中Collection集合存取元素的基本单位是：单个元素
    + 其中Map集合中存取元素的基本单位是：单对元素
<img src="../../images/Collection-structure.png">

#### Collection集合的准备和元素添加
+ 基本概念
    + java.util.Collection接口是List接口，Queue接口，Set接口的父接口，因此该接口里定义的方式既可以用于操作List集合，也可以用于操作Queue和Set集合
<img src="../../images/Collection-methods.png">
+ 示例
    ```
        // Collection c1 = new Collection(); collection是接口不能创建实例
        Collection c1 = new ArrayList();
        c1.add(new String("123"));
        c1.add(Integer.valueOf("4"));
        c1.add('a');
        System.out.println(c1); // 打印集合就是自动调用集合中每个元素中的toString方法

        // 向集合中添加集合
        Collection c2 = new ArrayList();
        c2.add("567");
        c2.add(9);
        c1.addAll(c2);  // 将c2集合中的元素分别加入c1 [123, 4, a, 567, 9]
        c1.add(c2);     // 将c2集合当做一个整体加入c1 [123, 4, a, 567, 9, [567, 9]]
        System.out.println(c1);
    ```

#### Collection集合判断单个元素是否存在
+ contains()
    + 该方法的本质是调用了元素的equals方法
+ 示例
    ```
        System.out.println(c2.contains(new String("567"))); // true 这边没有问题是因为String类的equals被重写了，只比较内容
    ```
<img src="../../images/Collection-contains.png">

#### Collection集合判断所有元素是否存在
+ 示例
    ```
        System.out.println(c1.containsAll(c2)); // true
    ```
#### Collection集合实现交集的计算
+ 示例
    ```
        Collection c3 = new ArrayList();
        c3.add(0);
        c3.add(1);
        Collection c4 = new ArrayList();
        c4.add(1);
        c4.add(2);
        c3.retainAll(c3);       // false
        c3.retainAll(c4);       // true 
        System.out.println(c3); // [1]
    ```
#### Collection集合实现元素删除
+ remove(Object obj)
+ removeAll(Collection c)
    + 该方法也是调用了元素的equals方法
        ```
            boolean b1 = c1.remove(c2); // true [123, 4, a, 9, 567, 9]
            boolean b2 = c1.removeAll(c2); // true 将c2的元素从c1中全部删除，有则删无则跳，只要删除过一个元素就返回true
            System.out.println(c1); // [123, 4, a]
        ```
#### Collection集合实现其他方法的测试
+ 示例
    ```
        c1.clear();
        System.out.println(c1.size());
        System.out.println(c1.isEmpty());

        Collection c5 = new ArrayList();
        c5.add(2);
        c5.add(1);
        Collection c6 = new ArrayList();
        c6.add(1);
        c6.add(2);
        System.out.println(c5.equals(c6));
    ```
#### Collection集合和数组的转换方式
+ 示例
    ```
        // 集合到数组
        Object[] objects = c5.toArray();
        System.out.println(Arrays.toString(objects));
        // 数组到集合
        Collection objects1 = Arrays.asList(objects);
        System.out.println(objects1);
    ```
#### Collection集合实现迭代器的使用
+ Iterator接口
    + 概念  
        + java.util.Iterator接口主要是描述迭代器对象，可以遍历Collection集合中所有的对象
        + java.util.Collection接口继承Iterator接口，因此所有实现Collection的类都可以使用迭代器对象
    + 常用方法
        + boolean hasNext()
            + 判断集合中是否有可以迭代/访问的元素
        + E next()
            + 用于取出一个元素并指向下一个元素
        + void remove()
            + 用于删除访问到的最后一个元素
#### Collection集合使用迭代器模拟toString()
+ 示例
    ```
        Collection c1 = new ArrayList();

        c1.add("one");
        c1.add(2);

        // 使用迭代器遍历集合元素
        Iterator iterator = c1.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // 重置迭代器指向
        iterator = c1.iterator();
        StringBuilder sb = new StringBuilder("[");
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            if(!iterator.hasNext()) sb.append(obj).append("]");
            else sb.append(obj).append(", ");
        }
        System.out.println(sb);
    ```
#### Collection集合迭代的过程中删除元素
+ remove()
    + 删除最近访问的元素
        ```
            iterator = c1.iterator();

            while (iterator.hasNext()) {
                Object obj = iterator.next();
                if("one".equals(obj)) iterator.remove();
            }
            System.out.println(c1);
        ```
#### Collection集合中使用foreach结构
+ 集合遍历方式
    + 直接println会调用toString
    + 迭代器
    + foreach循环（java5开始）
+ 基本概念
    + java5开始推行的增强型for循环
+ 语法结构
    ``` 
        for(元素类型 变量名 ： 数组/集合名称) {
            循环体；
        }
    ```
+ 示例
    ```
        int[] arr = new int[] {1,2,3,4};

        for(int i : arr) {
            System.out.println(i);
        }
    ```
#### List集合概念和ArrayList类的源码解析
+ 基本概念
    + java.util.List集合是Collection的子集合，该集合允许有重复的元素并且有放入的先后次序
    + 该集合主要的实现类有ArrayList,LinkedList,Stack,Vector类
    + 其中ArrayList类的底层是采用动态数组进行数据管理的，支持下标访问，增删元素不方便
    + 其中LinkedList底层是双向俩表进行数据管理，访问不方便，增删元素方便
    + 可以认为ArrayList和LinkedList的方法逻辑上完全一样，只是性能上有一定差别，ArrayList更适合访问，LinkedList更适合插入和删除，在性能要求不是特别苛刻的情形下可以忽略这个差别
    + 其中Stack类的底层是采用动态数组进行数据管理的，该类主要用于描述一种具有后进先出特征的数据结构，叫做栈
    + 其中Vector类的底层是采用动态数组进行数据管理，该类与ArrayList类相比属于线程安全类，效率更低（处理安全基本和ArrayList类似，Vector每次扩容两倍，ArrayList扩容1.5倍）
#### LinkedList类的概念和源码解析
+ 
#### Stack类和Vector类
#### List集合中增加和查找方法的使用
#### List集合中修改和删除以及子集合获取的方式
#### Stack类的编程使用
#### Queue集合的概念和使用
#### 泛型机制的基本概念
#### 泛型机制的基本使用
#### 泛型机制的底层原理
#### 自定义泛型类的实现和使用
#### 泛型类被继承时的处理方式
#### 泛型方法的定义和使用
#### 泛型通配符的使用和特点
#### Set集合的基本概念
#### HashSet集合的基本使用
#### HashSet集合放入元素的过程
#### TreeSet集合的概念
#### TreeSet集合放入String对象的实现
#### TreeSet集合中实现自然排序
#### TreeSet集合中实现比较器排序
#### Map集合的概念
#### Map集合实现元素的增加和修改
#### 元素放入HashMap集合的过程
#### Map集合实现元素的查找和删除
#### Map集合的三种遍历方式
#### Collections类的编程和使用