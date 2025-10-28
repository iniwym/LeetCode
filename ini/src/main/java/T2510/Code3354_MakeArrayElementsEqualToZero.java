package T2510;

/**
 * @Description: 前缀和
 * @Author: iniwym
 * @Date: 2025-10-28
 * @Link: https://leetcode.cn/problems/make-array-elements-equal-to-zero/
 */
public class Code3354_MakeArrayElementsEqualToZero {

    /**
     * 计算有效选择的数量
     *
     * @param nums 输入的整数数组
     * @return 有效选择的数量
     */
    public int countValidSelections(int[] nums) {
        // 计算数组元素总和
        int total = 0;
        for (int x : nums) {
            total += x;
        }

        int ans = 0;
        int pre = 0;
        // 遍历数组，根据特定条件计算有效选择数
        for (int x : nums) {
            if (x > 0) {
                pre += x;
            } else if (pre * 2 == total) {
                ans += 2;
            } else if (Math.abs(pre * 2 - total) == 1) {
                ans++;
            }
        }
        return ans;
    }

}

