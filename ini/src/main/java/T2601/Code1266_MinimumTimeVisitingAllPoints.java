package T2601;

/**
 * @Description: 切比雪夫距离
 * @Author: iniwym
 * @Date: 2026-01-12
 * @Link: https://leetcode.cn/problems/minimum-time-visiting-all-points/
 */
public class Code1266_MinimumTimeVisitingAllPoints {

    /**
     * 计算访问所有点所需的最小时间
     * 在每一秒内，可以沿水平、竖直或对角线方向移动一个单位距离
     * 对角线移动相当于同时在x和y方向各移动一个单位，因此移动到目标点的时间取决于x或y方向中需要移动的最大距离
     *
     * @param points 二维数组，points[i] = [xi, yi] 表示第i个点的坐标
     * @return 访问所有点所需的最小总时间
     */
    public int minTimeToVisitAllPoints(int[][] points) {
        int ans = 0;
        // 遍历相邻点对，计算从一个点到下一个点所需的时间
        for (int i = 1; i < points.length; i++) {
            int[] p = points[i - 1];
            int[] q = points[i];
            // 两点间最短时间为x坐标差和y坐标差中的最大值（对角线移动策略）
            ans += Math.max(Math.abs(p[0] - q[0]), Math.abs(p[1] - q[1]));
        }
        return ans;
    }


}
