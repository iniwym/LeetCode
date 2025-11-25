package T2511;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 模运算
 * @Author: iniwym
 * @Date: 2025-11-25
 * @Link: https://leetcode.cn/problems/smallest-integer-divisible-by-k/
 */
public class Code1015_SmallestIntegerDivisibleByK {

    /**
     * 计算最小的由连续1组成的数字（如1, 11, 111...）能够被k整除的长度
     *
     * @param k 除数，必须为正整数
     * @return 能够被k整除的最短连续1数字的长度，如果不存在则返回-1
     */
    public int smallestRepunitDivByK(int k) {
        // 使用HashSet记录已经出现过的余数，用于检测循环
        Set<Integer> seen = new HashSet<Integer>();

        // 初始化余数为1%k，表示第一个数字1除以k的余数
        int x = 1 % k;

        // 当余数不为0且该余数未出现过时继续循环
        // seen.add(x)会返回false如果x已经存在，true如果x是新加入的
        while (x > 0 && seen.add(x)) {
            // 计算下一个连续1数字除以k的余数
            // 相当于在当前数字后面加一个1，即原数字*10+1
            x = (x * 10 + 1) % k;
        }

        // 如果余数大于0说明出现了循环但未找到能整除的情况，返回-1
        // 否则返回已见过的余数个数加1，即最短长度
        return x > 0 ? -1 : seen.size() + 1;
    }

}
