package T2602;

/**
 * @Description: 分组循环
 * @Author: iniwym
 * @Date: 2026-02-04
 * @Link: https://leetcode.cn/problems/trionic-array-ii/
 */
public class Code3640_TrionicArrayIi {

    /**
     * 计算数组中满足特定条件的“极大三段式”子数组的最大元素和。
     * <p>
     * “极大三段式”子数组定义如下：
     * 1. 第一段：严格递增的连续子数组，至少包含两个元素；
     * 2. 第二段：严格递减的连续子数组，至少包含两个元素；
     * 3. 第三段：严格递增的连续子数组，至少包含两个元素；
     * <p>
     * 算法通过遍历数组，依次寻找满足上述条件的三段结构，并计算其最大元素和。
     *
     * @param nums 输入的整数数组
     * @return 满足条件的“极大三段式”子数组的最大元素和；如果不存在符合条件的子数组，则返回 Long.MIN_VALUE
     */
    public long maxSumTrionic(int[] nums) {
        int n = nums.length;
        long ans = Long.MIN_VALUE;

        // 遍历数组，寻找所有可能的“极大三段式”子数组
        for (int i = 0; i < n; ) {
            // 第一段：寻找严格递增的连续子数组
            int start = i;
            for (i++; i < n && nums[i - 1] < nums[i]; i++) ;
            if (i == start + 1) { // 第一段至少需要两个元素
                continue;
            }

            // 第二段：寻找严格递减的连续子数组
            int peak = i - 1;
            long res = nums[peak - 1] + nums[peak]; // 第一段的最后两个元素必须被选中
            for (; i < n && nums[i - 1] > nums[i]; i++) {
                res += nums[i]; // 第二段的所有元素都必须被选中
            }
            if (i == peak + 1 || i == n || nums[i - 1] == nums[i]) { // 第二段和第三段各自至少需要两个元素
                continue;
            }

            // 第三段：寻找严格递增的连续子数组
            int bottom = i - 1;
            res += nums[i]; // 第三段的前两个元素必须被选中（第一个已在上一循环中加入）

            // 从第三段的第三个元素开始向右扩展，计算最大元素和
            long maxS = 0;
            long s = 0;
            for (i++; i < n && nums[i - 1] < nums[i]; i++) {
                s += nums[i];
                maxS = Math.max(maxS, s);
            }
            res += maxS;

            // 从第一段的倒数第三个元素开始向左扩展，计算最大元素和
            maxS = 0;
            s = 0;
            for (int j = peak - 2; j >= start; j--) {
                s += nums[j];
                maxS = Math.max(maxS, s);
            }
            res += maxS;

            // 更新全局最大值
            ans = Math.max(ans, res);

            // 将当前第三段的起始位置作为下一次搜索的起点
            i = bottom;
        }

        return ans;
    }

}
