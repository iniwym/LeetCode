package T2603;

import java.util.Arrays;

/**
 * @Description: 枚举
 * @Author: iniwym
 * @Date: 2026-03-17
 * @Link: https://leetcode.cn/problems/largest-submatrix-with-rearrangements/
 */
public class Code1727_LargestSubmatrixWithRearrangements {

    /**
     * 计算重新排列后最大的全 1 子矩阵面积
     * <p>
     * 解题思路：
     * 1. 逐行枚举子矩阵的底边
     * 2. 维护每列的连续 1 的高度
     * 3. 对高度数组排序，贪心计算最大面积
     *
     * @param matrix 输入的二进制矩阵
     * @return 重新排列后最大的全 1 子矩阵面积
     */
    public int largestSubmatrix(int[][] matrix) {
        int n = matrix[0].length;
        // 记录每列的连续 1 的高度
        int[] heights = new int[n];
        int ans = 0;

        // 枚举每一行作为子矩阵的底边
        for (int[] row : matrix) {
            // 更新每列的高度
            for (int j = 0; j < n; j++) {
                if (row[j] == 0) {
                    heights[j] = 0;
                } else {
                    heights[j]++;
                }
            }

            // 复制并排序高度数组，用于贪心计算
            int[] hs = heights.clone();
            Arrays.sort(hs);

            // 枚举所有可能的宽度，计算最大面积
            for (int i = 0; i < n; i++) {
                // 子数组长为 n-i，最小值为 hs[i]，对应的子矩形面积为 (n-i)*hs[i]
                ans = Math.max(ans, (n - i) * hs[i]);
            }
        }

        return ans;
    }


}
