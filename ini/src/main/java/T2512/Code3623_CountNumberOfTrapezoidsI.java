package T2512;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 枚举
 * @Author: iniwym
 * @Date: 2025-12-02
 * @Link: https://leetcode.cn/problems/count-number-of-trapezoids-i/
 */
public class Code3623_CountNumberOfTrapezoidsI {

    private static final int MOD = 1_000_000_007;

    /**
     * 计算给定点集中能构成梯形的四边形个数
     * <p>
     * 解题思路：
     * 1. 按行（y坐标）统计每条水平线上点的个数
     * 2. 对于每条水平线，计算其上任意两点组成的线段数 C(c,2) = c*(c-1)/2
     * 3. 每两条不同水平线上的线段可以组成一个梯形（因为平行于x轴的线段不会相交）
     * 4. 使用前缀和优化计算：对于当前水平线，将其线段数与之前所有水平线的线段数相乘累加
     *
     * @param points 二维点集，每个点表示为 [x, y]
     * @return 能构成梯形的四边形个数，结果对MOD取模
     */
    public int countTrapezoids(int[][] points) {
        Map<Integer, Integer> cnt = new HashMap<>(points.length, 1); // 预分配空间
        for (int[] p : points) {
            cnt.merge(p[1], 1, Integer::sum); // 统计每一行（水平线）有多少个点
        }

        long ans = 0, s = 0;
        // 遍历每条水平线上的点数，计算能组成的梯形数量
        for (int c : cnt.values()) {
            long k = (long) c * (c - 1) / 2; // 当前水平线上能组成的线段数（从c个点中选2个点）
            ans += s * k; // 当前线段数与之前所有线段数的乘积即为新增的梯形数
            s += k; // 更新前缀线段数和
        }
        return (int) (ans % MOD);
    }

}
