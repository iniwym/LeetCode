package T2510;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @Description: 有序集合
 * @Author: iniwym
 * @Date: 2025-10-07
 * @Link: https://leetcode.cn/problems/avoid-flood-in-the-city/
 */
public class Code1488_AvoidFloodInTheCity {
    /**
     * 避免洪水泛滥问题
     * 给定一个数组rains，其中rains[i]表示第i天的操作：
     * - 如果rains[i] > 0，表示这一天湖rains[i]会下雨并被装满
     * - 如果rains[i] = 0，表示这一天可以抽干一个湖泊
     * 目标是避免任何湖泊在已满的情况下再次下雨（导致洪水）
     *
     * @param rains 操作数组，正数表示该湖泊下雨，0表示可抽水
     * @return 结果数组，-1表示下雨日，正数表示在该抽水日抽干的湖泊编号，
     * 如果无法避免洪水则返回空数组
     */
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] ans = new int[n];
        Map<Integer, Integer> fullDay = new HashMap<>(); // lake -> 装满日
        TreeSet<Integer> dryDay = new TreeSet<>(); // 未被使用的抽水日

        // 遍历每一天的操作
        for (int i = 0; i < n; i++) {
            int lake = rains[i];
            if (lake == 0) {
                ans[i] = 1; // 先随便选一个湖抽干
                dryDay.add(i); // 保存抽水日
                continue;
            }

            // 处理湖泊下雨的情况
            Integer j = fullDay.get(lake);
            if (j != null) {
                // 该湖泊之前已经满了，需要在它再次装满前抽干

                // 必须在 j 之后，i 之前把 lake 抽干
                // 选一个最早的未被使用的抽水日，如果选晚的，可能会导致其他湖没有可用的抽水日
                Integer d = dryDay.higher(j);
                if (d == null) {
                    return new int[]{}; // 无法阻止洪水
                }
                ans[d] = lake;
                dryDay.remove(d); // 移除已使用的抽水日
            }
            ans[i] = -1;
            fullDay.put(lake, i); // 插入或更新装满日
        }
        return ans;
    }

}

