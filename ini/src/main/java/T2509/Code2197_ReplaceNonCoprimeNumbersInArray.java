package T2509;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 栈
 * @Author: iniwym
 * @Date: 2025-09-16
 * @Link: https://leetcode.cn/problems/replace-non-coprime-numbers-in-array/
 */
public class Code2197_ReplaceNonCoprimeNumbersInArray {

    /**
     * 将数组中相邻的非互质数替换为它们的最小公倍数
     * <p>
     * 该函数遍历输入数组，使用栈式结构处理元素。当遇到相邻的非互质数时，
     * 将它们替换为最小公倍数，并继续检查新生成的数与栈中其他元素的关系。
     *
     * @param nums 输入的整数数组
     * @return 处理后的整数列表，其中相邻元素都是互质的
     */
    public List<Integer> replaceNonCoprimes(int[] nums) {
        List<Integer> st = new ArrayList<>();
        for (int x : nums) {
            // 当栈不为空且当前元素与栈顶元素非互质时，合并它们
            while (!st.isEmpty() && gcd(x, st.get(st.size() - 1)) > 1) {
                x = lcm(x, st.remove(st.size() - 1));
            }
            st.add(x);
        }
        return st;
    }


    /**
     * 计算两个整数的最大公约数
     * 使用欧几里得算法（辗转相除法）来计算最大公约数
     *
     * @param a 第一个整数
     * @param b 第二个整数
     * @return 两个数的最大公约数
     */
    private int gcd(int a, int b) {
        // 使用欧几里得算法计算最大公约数
        while (a != 0) {
            int tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }


    /**
     * 计算两个整数的最小公倍数
     * 通过先除后乘的方式避免计算过程中的整数溢出问题
     *
     * @param a 第一个整数
     * @param b 第二个整数
     * @return 两个整数的最小公倍数
     */
    private int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }

}
