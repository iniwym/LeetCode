package T2504;

/**
 * @Description: 暴力法/贪心
 * @Author: iniwym
 * @Date: 2025-04-02
 * @Link: https://leetcode.cn/problems/maximum-value-of-an-ordered-triplet-i/
 */
public class Code2873_MaximumValueOfAnOrderedTripletI {
    /**
     * 计算数组中所有可能的三元组 (i, j, k) 的最大值，其中 i < j < k。
     * 最大值定义为 (nums[i] - nums[j]) * nums[k] 的最大值。
     *
     * @param nums 输入的整数数组
     * @return 计算得到的最大值
     */
    public long maximumTripletValue(int[] nums) {
        long ans = 0;
        int n = nums.length;
        // 遍历第一个元素i的位置，确保后面有足够的元素形成三元组
        for (int i = 0; i <= n - 3; i++) {
            // 遍历第二个元素j的位置，在i之后，并留出空间给k
            for (int j = i + 1; j <= n - 2; j++) {
                // 遍历第三个元素k的位置，在j之后
                for (int k = j + 1; k <= n - 1; k++) {
                    ans = Math.max(ans, (long) (nums[i] - nums[j]) * nums[k]);
                }
            }
        }
        return ans;
    }

    /**
     * 计算数组中所有可能的i<j<k三元组的最大值，其中值为(nums[i] - nums[j]) * nums[k]。
     * 通过维护前j个元素中的最大值来优化计算过程。
     *
     * @param nums 输入的整数数组
     * @return 最大的三元组值，类型为long
     */
    public long maximumTripletValue2(int[] nums) {
        long ans = 0;
        int n = nums.length;
        for (int k = 2; k <= n - 1; k++) { // 遍历第三个元素k的可能位置
            int m = nums[0]; // 初始化前j个元素中的最大值为第一个元素
            for (int j = 1; j <= k - 1; j++) { // 遍历第二个元素j的可能位置
                ans = Math.max(ans, (long) (m - nums[j]) * nums[k]);
                m = Math.max(m, nums[j]); // 更新前j个元素的最大值
            }
        }
        return ans;
    }

}
