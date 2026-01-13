package T2601;

/**
 * @Description: 二分查找
 * @Author: iniwym
 * @Date: 2026-01-13
 * @Link: https://leetcode.cn/problems/separate-squares-i/
 */
public class Code3453_SeparateSquaresI {

    /**
     * 计算分离正方形所需的最小高度
     * 使用二分查找算法找到能够容纳所有正方形的最小垂直空间
     *
     * @param squares 二维数组，每个元素表示一个正方形 [x坐标, y坐标, 边长]
     * @return 返回分离所有正方形所需的最小高度值
     */
    public double separateSquares(int[][] squares) {
        long totArea = 0;
        int maxY = 0;
        for (int[] sq : squares) {
            int l = sq[2];
            totArea += (long) l * l;
            maxY = Math.max(maxY, sq[1] + l);
        }

        // 二分查找范围：从0到最大y坐标值
        double left = 0;
        double right = maxY;
        for (int i = 0; i < 47; i++) {
            double mid = (left + right) / 2;
            if (check(squares, mid, totArea)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return (left + right) / 2; // 区间中点误差小
    }

    /**
     * 检查在给定y坐标下方的正方形区域面积是否达到总面积的一半
     *
     * @param squares 正方形数组，每个元素包含[x坐标, y坐标, 边长]信息
     * @param y       用于判断的y坐标阈值
     * @param totArea 总面积的一半（作为比较基准）
     * @return 如果y坐标下方的累积面积大于等于总面积的一半则返回true，否则返回false
     */
    private boolean check(int[][] squares, double y, long totArea) {
        double area = 0;

        // 遍历所有正方形，计算在y坐标下方的面积
        for (int[] sq : squares) {
            double yi = sq[1];
            if (yi < y) {
                double l = sq[2];
                // 计算当前正方形在y坐标下方的部分的面积并累加
                area += l * Math.min(y - yi, l);
            }
        }

        // 判断累积面积是否达到总面积的一半
        return area >= totArea / 2.0;
    }

}
