package T2503;

import java.util.Arrays;

/**
 * @Description: 子数组最大累加和
 * @Author: iniwym
 * @Date: 2025-03-11
 * @Link: https://leetcode.cn/problems/max-submatrix-lcci/
 */
public class Code1724_MaxSubmatrixLcci {
    /**
     * 找到矩阵中的最大子矩阵并返回其左上角和右下角坐标。
     *
     * @param matrix 输入的二维整数矩阵
     * @return 包含四个元素的数组，分别表示左上角行、左上角列、右下角行、右下角列
     */
    public int[] getMaxMatrix(int[][] matrix) {

        int n = matrix.length, m = matrix[0].length;
        int a = 0, b = 0, c = 0, d = 0;
        int max = Integer.MIN_VALUE;

        int[] nums = new int[m];
        // 枚举所有可能的上下边界（up为上边界，down为下边界）
        for (int up = 0; up < n; up++) {
            Arrays.fill(nums, 0); // 重置nums数组为0
            for (int down = up; down < n; down++) {

                // 使用Kadane算法在当前行的列和数组中寻找最大子数组
                for (int l = 0, r = 0, pre = Integer.MIN_VALUE; r < m; r++) {
                    nums[r] += matrix[down][r];

                    if (pre >= 0) {
                        pre += nums[r];
                    } else {
                        pre = nums[r];
                        l = r;
                    }
                    if (pre > max) {
                        max = pre;
                        a = up;
                        b = l;
                        c = down;
                        d = r;

                    }
                }

            }
        }

        return new int[]{a, b, c, d};
    }

}
