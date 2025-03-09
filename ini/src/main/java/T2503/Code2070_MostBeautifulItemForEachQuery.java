package T2503;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * @Description: 二分查找
 * @Author: iniwym
 * @Date: 2025-03-09
 * @Link: https://leetcode.cn/problems/most-beautiful-item-for-each-query/
 */
public class Code2070_MostBeautifulItemForEachQuery {
    public int[] maximumBeauty1(int[][] items, int[] queries) {
        // 按价格升序排序物品
        Arrays.sort(items, (a, b) -> a[0] - b[0]);

        // 使用 TreeMap 存储价格到最大美丽值的映射
        TreeMap<Integer, Integer> priceToMaxBeauty = new TreeMap<>();
        int currentMax = 0;
        for (int[] item : items) {
            currentMax = Math.max(currentMax, item[1]); // 维护当前最大美丽值
            priceToMaxBeauty.put(item[0], currentMax); // 存储价格和对应的最大值
        }

        // 处理每个查询
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            Integer floorPrice = priceToMaxBeauty.floorKey(queries[i]); // 查找≤queries[i]的最大价格
            answer[i] = (floorPrice != null) ? priceToMaxBeauty.get(floorPrice) : 0;
        }
        return answer;
    }

    public int[] maximumBeauty(int[][] items, int[] queries) {
        // 按价格升序排序物品
        Arrays.sort(items, (a, b) -> a[0] - b[0]);

        int[] maxBeauty = new int[items.length];
        maxBeauty[0] = items[0][1];
        for (int i = 1; i < items.length; i++) {
            maxBeauty[i] = Math.max(maxBeauty[i - 1], items[i][1]); // 维护当前最大美丽值
        }

        // 处理每个查询
        int[] answer = new int[queries.length];
        for (int j = 0; j < queries.length; j++) {
            int q = queries[j];
            // 手动二分查找最后一个价格≤q的索引
            int left = 0, right = items.length - 1, res = -1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (items[mid][0] <= q) {
                    res = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            answer[j] = (res != -1) ? maxBeauty[res] : 0;
        }
        return answer;
    }
}
