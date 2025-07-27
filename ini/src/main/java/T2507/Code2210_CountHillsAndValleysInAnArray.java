package T2507;

/**
 * @Description: 去重
 * @Author: iniwym
 * @Date: 2025-07-27
 * @Link: https://leetcode.cn/problems/count-hills-and-valleys-in-an-array/
 */
public class Code2210_CountHillsAndValleysInAnArray {
    /**
     * 计算数组中山峰和山谷的数量
     * 山峰：某个元素比相邻的前一个元素和后一个元素都大
     * 山谷：某个元素比相邻的前一个元素和后一个元素都小
     *
     * @param nums 输入的整数数组
     * @return 返回山峰和山谷的总数量
     */
    public int countHillValley(int[] nums) {
        // 删除有序数组中的重复项
        int k = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) { // nums[i] 不是重复项
                nums[k] = nums[i]; // 保留 nums[i]
                k++;
            }
        }

        // 遍历去重后的数组，统计山峰和山谷的数量
        // 判断条件：(nums[i-1] < nums[i]) == (nums[i] > nums[i+1])
        // 这个条件同时涵盖了山峰(nums[i-1] < nums[i] && nums[i] > nums[i+1])和
        // 山谷(nums[i-1] > nums[i] && nums[i] < nums[i+1])两种情况
        int ans = 0;
        for (int i = 1; i < k - 1; i++) {
            if ((nums[i - 1] < nums[i]) == (nums[i] > nums[i + 1])) {
                ans++;
            }
        }
        return ans;
    }

}
