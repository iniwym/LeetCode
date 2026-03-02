package T2603;

import java.util.Arrays;

/**
 * @Description: 贪心策略
 * @Author: iniwym
 * @Date: 2026-03-02
 * @Link: https://leetcode.cn/problems/minimum-swaps-to-arrange-a-binary-grid/
 */
public class Code1536_MinimumSwapsToArrangeABinaryGrid {

    /**
     * 计算使二进制网格有效所需的最小相邻行交换次数
     * 有效网格定义为：对于每一行 i，主对角线右侧的所有元素均为 0
     *
     * @param grid 输入的二进制网格
     * @return 最小交换次数，如果无法完成则返回 -1
     */
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int[] pos = new int[n];
        Arrays.fill(pos, -1);
        // 统计每一行最右侧 1 的位置
        for (int i = 0; i < n; ++i) {
            for (int j = n - 1; j >= 0; --j) {
                if (grid[i][j] == 1) {
                    pos[i] = j;
                    break;
                }
            }
        }
        int ans = 0;
        // 贪心策略，为每一行寻找满足条件的最近行并交换
        for (int i = 0; i < n; ++i) {
            int k = -1;
            for (int j = i; j < n; ++j) {
                if (pos[j] <= i) {
                    ans += j - i;
                    k = j;
                    break;
                }
            }
            // 模拟相邻行交换，将找到的行移动到目标位置
            if (k >= 0) {
                for (int j = k; j > i; --j) {
                    int temp = pos[j];
                    pos[j] = pos[j - 1];
                    pos[j - 1] = temp;
                }
            } else {
                // 如果找不到满足条件的行，说明无法完成排列
                return -1;
            }
        }
        return ans;
    }

}
