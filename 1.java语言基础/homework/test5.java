package java语言基础.homework;


// 使用二维数组和循环实现五子棋游戏棋盘的绘制，具体如下
import java.util.*;
public class test5 {

    public static void main(String[] args) {

        // 创建特殊值数组
        char[] sp = new char[] {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

        // 创建一个17*17的二维数组
        char[][] arr = new char[17][17];
        int len =  arr.length;

        // 将特殊值赋值给第一行和第一列
        for(int i = 1; i < len; i++) {
            arr[i][0] = sp[i-1];
        }
        for(int i = 1; i < arr[0].length; i++) {
            arr[0][i] = sp[i-1];
        }

        // 将+赋值给数组其他元素
        for(int i = 1; i < len; i++) {
            for(int j = 1; j < arr[i].length; j++) {
                arr[i][j] = '+';
            }
        }
        // 遍历输出
        for(int i = 0;i < len;i++) {
            for(int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }

}