package T2511;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description: 贪心策略
 * @Author: iniwym
 * @Date: 2025-11-23
 * @Link: https://leetcode.cn/problems/greatest-sum-divisible-by-three/
 */
public class Code1262_GreatestSumDivisibleByThree {

    /**
     * 计算数组中选出若干个数使其和最大且能被3整除
     *
     * @param nums 输入的整数数组
     * @return 最大和且能被3整除，如果不存在则返回0
     */
    public int maxSumDivThree(int[] nums) {
        // 计算数组所有元素的总和
        int s = 0;
        for (int x : nums)
            s += x;
        // 如果总和已经能被3整除，直接返回
        if (s % 3 == 0)
            return s;

        // 将数组元素按照除以3的余数分类存储
        List<Integer> a1 = new ArrayList<Integer>();
        List<Integer> a2 = new ArrayList<Integer>();
        for (int x : nums) {
            if (x % 3 == 1) a1.add(x);
            else if (x % 3 == 2) a2.add(x);
        }
        // 对两个列表进行升序排序，便于后续取最小值
        Collections.sort(a1);
        Collections.sort(a2);

        // 如果余数为2，交换两个列表，统一处理逻辑
        if (s % 3 == 2) { // swap(a1,a2)
            List<Integer> tmp = a1;
            a1 = a2;
            a2 = tmp;
        }

        // 计算最大和：要么减去余数为1的最小元素，要么减去余数为2的两个最小元素
        int ans = a1.isEmpty() ? 0 : s - a1.get(0);
        if (a2.size() > 1)
            ans = Math.max(ans, s - a2.get(0) - a2.get(1));
        return ans;
    }

}
