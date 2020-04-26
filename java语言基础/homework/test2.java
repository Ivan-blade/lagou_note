package java语言基础.homework;

// 编程找出 1000 以内的所有完数并打印出来。
// 所谓完数就是一个数恰好等于它的因子之和
// 如：6=1＋2＋3
// 获取一个数所有因子进行累加
// 判断累加和是否等于自身
import java.util.*;
public class test2 {

    public static void main(String[] args) {

        for(int i = 1; i < 1000; i++) {
            if(isSuit(i)) System.out.print(i+" ");
        }
    }

    // isSuit方法判断一个数是不是合数
    public static boolean isSuit (int val) {

        // 创建变量保存因数和，默认值为1，因为1和数字本身也是一对因数但是合数定义不能包括自身1，所以提前处理
        int sum = 1;
        // 根据合数定义，因子不能包括1本身所以如果数字是1，sum为0
        if(1 == val) sum = 0;

        // 使用for循环对该数字的所有因数进行累计
        for(int i = 2;i < Math.sqrt(val); i++) {

            // 如果val可以被一个数字整除，sum加上该因数以及对应的因素
            if(0 == val % i) {

                // 获取当前因数
                sum += i;

                // 获取对应因数
                sum += val / i;
            }
        }

        // 返回布尔值
        if(sum == val) return true;
        else return false;
    } 
}