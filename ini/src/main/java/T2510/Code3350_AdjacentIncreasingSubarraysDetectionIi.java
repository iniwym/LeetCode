package T2510;

import java.util.List;

/**
 * @Description: 递增子数组
 * @Author: iniwym
 * @Date: 2025-10-16
 * @Link: https://leetcode.cn/problems/adjacent-increasing-subarrays-detection-ii/
 */
public class Code3350_AdjacentIncreasingSubarraysDetectionIi {

    /**
     * 计算数组中两个不重叠的严格递增子数组的最大长度和
     *
     * @param nums 输入的整数列表
     * @return 两个不重叠的严格递增子数组的最大长度和
     */
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int ans = 0;
        int preCnt = 0;
        int cnt = 0;
        for (int i = 0; i < nums.size(); i++) {
            cnt++;
            // 找到严格递增段的末尾，计算该段可以分割成两个子数组的最大长度
            if (i == nums.size() - 1 || nums.get(i) >= nums.get(i + 1)) {
                ans = Math.max(ans, Math.max(cnt / 2, Math.min(preCnt, cnt)));
                preCnt = cnt;
                cnt = 0;
            }
        }
        return ans;
    }
}
