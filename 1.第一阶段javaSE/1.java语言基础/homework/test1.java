package java语言基础.homework;

// 提示用户输入年月日信息
// 判断这一天是这一年中的第几天并打印
// 判断闰年还是平年判断二月天数 年份是4的倍数且不是100的倍数
// ...直接记录十二个月份的天数，累加求和
import java.util.*;
public class test1 {

    public static void main(String[] args) {

        // 初始化每个月份天数
        int[] arr = new int[] {31,0,31,30,31,30,31,31,30,31,30,31};

        Scanner input = new Scanner(System.in);

        // 提示输入年月日信息
        System.out.println("please input year: ");
        int year = input.nextInt();
        System.out.println("please input month: ");
        int month = input.nextInt();
        System.out.println("please input day: ");
        int day = input.nextInt();

        // 判断闰年还是平年确定二月天数
        if(year % 4 == 0 && year % 100 != 0) arr[1] = 29;
        else arr[1] = 28;

        // sum值保存第几天，初始化值为输入的天数
        int sum = day;

        // 对输入月份前几个月所有天数做累加
        for(int i = 0; i < month - 1; i++) {
            sum += arr[i];
        }
        System.out.println(year+"-"+month+"-"+day+"是今年第"+sum+"天");
    }
}