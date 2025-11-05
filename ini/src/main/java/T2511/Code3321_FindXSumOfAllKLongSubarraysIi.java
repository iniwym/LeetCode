package T2511;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @Description: 有序集合
 * @Author: iniwym
 * @Date: 2025-11-05
 * @Link: https://leetcode.cn/problems/find-x-sum-of-all-k-long-subarrays-ii/
 */
public class Code3321_FindXSumOfAllKLongSubarraysIi {

    /**
     * 解决方案类，用于计算数组中每个长度为k的子数组的X-Sum。
     * X-Sum定义为：选出出现次数最多的前x个不同数字，将它们的值乘以其出现次数后求和。
     */
    class Solution {
        // 存储较大频率元素的TreeSet，按频率升序排列（若频率相同则按键升序）
        private final TreeSet<int[]> L = new TreeSet<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        // 存储较小频率元素的TreeSet，使用与L相同的比较器
        private final TreeSet<int[]> R = new TreeSet<>(L.comparator());
        // 记录每个数值当前在窗口中的出现次数
        private final Map<Integer, Integer> cnt = new HashMap<>();
        // 当前L集合中所有元素的加权和（频率*值），即X-Sum的一部分
        private long sumL = 0;

        /**
         * 找出数组中每个长度为k的滑动窗口的X-Sum。
         *
         * @param nums 输入整数数组
         * @param k    滑动窗口的大小
         * @param x    要选择的最大频率元素数量
         * @return 长度为nums.length - k + 1的结果数组，表示每个窗口的X-Sum
         */
        public long[] findXSum(int[] nums, int k, int x) {
            long[] ans = new long[nums.length - k + 1];
            for (int r = 0; r < nums.length; r++) {
                // 将新进入窗口的元素加入统计结构
                int in = nums[r];
                del(in);
                cnt.merge(in, 1, Integer::sum); // 增加该元素计数
                add(in);

                int l = r + 1 - k;
                if (l < 0) {
                    continue;
                }

                // 维护L和R两个TreeSet的大小关系，确保L最多包含x个最大频率元素
                while (!R.isEmpty() && L.size() < x) {
                    r2l(); // 将R中最大的移入L
                }
                while (L.size() > x) {
                    l2r(); // 将L中最小的移入R
                }
                ans[l] = sumL; // 当前窗口的X-Sum

                // 移除即将离开窗口的元素
                int out = nums[l];
                del(out);
                cnt.merge(out, -1, Integer::sum); // 减少该元素计数
                add(out);
            }
            return ans;
        }

        /**
         * 根据当前元素的频率将其插入合适的TreeSet中。
         * 如果其频率足够大可以进入L，则更新sumL并插入L；否则插入R。
         *
         * @param val 元素值
         */
        private void add(int val) {
            int c = cnt.get(val);
            if (c == 0) {
                return;
            }
            int[] p = new int[]{c, val};
            if (!L.isEmpty() && L.comparator().compare(p, L.first()) > 0) { // p比L中最小的大
                sumL += (long) p[0] * p[1];
                L.add(p);
            } else {
                R.add(p);
            }
        }

        /**
         * 删除指定元素对应的记录。如果该元素存在于L中，则同时更新sumL。
         *
         * @param val 待删除的元素值
         */
        private void del(int val) {
            int c = cnt.getOrDefault(val, 0);
            if (c == 0) {
                return;
            }
            int[] p = new int[]{c, val};
            if (L.contains(p)) {
                sumL -= (long) p[0] * p[1];
                L.remove(p);
            } else {
                R.remove(p);
            }
        }

        /**
         * 将L中最左边（频率最低）的一个元素移动到R中，并更新sumL。
         */
        private void l2r() {
            int[] p = L.pollFirst();
            sumL -= (long) p[0] * p[1];
            R.add(p);
        }

        /**
         * 将R中最右边（频率最高）的一个元素移动到L中，并更新sumL。
         */
        private void r2l() {
            int[] p = R.pollLast();
            sumL += (long) p[0] * p[1];
            L.add(p);
        }
    }

}
