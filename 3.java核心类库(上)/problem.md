### java核心类库
+ 泛型通配符的特点（奇奇怪怪）
    
    + 添加元素的特点
+ 接口通过匿名内部类创建对象？
    ```
        Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    // o1表示新增的对象，o2表示已有的对象
                    return o2.getAge() - o1.getAge();
                }
        };
    ```
+ 匿名内部类可以用lamda表达式替换，替换机制？
    ```java
        Comparator<Student> comparator = (Student o1, Student o2) -> {
            return o2.getAge() - o1.getAge();
        }
    ```

+ TreeMap设置对key和value排序的比较器