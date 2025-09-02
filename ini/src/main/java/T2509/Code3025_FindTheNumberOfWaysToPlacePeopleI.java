package T2509;

/**
 * @Description: 暴力循环
 * @Author: iniwym
 * @Date: 2025-09-02
 * @Link: https://leetcode.cn/problems/find-the-number-of-ways-to-place-people-i/
 */
public class Code3025_FindTheNumberOfWaysToPlacePeopleI {

    /**
     * 计算点对的数量，满足特定的几何条件
     * <p>
     * 该函数遍历所有点对(A,B)，找出满足以下条件的点对数量：
     * 1. 点A的x坐标小于等于点B的x坐标，且点A的y坐标大于等于点B的y坐标
     * 2. 在矩形区域(由点A和点B确定的左上角和右下角)内，除了A和B之外没有其他点
     *
     * @param points 二维整数数组，表示平面上的点集合，每个点格式为[x, y]
     * @return 满足条件的点对数量
     */
    public int numberOfPairs(int[][] points) {
        int ans = 0;
        int n = points.length;

        // 遍历所有可能的点对组合
        for (int i = 0; i < n; i++) {
            int[] pointA = points[i];
            for (int j = 0; j < n; j++) {
                int[] pointB = points[j];
                // 跳过相同点，以及不满足基本几何条件的点对
                if (i == j || !(pointA[0] <= pointB[0] && pointA[1] >= pointB[1])) {
                    continue;
                }
                // 特殊情况：只有两个点时直接计数
                if (n == 2) {
                    ans++;
                    continue;
                }

                // 检查在点A和点B构成的矩形区域内是否存在其他点
                boolean illegal = false;
                for (int k = 0; k < n; k++) {
                    if (k == i || k == j) {
                        continue;
                    }

                    int[] pointTmp = points[k];
                    // 检查点是否在由点A(左上角)和点B(右下角)构成的矩形区域内
                    boolean isXContained = pointTmp[0] >= pointA[0] && pointTmp[0] <= pointB[0];
                    boolean isYContained = pointTmp[1] <= pointA[1] && pointTmp[1] >= pointB[1];
                    if (isXContained && isYContained) {
                        illegal = true;
                        break;
                    }
                }
                // 如果矩形区域内没有其他点，则该点对有效
                if (!illegal) {
                    ans++;
                }
            }
        }
        return ans;
    }

}
