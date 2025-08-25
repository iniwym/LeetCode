package T2508;

/**
 * @Description: 遍历对角线
 * @Author: iniwym
 * @Date: 2025-08-25
 * @Link: https://leetcode.cn/problems/diagonal-traverse/
 */
public class Code0498_DiagonalTraverse {

    /**
     * 按对角线遍历矩阵，返回遍历结果数组
     * 遍历规则：第一条对角线从左上到右下，第二条从右上到左下，交替进行
     *
     * @param mat 输入的二维矩阵
     * @return 按对角线顺序遍历得到的一维数组
     */
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int idx = 0;
        int[] ans = new int[m * n];

        // 遍历每一条对角线，总共有 m + n - 1 条对角线
        for (int k = 0; k < m + n - 1; k++) {
            // 计算当前对角线中列索引的范围
            int minJ = Math.max(0, k - m + 1);
            int maxJ = Math.min(k, n - 1);

            // 偶数编号的对角线：从左下到右上遍历
            if (k % 2 == 0) {
                for (int j = minJ; j <= maxJ; j++) {
                    ans[idx++] = mat[k - j][j];
                }
            }
            // 奇数编号的对角线：从右上到左下遍历
            else {
                for (int j = maxJ; j >= minJ; j--) {
                    ans[idx++] = mat[k - j][j];
                }
            }
        }

        return ans;
    }


}
