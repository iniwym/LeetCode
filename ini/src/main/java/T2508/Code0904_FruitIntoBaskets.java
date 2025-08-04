package T2508;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 滑动窗口
 * @Author: iniwym
 * @Date: 2025-08-04
 * @Link: https://leetcode.cn/problems/fruit-into-baskets/
 */
public class Code0904_FruitIntoBaskets {

    /**
     * 计算最多可以收集的水果数量
     * 使用滑动窗口算法，找到数组中最多包含两种不同水果的最长连续子数组
     *
     * @param fruits 表示水果种类的数组，每个元素代表一种水果的类型
     * @return 返回最多可以收集的水果数量（连续子数组的最大长度）
     */
    public int totalFruit(int[] fruits) {
        int ans = 0;
        int l = 0;
        Map<Integer, Integer> map = new HashMap<>();

        // 滑动窗口右边界遍历整个数组
        for (int r = 0; r < fruits.length; r++) {
            // 将当前水果加入窗口，更新其出现次数
            map.merge(fruits[r], 1, Integer::sum);

            // 当窗口中水果种类超过2种时，收缩左边界
            while (map.size() > 2) {
                int out = fruits[l];
                map.merge(out, -1, Integer::sum);
                // 如果某种水果数量减为0，则从窗口中移除
                if (map.get(out) == 0) {
                    map.remove(out);
                }
                l++;
            }

            // 更新最大窗口大小
            ans = Math.max(ans, r - l + 1);
        }

        return ans;
    }

}
