package T2508;

import java.util.Arrays;

/**
 * @Description: 单调栈
 * @Author: iniwym
 * @Date: 2025-08-21
 * @Link: https://leetcode.cn/problems/count-submatrices-with-all-ones/
 */
public class Code1504_CountSubmatricesWithAllOnes {

    /**
     * 最大矩阵宽度常量，用于定义height和stack数组的大小
     */
    public static int MAXM = 151;

    /**
     * 用于记录每一列的当前高度（连续1的个数）
     */
    public static int[] height = new int[MAXM];

    /**
     * 单调栈，用于存储列索引，辅助计算矩形数量
     */
    public static int[] stack = new int[MAXM];

    /**
     * 栈顶指针，指向栈中下一个可写入位置
     */
    public static int r;

    /**
     * 计算二进制矩阵中全为1的子矩形数量
     *
     * @param mat 输入的二维二进制矩阵
     * @return 矩阵中全为1的子矩形总数
     */
    public static int numSubmat(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int ans = 0;
        // 初始化height数组前m个元素为0
        Arrays.fill(height, 0, m, 0);
        for (int i = 0; i < n; i++) {
            // 更新每一列的高度：如果当前位置为0则高度重置为0，否则高度加1
            for (int j = 0; j < m; j++) {
                height[j] = mat[i][j] == 0 ? 0 : height[j] + 1;
            }
            // 累加当前行作为底边时能形成的矩形数量
            ans += countFromBottom(m);
        }
        return ans;
    }

    /**
     * 使用单调栈计算以当前行作为底边能形成的全1子矩形数量
     * 核心思想：对于每个位置，计算必须以该位置高度作为矩形高度时能形成的矩形数量
     *
     * @param m 矩阵的列数
     * @return 当前行能形成的全1子矩形数量
     */
    public static int countFromBottom(int m) {
        // height[0...m-1]
        r = 0;
        int ans = 0;
        for (int i = 0, left, len, bottom; i < m; i++) {
            // 维护单调递增栈，当遇到较小高度时开始结算
            while (r > 0 && height[stack[r - 1]] >= height[i]) {
                int cur = stack[--r];
                if (height[cur] > height[i]) {
                    // 只有当前高度严格大于下一个高度时才进行结算
                    // 如果是因为相等导致弹出则不结算，避免重复计算
                    left = r == 0 ? -1 : stack[r - 1];
                    len = i - left - 1;
                    // 计算矩形的最小下界高度
                    bottom = Math.max(left == -1 ? 0 : height[left], height[i]);
                    // 计算在指定范围内以不同高度作为矩形高度时的矩形数量
                    ans += (height[cur] - bottom) * len * (len + 1) / 2;
                }
            }
            stack[r++] = i;
        }
        // 处理栈中剩余元素
        while (r > 0) {
            int cur = stack[--r];
            int left = r == 0 ? -1 : stack[r - 1];
            int len = m - left - 1;
            int down = left == -1 ? 0 : height[left];
            ans += (height[cur] - down) * len * (len + 1) / 2;
        }
        return ans;
    }


}
