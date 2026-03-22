package T2603;

import java.util.Arrays;

/**
 * @Description: 矩阵旋转
 * @Author: iniwym
 * @Date: 2026-03-22
 * @Link: https://leetcode.cn/problems/determine-whether-matrix-can-be-obtained-by-rotation/
 */
public class Code1886_DetermineWhetherMatrixCanBeObtainedByRotation {

    /**
     * 判断目标矩阵是否可以通过旋转原矩阵得到
     * <p>
     * 该方法检查给定的矩阵mat是否可以通过0°、90°、180°或270°的顺时针旋转
     * 转换为目标矩阵target。算法尝试最多四次旋转（每次90度），每次旋转后
     * 检查两个矩阵是否相等。
     *
     * @param mat    原始二维整数矩阵，该方法会对其进行修改（旋转操作）
     * @param target 目标二维整数矩阵，用于比较
     * @return 如果mat经过0°、90°、180°或270°旋转后可以等于target则返回true；否则返回false
     */
    public boolean findRotation(int[][] mat, int[][] target) {
        // 尝试最多四次旋转（0°, 90°, 180°, 270°），因为四次90度旋转后矩阵会回到原始状态
        for (int i = 0; i < 4; i++) {
            // 检查当前旋转状态下的矩阵是否与目标矩阵相等
            if (Arrays.deepEquals(mat, target)) {
                return true;
            }
            // 对矩阵进行90度顺时针旋转，为下一次比较做准备
            rotate(mat);
        }
        // 经过所有可能的旋转后仍未找到匹配，返回false
        return false;
    }

    /**
     * 将二维矩阵顺时针旋转90度
     * <p>
     * 该方法使用"转置+水平翻转"的组合操作来实现90度顺时针旋转：
     * 1. 首先对矩阵进行转置（沿主对角线交换元素）
     * 2. 然后对每一行进行水平翻转（左右翻转）
     * 这两个操作的组合等价于整个矩阵顺时针旋转90度。
     * 注意：此方法会直接修改原矩阵。
     *
     * @param matrix 输入的n×n二维整数矩阵，将在原矩阵上进行旋转操作
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 遍历矩阵的每一行
        for (int i = 0; i < n; i++) {
            int[] row = matrix[i];
            // 对矩阵进行转置操作：交换matrix[i][j]和matrix[j][i]
            // 只遍历对角线上方的元素，避免重复交换
            for (int j = i + 1; j < n; j++) {
                int tmp = row[j];
                row[j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
            // 对每一行进行水平翻转（左右翻转）
            // 只遍历前半部分元素，与对应的后半部分元素交换
            for (int j = 0; j < n / 2; j++) {
                int tmp = row[j];
                row[j] = row[n - 1 - j];
                row[n - 1 - j] = tmp;
            }
        }
    }

}

