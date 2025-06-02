package T2506;

import java.util.Arrays;

/**
 * @Description: 贪心
 * @Author: iniwym
 * @Date: 2025-06-02
 * @Link: https://leetcode.cn/problems/candy/
 */
public class Code0135_Candy {
    /**
     * 分发糖果的方法
     * 该方法旨在确保评分更高的孩子至少比相邻评分更低的孩子多得到一颗糖果
     *
     * @param ratings 孩子们的评分数组
     * @return 最少需要的糖果数量
     */
    public int candy(int[] ratings) {
        // 孩子们的数量
        int n = ratings.length;
        // 初始化两个数组，分别用于记录从左到右和从右到左的糖果分配
        int[] left = new int[n];
        int[] right = new int[n];

        // 初始化left数组，每个孩子至少有一颗糖果
        Arrays.fill(left, 1);
        // 初始化right数组，每个孩子至少有一颗糖果
        Arrays.fill(right, 1);

        // 从左到右遍历，确保右边评分更高的孩子得到更多的糖果
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        // 从右到左遍历，确保左边评分更高的孩子得到更多的糖果
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }

        // 初始化最终需要的糖果数量
        int ans = 0;
        // 计算最终的糖果分配，取每个孩子左右两边糖果数的最大值
        for (int i = 0; i < n; i++) {
            ans += Math.max(left[i], right[i]);
        }

        // 返回最终需要的糖果数量
        return ans;
    }

}
