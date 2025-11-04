package T2511;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @Description: 有序集合
 * @Author: iniwym
 * @Date: 2025-11-04
 * @Link: https://leetcode.cn/problems/find-x-sum-of-all-k-long-subarrays-i/
 */
public class Code3318_FindXSumOfAllKLongSubarraysI {

    /**
     * 解决方案类，用于计算数组中每个长度为 k 的子数组的 X-sum。
     * X-sum 定义为：选择出现次数最多的前 x 个不同数字，
     * 每个数字的贡献是其值乘以其出现次数，将这些贡献相加得到结果。
     */
    class Solution {
        /**
         * TreeSet L：维护当前频率最高的 x 个元素（按频率和值排序）。
         * 比较器规则：首先按频率升序，频率相同则按值升序。
         */
        private final TreeSet<int[]> L = new TreeSet<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

        /**
         * TreeSet R：维护其余元素（频率较低的元素），与 L 使用相同的比较器。
         */
        private final TreeSet<int[]> R = new TreeSet<>(L.comparator());

        /**
         * Map cnt：记录每个数值当前的出现次数。
         */
        private final Map<Integer, Integer> cnt = new HashMap<>();

        /**
         * int sumL：记录 TreeSet L 中所有元素的频率*值之和。
         */
        private int sumL = 0;

        /**
         * 计算数组中每个长度为 k 的子数组的 X-sum。
         *
         * @param nums 输入数组
         * @param k    子数组的长度
         * @param x    选取的最多不同数字个数
         * @return 每个子数组对应的 X-sum 结果数组
         */
        public int[] findXSum(int[] nums, int k, int x) {
            int[] ans = new int[nums.length - k + 1];
            for (int r = 0; r < nums.length; r++) {
                // 添加新进入窗口的元素
                int in = nums[r];
                del(in);
                cnt.merge(in, 1, Integer::sum); // 增加该元素的计数
                add(in);

                int l = r + 1 - k;
                if (l < 0) {
                    continue;
                }

                // 维护 L 和 R 的大小关系，确保 L 中有最多 x 个元素
                while (!R.isEmpty() && L.size() < x) {
                    r2l();
                }
                while (L.size() > x) {
                    l2r();
                }
                ans[l] = sumL;

                // 移除滑出窗口的元素
                int out = nums[l];
                del(out);
                cnt.merge(out, -1, Integer::sum); // 减少该元素的计数
                add(out);
            }
            return ans;
        }

        /**
         * 将指定值添加到合适的 TreeSet 中。
         * 如果该值的频率大于 L 中最小元素的频率，则加入 L，否则加入 R。
         *
         * @param val 要添加的值
         */
        private void add(int val) {
            int c = cnt.get(val);
            if (c == 0) {
                return;
            }
            int[] p = new int[]{c, val};
            if (!L.isEmpty() && L.comparator().compare(p, L.first()) > 0) { // p 比 L 中最小的还大
                sumL += (long) p[0] * p[1];
                L.add(p);
            } else {
                R.add(p);
            }
        }

        /**
         * 从 TreeSet 中删除指定值对应的节点。
         *
         * @param val 要删除的值
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
         * 将 L 中频率最小的元素移动到 R。
         */
        private void l2r() {
            int[] p = L.pollFirst();
            sumL -= (long) p[0] * p[1];
            R.add(p);
        }

        /**
         * 将 R 中频率最大的元素移动到 L。
         */
        private void r2l() {
            int[] p = R.pollLast();
            sumL += (long) p[0] * p[1];
            L.add(p);
        }
    }

}