package T2509;

import java.util.Arrays;

/**
 * @Description: 双向指针
 * @Author: iniwym
 * @Date: 2025-09-26
 * @Link: https://leetcode.cn/problems/valid-triangle-number/
 */
public class Code0611_ValidTriangleNumber {

    /**
     * 计算数组中能构成三角形的三元组个数
     * <p>
     * 解题思路：
     * 1. 先对数组进行排序
     * 2. 固定最大边，使用双指针法查找满足三角形不等式的另外两边
     * 3. 对于排序后的数组，只需检查 a + b > c（其中 c 是最大边）
     *
     * @param nums 输入的整数数组
     * @return 能构成三角形的三元组个数
     */
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        // 从第三个元素开始，固定最大边
        for (int k = 2; k < nums.length; k++) {
            int c = nums[k];
            int i = 0; // a=nums[i]
            int j = k - 1; // b=nums[j]
            // 双指针法查找满足条件的三元组
            while (i < j) {
                if (nums[i] + nums[j] > c) {
                    // 当前组合满足三角形不等式
                    // 由于数组已排序，固定 j 时，从 i 到 j-1 的所有元素与 j 组合都满足条件
                    ans += j - i;
                    j--;
                } else {
                    // 当前组合不满足三角形不等式
                    // 由于数组已排序，固定 i 时，j 及其之前的元素都无法满足条件
                    i++;
                }
            }
        }
        return ans;
    }

}
