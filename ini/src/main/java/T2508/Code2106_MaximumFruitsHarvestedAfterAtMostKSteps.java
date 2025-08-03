package T2508;

/**
 * @Description: 滑动窗口
 * @Author: iniwym
 * @Date: 2025-08-03
 * @Link: https://leetcode.cn/problems/maximum-fruits-harvested-after-at-most-k-steps/
 */
public class Code2106_MaximumFruitsHarvestedAfterAtMostKSteps {

    class Solution {
        /**
         * 计算在给定步数限制下能摘到的最大水果总数
         *
         * @param fruits   二维数组，fruits[i] = [positioni, amounti] 表示在位置 positioni 有 amounti 个水果
         * @param startPos 起始位置
         * @param k        最多可以移动的步数
         * @return 在最多移动 k 步的情况下能摘到的最大水果总数
         */
        public int maxTotalFruits(int[][] fruits, int startPos, int k) {
            int n = fruits.length;
            int left = lowerBound(fruits, startPos - k); // 向左最远能到 fruits[left][0]

            int right = left;
            // 从 fruits[left][0] 到 startPos 的水果数
            int s = 0;
            for (; right < n && fruits[right][0] <= startPos; right++) {
                s += fruits[right][1];
            }

            int ans = s;
            // 枚举最右走到 fruits[right][0]
            for (; right < n && fruits[right][0] <= startPos + k; right++) {
                s += fruits[right][1];
                // 判断当前窗口是否超出步数限制，如果超出则移动左边界
                while (fruits[right][0] * 2 - fruits[left][0] - startPos > k &&
                        fruits[right][0] - fruits[left][0] * 2 + startPos > k) {
                    s -= fruits[left][1]; // fruits[left][0] 太远了
                    left++;
                }
                ans = Math.max(ans, s); // 更新答案最大值
            }
            return ans;
        }

        /**
         * 在二维数组fruits中查找第一个大于等于target的位置（下界）
         * 使用二分查找在有序数组中寻找目标值的下界位置
         *
         * @param fruits 二维数组，其中fruits[i][0]表示位置坐标，数组按位置坐标升序排列
         * @param target 目标位置坐标
         * @return 返回第一个大于等于target的元素在数组中的索引位置
         */
        private int lowerBound(int[][] fruits, int target) {
            int left = -1;
            int right = fruits.length; // 开区间 (left, right)
            while (left + 1 < right) { // 开区间不为空
                // 循环不变量：
                // fruits[left][0] < target
                // fruits[right][0] >= target
                int mid = left + (right - left) / 2;
                if (fruits[mid][0] < target) {
                    left = mid; // 范围缩小到 (mid, right)
                } else {
                    right = mid; // 范围缩小到 (left, mid)
                }
            }
            return right;
        }
    }

}
