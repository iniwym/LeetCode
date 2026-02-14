package T2602;

/**
 * @Description: BFS
 * @Author: iniwym
 * @Date: 2026-02-14
 * @Link: https://leetcode.cn/problems/champagne-tower/
 */
public class Code0799_ChampagneTower {

    /**
     * 计算香槟塔中指定位置的香槟体积。
     * <p>
     * 香槟从顶部倒入，每一层的杯子最多只能装 1 单位的香槟。如果某个杯子装满后仍有香槟溢出，
     * 则会平均分配给下一层相邻的两个杯子。
     *
     * @param poured     倒入的香槟总量（单位）
     * @param queryRow   查询的行号（从 0 开始）
     * @param queryGlass 查询的列号（从 0 开始）
     * @return 返回指定位置杯子中的香槟体积（范围在 0 到 1 之间）
     */
    public double champagneTower(int poured, int queryRow, int queryGlass) {
        // 初始化当前层的香槟分布，第一层只有一个杯子
        double[] cur = new double[]{(double) poured};

        // 逐层计算香槟的分布情况，直到目标行
        for (int i = 1; i <= queryRow; i++) {
            // 创建下一层的香槟分布数组
            double[] nxt = new double[i + 1];

            // 遍历当前层的每个杯子，计算溢出并分配到下一层
            for (int j = 0; j < cur.length; j++) {
                double x = cur[j] - 1; // 当前杯子溢出的香槟量
                if (x > 0) { // 如果有溢出，则平均分配到下一层的两个杯子
                    nxt[j] += x / 2;
                    nxt[j + 1] += x / 2;
                }
            }

            // 更新当前层为下一层
            cur = nxt;
        }

        // 返回目标位置的香槟体积，最大不超过 1
        return Math.min(cur[queryGlass], 1);
    }

}
