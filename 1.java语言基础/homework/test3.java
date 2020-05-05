package java语言基础.homework;

// 实现双色球抽奖游戏中奖号码的生成
// 中奖号码由 6 个红球号码和 1 个蓝球号码组成。 
// 其中红球号码要求随机生成 6 个 1~33 之间不重复的随机号码。 
// 其中蓝球号码要求随机生成 1 个 1~16 之间的随机号码。
import java.util.*;
public class test3 {

    public static void main(String[] args) {

        // 创建随机变量对象
        Random rand = new Random();

        // 创建篮球变量并赋随机值
        int blue = rand.nextInt(15)+1;

        // 创建红球数组
        int[] red = new int[6];
        int len = red.length;

        // 使用while循环对红球数组进行赋值,num为已赋值元素数量
        // 使用binarySearch判断数组中是否已有该值，如果没有直接赋值，num自增，如果有进入下一轮循环
        int num = 0;
        while(num < len) {

            // 获取随机值，并使用index保存二叉搜索结果
            int temp = rand.nextInt(32)+1;
            int index = Arrays.binarySearch(red,temp);

            // 如果搜索失败，即该随机值还未存在，进行赋值
            // 二叉搜索失败返回的索引是第一个比它大的数的索引的负数，虽然索引不确定但是必定是负的
            if(index < 0) red[num++] = temp;
        }

        System.out.println(blue);
        System.out.println(Arrays.toString(red));
    }

}