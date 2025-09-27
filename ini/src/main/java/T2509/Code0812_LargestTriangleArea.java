package T2509;

/**
 * @Description: 枚举
 * @Author: iniwym
 * @Date: 2025-09-27
 * @Link: https://leetcode.cn/problems/largest-triangle-area/
 */
public class Code0812_LargestTriangleArea {

    /**
     * 计算给定二维点集中任意三个点组成的三角形的最大面积
     *
     * @param points 二维点集，每个点用长度为2的数组表示，points[i][0]为x坐标，points[i][1]为y坐标
     * @return 返回所有可能三角形中的最大面积
     */
    public double largestTriangleArea(int[][] points) {
        int n = points.length;
        int ans = 0;
        // 三重循环枚举所有可能的三个点的组合
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    int[] p1 = points[i], p2 = points[j], p3 = points[k];
                    // 计算向量p1p2和p1p3的坐标差值
                    int x1 = p2[0] - p1[0], y1 = p2[1] - p1[1];
                    int x2 = p3[0] - p1[0], y2 = p3[1] - p1[1];
                    // 使用向量叉积计算平行四边形面积，取最大值
                    ans = Math.max(ans, Math.abs(x1 * y2 - y1 * x2)); // 注意这里没有除以 2
                }
            }
        }
        // 将平行四边形面积转换为三角形面积
        return ans / 2.0;
    }

}

