package T2602;

/**
 * @Description: 统计
 * @Author: iniwym
 * @Date: 2026-02-03
 * @Link: https://leetcode.cn/problems/trionic-array-i/
 */
public class Code3637_TrionicArrayI {

    /**
     * 判断给定的整数数组是否满足“三段式”条件。
     * “三段式”定义为：数组必须严格递增、然后严格递减、再严格递增，且整个过程中不能有相等的元素。
     *
     * @param nums 输入的整数数组
     * @return 如果数组满足“三段式”条件则返回 true，否则返回 false
     */
    public boolean isTrionic(int[] nums) {
        // 检查初始两个元素是否递增，如果不满足则直接返回 false
        if (nums[0] >= nums[1]) {
            return false;
        }

        // 记录变化次数（从递增到递减或从递减到递增）
        int cnt = 1;

        // 遍历数组，检查相邻元素之间的变化趋势
        for (int i = 2; i < nums.length; i++) {
            // 如果存在相等的相邻元素，则不满足条件
            if (nums[i - 1] == nums[i]) {
                return false;
            }

            // 检查当前元素与前一个元素的变化趋势是否与前一个元素和更前一个元素的趋势相反
            if ((nums[i - 2] < nums[i - 1]) != (nums[i - 1] < nums[i])) {
                cnt++;
            }
        }

        // 最终必须恰好发生三次变化（增-减-增），否则返回 false
        return cnt == 3;
    }


}
