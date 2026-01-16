package T2601;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 枚举
 * @Author: iniwym
 * @Date: 2026-01-16
 * @Link: https://leetcode.cn/problems/maximum-square-area-by-removing-fences-from-a-field/
 */
public class Code2975_MaximumSquareAreaByRemovingFencesFromAField {

    /**
     * 计算在给定网格中由水平和垂直围栏分割后能形成的最大正方形区域面积
     *
     * @param m       网格的高度（行数）
     * @param n       网格的宽度（列数）
     * @param hFences 水平方向的围栏位置数组
     * @param vFences 垂直方向的围栏位置数组
     * @return 最大正方形面积对1000000007取模的结果，如果无法形成正方形则返回-1
     */
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        final int MOD = 1_000_000_007;
        // 获取所有可能的水平间距并存储到集合中
        Set<Integer> hSet = f(hFences, m);
        // 获取所有可能的垂直间距并存储到集合中
        Set<Integer> vSet = f(vFences, n);

        int ans = 0;
        // 遍历所有水平间距，在垂直间距集合中查找相同的值，找到最大的公共间距
        for (int x : hSet) {
            if (vSet.contains(x)) {
                ans = Math.max(ans, x);
            }
        }
        // 如果找到了公共间距，则计算其平方作为正方形面积并取模；否则返回-1
        return ans > 0 ? (int) ((long) ans * ans % MOD) : -1;
    }


    /**
     * 计算数组中任意两个数的差值集合
     * 该方法会将原数组扩展并排序，然后计算所有可能的差值
     *
     * @param a  输入的整数数组
     * @param mx 最大值，会被添加到数组中参与差值计算
     * @return 包含所有可能差值的Set集合
     */
    private Set<Integer> f(int[] a, int mx) {
        int n = a.length;
        a = Arrays.copyOf(a, n + 2);
        a[n++] = 1;
        a[n++] = mx;
        Arrays.sort(a);

        // 计算 a 中任意两个数的差，保存到哈希集合中
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                set.add(a[j] - a[i]);
            }
        }
        return set;
    }

}
