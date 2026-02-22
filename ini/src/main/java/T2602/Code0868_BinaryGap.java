package T2602;

/**
 * @Description: 二进制
 * @Author: iniwym
 * @Date: 2026-02-22
 * @Link: https://leetcode.cn/problems/binary-gap/
 */
public class Code0868_BinaryGap {

    /**
     * 计算给定正整数 n 的二进制表示中两个相邻 1 之间的最大距离。
     * <p>
     * 方法说明：
     * 1. 首先去除 n 末尾的连续 0，使得最低位为 1。
     * 2. 然后依次计算每对相邻 1 之间的距离，并记录最大值。
     * <p>
     * 参数：
     * n - 输入的正整数（范围：1 <= n <= 10^9）
     * <p>
     * 返回值：
     * 返回两个相邻 1 之间的最大距离；如果只有一个 1 或没有 1，则返回 0。
     */
    public int binaryGap(int n) {
        int ans = 0;
        // 去除 n 末尾的连续 0，使最低位变为 1
        n /= (n & -n) * 2;

        // 循环处理剩余的二进制位
        while (n > 0) {
            // 计算当前最低位 1 到下一个 1 之间的距离
            int gap = Integer.numberOfTrailingZeros(n) + 1;
            // 更新最大距离
            ans = Math.max(ans, gap);
            // 右移去掉已处理的部分
            n >>= gap;
        }

        return ans;
    }

}
