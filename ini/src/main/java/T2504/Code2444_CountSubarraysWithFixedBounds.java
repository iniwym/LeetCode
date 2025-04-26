package T2504;

/**
 * @Description: 分割数组
 * @Author: iniwym
 * @Date: 2025-04-26
 * @Link: https://leetcode.cn/problems/count-subarrays-with-fixed-bounds/
 */
public class Code2444_CountSubarraysWithFixedBounds {
    /**
     * 统计满足定界条件的子数组数目。
     *
     * @param nums 输入的整数数组
     * @param minK 子数组的最小值目标值
     * @param maxK 子数组的最大值目标值
     * @return 符合条件的定界子数组的总数
     */
    public static long countSubarrays(int[] nums, int minK, int maxK) {
        long ans = 0;
        int left = -1, lastMin = -1, lastMax = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < minK || nums[i] > maxK) {
                lastMin = -1;
                lastMax = -1;
                left = i;
            }
            // 当前元素超出[minK, maxK]范围，重置最近的min和max位置，并更新左边界
            // 因为任何包含该元素的子数组都不符合条件
            if (nums[i] == minK) {
                lastMin = i;
            }
            if (nums[i] == maxK) {
                lastMax = i;
            }
            // 记录最近出现的minK和maxK的位置

            if (lastMin != -1 && lastMax != -1) {
                // 当同时存在有效的min和max时，计算可添加的子数组数目
                // 最小有效起始位置为left+1，最大有效起始位置为min(lastMin, lastMax)
                ans += Math.min(lastMin, lastMax) - left;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 2, 7, 5};
        int minK = 1;
        int maxK = 5;
        long result = countSubarrays(nums, minK, maxK);
        System.out.println(result);
    }

}
