package T2508;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 贪心
 * @Author: iniwym
 * @Date: 2025-08-02
 * @Link: https://leetcode.cn/problems/rearranging-fruits/
 */
public class Code2561_RearrangingFruits {

    /**
     * 计算将两个数组调整为相同元素集合的最小代价
     * <p>
     * 给定两个数组basket1和basket2，每次操作可以选择两个元素进行交换，
     * 交换的代价是两个元素中的较小值。目标是通过交换操作使得两个数组
     * 包含相同的元素（不考虑顺序），返回达到目标的最小代价。
     *
     * @param basket1 第一个数组
     * @param basket2 第二个数组
     * @return 达到目标的最小代价，如果无法达到目标则返回-1
     */
    public long minCost(int[] basket1, int[] basket2) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i < basket1.length; i++) {
            cnt.merge(basket1[i], 1, Integer::sum);
            cnt.merge(basket2[i], -1, Integer::sum);
            // 交集元素在这个过程中互相抵消
        }

        // 分离出需要重新分配的元素
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        int mn = Integer.MAX_VALUE; // 记录所有元素中的最小值，作为中介交换的代价
        for (Map.Entry<Integer, Integer> e : cnt.entrySet()) {
            int x = e.getKey();
            int c = e.getValue();
            if (c % 2 != 0) { // 如果某个元素的差值为奇数，无法均分，无解
                return -1;
            }
            mn = Math.min(mn, x);
            // 将需要重新分配的元素的一半分别放入a和b中
            if (c > 0) {
                for (int i = 0; i < c / 2; i++) {
                    a.add(x);
                }
            } else {
                for (int i = 0; i < -c / 2; i++) {
                    b.add(x);
                }
            }
        }

        // 对a升序排序，对b降序排序，以便贪心地进行配对
        Collections.sort(a);
        b.sort(Collections.reverseOrder());

        // 计算最小交换代价
        long ans = 0;
        for (int i = 0; i < a.size(); i++) {
            // 对于每一对需要交换的元素，选择直接交换和通过最小元素中介交换中较小的代价
            ans += Math.min(Math.min(a.get(i), b.get(i)), mn * 2);
        }
        return ans;
    }

}
