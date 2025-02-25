package T2502;

import java.util.List;

/**
 * @Description: 贪心算法和线性遍历算法
 * @Author: iniwym
 * @Date: 2025-02-19
 * @Link: https://leetcode.cn/problems/maximum-distance-in-arrays/
 */
public class Code0624_MaximumDistanceInArrays {
    /**
     * 计算多个整数数组中的最大最小值之间的最大距离
     *
     * @param arrays 包含多个整数数组的列表
     * @return 最大最小值之间的最大距离
     */
    public int maxDistance(List<List<Integer>> arrays) {

        // 初始化最小值和最大值为第一个数组的第一个和最后一个元素
        int min = arrays.get(0).get(0);
        int max = arrays.get(0).get(arrays.get(0).size() - 1);
        int ans = 0;

        // 遍历剩余的数组
        for (int i = 1; i < arrays.size(); i++) {
            // 获取当前数组的最小值和最大值
            int minN = arrays.get(i).get(0);
            int maxN = arrays.get(i).get(arrays.get(i).size() - 1);

            // 更新最大距离为当前数组的最大值与之前数组的最小值之差
            ans = Math.max(ans, Math.abs(maxN - min));
            // 更新最大距离为之前数组的最大值与当前数组的最小值之差
            ans = Math.max(ans, Math.abs(max - minN));

            // 更新全局最小值和最大值
            min = Math.min(min, minN);
            max = Math.max(max, maxN);
        }
        // 返回最大距离
        return ans;

    }
}
