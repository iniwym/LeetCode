package T2505;

/**
 * @Description: 贪心
 * @Author: iniwym
 * @Date: 2025-05-03
 * @Link: https://leetcode.cn/problems/minimum-domino-rotations-for-equal-row/
 */
public class Code1007_MinimumDominoRotationsForEqualRow {
    /**
     * 计算最少的多米诺旋转次数以使顶部或底部的数字相同
     *
     * @param tops    多米诺顶部数字数组
     * @param bottoms 多米诺底部数字数组
     * @return 返回最少的旋转次数，如果无法实现，则返回-1
     */
    public static int minDominoRotations(int[] tops, int[] bottoms) {
        // 多米诺的数量
        int n = tops.length;
        // 第一个多米诺的顶部和底部数字
        int top0 = tops[0];
        int bottom0 = bottoms[0];
        // 第二个多米诺的顶部和底部数字
        int top1 = tops[1];
        int bottom1 = bottoms[1];

        // x1和x2用于记录可能的统一数字
        int x1 = 0, x2 = 0;
        // 检查第一个数字是否可以作为统一数字
        if (top0 == top1 || top0 == bottom1) {
            x1 = top0;
        }

        // 检查第二个数字是否可以作为统一数字
        if (bottom0 == top1 || bottom0 == bottom1) {
            x2 = bottom0;
        }

        // 如果没有找到可能的统一数字，返回-1
        if (x1 == 0 && x2 == 0) {
            return -1;
        }

        // ans1和ans2用于记录当x1或x2作为统一数字时的最少旋转次数
        int ans1 = -1, ans2 = -1;
        // 如果x1是可能的统一数字，计算最少旋转次数
        if (x1 != 0) {
            int x = x1;
            int topCount = 0;
            int bottomCount = 0;

            boolean flag = false;
            // 遍历所有多米诺，计算旋转次数
            for (int i = 0; i < n; i++) {
                if (tops[i] != x && bottoms[i] != x) {
                    flag = true;
                    break;
                }
                if (tops[i] != x) {
                    topCount++;
                }
                if (bottoms[i] != x) {
                    bottomCount++;
                }
            }
            ans1 = flag ? -1 : Math.min(topCount, bottomCount);
        }

        // 如果x2是可能的统一数字，计算最少旋转次数
        if (x2 != 0) {
            int x = x2;
            int topCount = 0;
            int bottomCount = 0;
            boolean flag = false;
            // 遍历所有多米诺，计算旋转次数
            for (int i = 0; i < n; i++) {
                if (tops[i] != x && bottoms[i] != x) {
                    flag = true;
                    break;
                }
                if (tops[i] != x) {
                    topCount++;
                }
                if (bottoms[i] != x) {
                    bottomCount++;
                }
            }
            ans2 = flag ? -1 : Math.min(topCount, bottomCount);
        }

        // 根据ans1和ans2的值确定最终的最少旋转次数
        int ans = -1;
        if (ans1 != -1 && ans2 != -1) {
            ans = Math.min(ans1, ans2);
        } else if (ans1 != -1) {
            ans = ans1;
        } else if (ans2 != -1) {
            ans = ans2;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] tops = {1, 2, 1, 1, 1, 2, 2, 2};
        int[] bottoms = {2, 1, 2, 2, 2, 2, 2, 2};
        System.out.println(minDominoRotations(tops, bottoms));
    }
}
