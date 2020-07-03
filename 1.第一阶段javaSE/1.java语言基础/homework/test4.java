package java语言基础.homework;


// 自定义数组扩容规则
// 当已存储元素数量达到总容量的 80%时，扩容 1.5 倍。 
// 例如，总容量是 10，当输入第 8 个元素时，数组进行扩容，容量从 10 变 15
import java.util.*;
public class test4 {

    public static void main(String[] args) {

        // 初始化数组
        int[] arr = new int[] {0,1,2,3,4,5,6,7,0,0};
        // 输出操作后的数组
        System.out.println(Arrays.toString(expand(arr)));
    }

    // 定义扩容操作
    public static int[] expand(int[] arr) {

        // 定义返回数组，默认为指向原数组
        int[] res = arr;

        int len = arr.length;

        // 当达到扩容要求时
        // 申请原长度1.5倍的空间
        // 更改res保存的内存地址
        // 将原数组内容复制新的内存空间中
        // 输出扩容完成信息
        if(isNeed(arr)) {
            int newlen = (int)(1.5 * len);
            res = new int[newlen];
            System.arraycopy(arr, 0, res, 0, len);
            System.out.println("扩容完成！");
        }
        
        return res;
    }

    // 判断是否达到扩容要求
    // 获取已存储元素数量，int类型数组元素默认元素为0，若不为0则认为是一个存储元素
    // 如果存储数到达申请内存的80%则认为需要扩容，否则提示不需要扩容
    public static boolean isNeed(int[] arr) {

        int len = arr.length;
        int num = 0;
        for(int i = 0;i < len;i++) {
            if(arr[i] != 0) num++;
        }

        double flag = (double)num / len;

        if(flag < 0.8) System.out.println("暂不需要扩容");
        return flag < 0.8 ? false : true;
    }
}