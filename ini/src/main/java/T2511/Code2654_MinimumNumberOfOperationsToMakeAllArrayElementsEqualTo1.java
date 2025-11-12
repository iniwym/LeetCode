package T2511;

/**
 * @Description: gcd
 * @Author: iniwym
 * @Date: 2025-11-12
 * @Link: https://leetcode.cn/problems/minimum-number-of-operations-to-make-all-array-elements-equal-to-1/
 */
public class Code2654_MinimumNumberOfOperationsToMakeAllArrayElementsEqualTo1 {

    /**
     * 解决获取最少操作次数使数组所有元素变为1的问题
     * <p>
     * 解题思路：
     * 1. 如果数组中所有元素的最大公约数大于1，则无法得到1，返回-1
     * 2. 如果数组中已经存在1，则只需要将其他元素变为1，操作次数为n-cnt1
     * 3. 如果数组中没有1，则需要找到最短的子数组使其gcd为1，然后通过操作将其变为1，
     * 最后将其他元素也变为1
     *
     * @param nums 输入的正整数数组
     * @return 使所有元素变为1的最少操作次数，如果不可能则返回-1
     */
    class Solution {
        public int minOperations(int[] nums) {
            int n = nums.length, gcdAll = 0, cnt1 = 0;
            // 计算所有元素的最大公约数，同时统计1的个数
            for (int x : nums) {
                gcdAll = gcd(gcdAll, x);
                if (x == 1) ++cnt1;
            }
            // 如果所有元素的最大公约数大于1，无法得到1
            if (gcdAll > 1) return -1;
            // 如果数组中已经存在1，只需要将其他元素变为1
            if (cnt1 > 0) return n - cnt1;

            // 寻找最短的gcd为1的连续子数组
            int minSize = n;
            for (int i = 0; i < n; i++) {
                int g = 0;
                for (int j = i; j < n; j++) {
                    g = gcd(g, nums[j]);
                    if (g == 1) {
                        // 这里本来是 j-i+1，把 +1 提出来合并到 return 中
                        minSize = Math.min(minSize, j - i);
                        break;
                    }
                }
            }
            // 找到最短子数组后，需要该子数组长度-1次操作得到第一个1，
            // 然后需要n-1次操作将其他元素变为1
            return minSize + n - 1;
        }

        /**
         * 计算两个数的最大公约数
         *
         * @param a 第一个数
         * @param b 第二个数
         * @return a和b的最大公约数
         */
        private int gcd(int a, int b) {
            while (a != 0) {
                int tmp = a;
                a = b % a;
                b = tmp;
            }
            return b;
        }
    }


}
