package T2506;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 深度优先搜索
 * @Author: iniwym
 * @Date: 2025-06-08
 * @Link: https://leetcode.cn/problems/lexicographical-numbers/
 */
public class Code0386_LexicographicalNumbers {

    /**
     * 以字典序返回从1到n的整数列表
     *
     * @param n 整数列表的最大值
     * @return 按字典序排列的整数列表
     */
    public List<Integer> lexicalOrder(int n) {
        // 初始化整数列表，容量为n+1，考虑到从1开始计数，预留足够的空间
        List<Integer> nums = new ArrayList(n + 1);
        // 填充列表从1到n
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }

        // 按字典序排序整数列表
        // 使用String.valueOf将整数转换为字符串进行比较，以实现字典序排序
        nums.sort((a, b) -> String.valueOf(a).compareTo(String.valueOf(b)));

        // 返回排序后的整数列表
        return nums;
    }


    /**
     * 生成按字典序排序的整数列表
     * 该方法通过迭代生成从1到n的整数列表，按字典序排序
     * 例如，当n=13时，返回的列表应该是[1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]
     *
     * @param n 列表中最大的整数
     * @return 按字典序排序的整数列表
     */
    public List<Integer> lexicalOrder1(int n) {
        // 初始化结果列表
        List<Integer> ret = new ArrayList<Integer>();
        // 初始化当前数字为1，作为起始点
        int number = 1;
        // 循环n次，生成n个按字典序排序的数字
        for (int i = 0; i < n; i++) {
            // 将当前数字添加到结果列表中
            ret.add(number);
            // 如果当前数字乘以10仍然不超过n，则优先向子节点（即乘以10）移动
            if (number * 10 <= n) {
                number *= 10;
            } else {
                // 如果当前数字的个位为9，或者下一个数字超过n，则需要回溯到父节点
                while (number % 10 == 9 || number + 1 > n) {
                    number /= 10;
                }
                // 在完成必要的回溯后，移动到下一个兄弟节点
                number++;
            }
        }
        // 返回结果列表
        return ret;
    }


}
