### 运算符
#### 算术运算符的概念及使用
+ 算术运算符
    + 加法运算符+
    + 减法运算符-
    + 乘法运算符*
    + 除法运算符/
    + 取余运算符%
#### 算术运算符的注意事项
+ 注意事项
    + java语言中两个整数相除时，结果只保留整数部分，丢弃小数部分
        ```
            System.out.println( 5/2 ); // 2
        ```
        + 如果想要保留小数
            + 将其中一个数据强制转换为double
            ```
            System.out.println( (double)5/2 ); // 2 
            ```
            + 其中一个数*1.0完成类型转化
            ```
            System.out.println( 5*1.0 /2 ); // 2 
            ```
#### 算术运算符实现时间拆分
+ 输入秒数，转换为时分秒格式
    ```
        import java.util.*;

        public class test {

            public static void main(String[] args) {

                Scanner input = new Scanner(System.in);
                int data = input.nextInt();
                int hour = data / 3600;
                int min = data % 3600 / 60;
                int sec = data % 60;
                System.out.println(data+"秒是"+hour+"时"+min+"分"+sec+"秒"); 
            }
        }
    ```
#### 字符串连接符的概念和使用
+ 字符串连接符+可以实现字符串连接，同时实现字符串与其他数据类型相连
    + 从左向右遍历只要+两边操作数有一个是字符串类型，则该+就被当做字符串连接符处理，否则就作为加法运算符
    ```
        int hour = 1;
        int min = 1;
        int sec = 6;
        System.out.println(hour+min+sec); // 8
        System.out.println(hour+min+sec+""); // 8
        System.out.println(hour+min+""+sec); // 26
        System.out.println(hour+""+min+sec); // 116
        System.out.println(""+hour+min+sec); // 116
        System.out.println(""+(hour+min+sec)); // 8
    ```
#### 关系运算符的概念和使用
+ 类型
    + 是否大于运算符    >
    + 是否大于等于运算符    >=
    + 是否小于运算符    <
    + 是否小于等于运算符    <=
    + 是否等于运算符    ==
    + 是否不等于运算符  !=
    + 所有以关系运算符作为最终运算的表达式结果一定是boolean类型
#### 关系运算符实现复数判断
+ 题目
    + 输入一个整数，判断是否为负
        ```
            import java.util.*;

            public class test{
                public static void main(String[] args) {
                    Scanner input = new Scanner(System.in);

                    int num = input.nextInt();
                    System.out.println(num < 0);
                }
            }
        ```
#### 自增减运算符的概念和使用
+ 自增运算符++，让当前变量自身数值+1
+ 自减运算符--，让当前变量自身数值-1
#### 前后加加的区别
+ 后加加先让变量作为表达式的结果再将变量+1
+ 先加加先将变量+1后让变量作为表达式的结果
    ```
        int i = 1;
        System.out.println(i++); // 1
        System.out.println(i);   // 2
        System.out.println(++i); // 3
        System.out.println(i);   // 3
    ```
#### 自增减运算符的笔试考点
+ 逻辑与运算符&&
    + 同真为真，一假为假
+ 逻辑或运算符||
    + 一真为真，同假为假
+ 逻辑非运算符！
    + 真为假，假为真
#### 逻辑运算符的概念和使用
#### 逻辑运算符的短路特性
+ 当使用逻辑与运算符时，第一个条件为假,跳过第二个表达式不执行
+ 当使用逻辑或运算符时，第一个条件为真，跳过第二个表达式不执行
    ```
        int i = 3;
        int j = 5;
        boolean k = (++i == 3) && (++j == 5);
        System.out.println(k); // false 
        System.out.println(i); // 4
        System.out.println(j); // 5
    ```
#### 逻辑运算符判断三位数
+ 输入一个整数判断是否为三位数
    ```
        import java.util.*;
        public class test {
            
            public static void main(String[] args) {
                
                Scanner sc = new Scanner(System.in);

                int data = sc.nextInt();
                int res = Math.abs(data);
                System.out.println(res/100 > 0 && res/100 < 10);
            }   
        }
    ```
#### 三目运算符的概念和使用
+ 语法
    + 条件表达式 ? 表达式1 : 表达式2;
    + 条件为真执行表达式1否则执行表达式2
#### 三目运算符查找最大值
+ 输入两个整数输出最大值
    ```
        import java.util.*;
        public class test {
            
            public static void main(String[] args) {

                Scanner sc = new Scanner(System.in);
                System.out.println("please input first number:");
                int data1 = sc.nextInt();
                System.out.println("please input second number:");
                int data2 = sc.nextInt();
                System.out.println("the bigger number is : "+ (data1 >= data2 ? data1 : data2));
            }   
        }
    ```
#### 赋值运算符的概念和使用
+ 赋值运算符=，用于将=右边的数据赋值给=左边的变量
+ 赋值表达式本身也有值，其本身的值即为所赋的值
+ 复合表达式：+= , -= , *= , /= 等
    ```
        import java.util.*;
        public class test {
            
            public static void main(String[] args) {

                int a = 3;
                System.out.println( a = 5); // 表达式的值即赋给的值，所以输出5
                int b = a = 4;
                System.out.println(b); // 4
            }   
        }
    ```
#### 赋值运算符的考点
+ 对于byte类型加法的处理
    + byte + int = int
    + byte + byte = int
    + 原因： 两个byte相加可能超过byte能够表达的值，所以编译器做了优化
        ```
            byte b1 = 1;
            System.out.println(b1); // 1
            b1 = b1 + 2; // 错误不兼容类型，int转换为byte可能有损失—— byte + int = int
            b1 = b1 + (byte)2; // 错误不兼容类型，int转换为byte可能有损失 —— byte + byte = int
            // b1 = (byte)(b1 + 2); // b1 = 3;
            b1 += 2; // 与上一个等价,b1 = 3,由于等价，所以数值范围超过127会报错
        ```
+ 布尔表达式推荐格式
    ```
        i == 2; // 判断i是否等于2
        2 == i; // 判断2是否等于i(推荐该写法)
        -----------------------------------
        i = 2;  // 将2赋值给i
        2 = i;  // 编译报错
        -----------------------------------
        // 推荐下一个写法的原因是在漏写等号的情况下，第一种写法编译通过，但是会错误赋值，第二种写法直接报错
    ```
#### 移位运算符的概念
+ 左移运算符<<,用于将数据的二进制位向左移动，右边使用0补充（移动n位相当于*2^n）
+ 右移运算符>>,用于将数据的二进制为向右移动，左边符号位补充（移动n为相当于/2^n）
+ 逻辑右移运算符（无符号右移运算符）>>>,用于将数据的二进制为向右移动，左边使用0补充（几乎同右移）
#### 移位运算符的使用
```
    byte a = 3;
    a <<= 1;
    System.out.println(a); // 6
    // byte b = a << 1 // 会报错，需要强制转换
    // byte b = (byte)(a << 1); // 12
    a >>= 1;
    System.out.println(a); // 3
```
#### 位运算符的概念
+ 类型
    + 按位与& —— 按二进制位计算，同1为1，一0为0
    + 按位或| —— 按二进制位计算，一1为1，同0为0
    + 按位取反~ —— 按二进制位计算，1为0，0为1
    + 按位异或^ —— 按二进制位计算，同为0，不同为1
#### 位运算符的使用
```
    import java.util.*;
    public class test {
        
        public static void main(String[] args) {

            byte b1 = 11;
            byte b2 = 13;

            System.out.println(b1); // 11
            System.out.println(b2); // 13
            // b1 0000 1011
            // b2 0000 1101

            System.out.println(b1 & b2);
            // 0000 1001 9
            System.out.println(b1 | b2);
            // 0000 1111 15
            System.out.println(b1 ^ b2);
            // 0000 0110 6
            System.out.println(~b1);
            // 1111 0100
            // -1
            // 1111 0011
            // 取反
            // 0000 1100
            // -12
        }   
    }
```
#### 运算符的优先级
+ 顺序
    + 括号 > 一元运算符 > 乘除 > 加减 > 移位 > 大小 > 判断相等 > 位运算 > 等号
+ 建议
    + 无法确认优先级用括号装载即可
#### 总结