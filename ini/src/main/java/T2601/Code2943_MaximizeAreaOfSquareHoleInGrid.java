package T2601;

import java.util.Arrays;

/**
 * @Description: 贪心策略
 * @Author: iniwym
 * @Date: 2026-01-15
 * @Link: https://leetcode.cn/problems/maximize-area-of-square-hole-in-grid/
 */
public class Code2943_MaximizeAreaOfSquareHoleInGrid {
    /**
     * 计算在网格中由水平和垂直挡板形成的最大正方形空洞的面积
     *
     * @param n     网格的行数
     * @param m     网格的列数
     * @param hBars 水平挡板的位置数组
     * @param vBars 垂直挡板的位置数组
     * @return 最大正方形空洞的面积
     */
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        int side = Math.min(f(hBars), f(vBars)) + 1;
        return side * side;
    }


    /**
     * 计算数组排序后最长连续递增子序列的长度
     *
     * @param a 输入的整数数组
     * @return 返回排序后数组中最长连续递增子序列的长度
     */
    private int f(int[] a) {
        Arrays.sort(a);
        int mx = 1;
        int cnt = 1;

        // 遍历排序后的数组，计算连续递增序列的最大长度
        for (int i = 1; i < a.length; i++) {
            if (a[i] == a[i - 1] + 1) {
                cnt++;
                mx = Math.max(mx, cnt);
            } else {
                cnt = 1; // 重新计数
            }
        }
        return mx;
    }
}

