package T2510;

import java.util.List;

/**
 * @Description: 递增子数组
 * @Author: iniwym
 * @Date: 2025-10-14
 * @Link: https://leetcode.cn/problems/adjacent-increasing-subarrays-detection-i/
 */
public class Code3349_AdjacentIncreasingSubarraysDetectionI {

    /**
     * 判断数组中是否存在两个长度为k的不相交的严格递增子数组
     *
     * @param nums 整数列表
     * @param k    子数组的长度
     * @return 如果存在两个不相交的长度为k的严格递增子数组则返回true，否则返回false
     */
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int ans = 0;
        int preCnt = 0;
        int cnt = 0;
        for (int i = 0; i < nums.size(); i++) {
            cnt++;
            // 找到一个严格递增段的结束位置，计算可以形成的最长符合条件的子数组
            if (i == nums.size() - 1 || nums.get(i) >= nums.get(i + 1)) {
                // 计算当前递增段能形成的最大答案：当前段长度的一半 或者 前一段和当前段的最小值
                ans = Math.max(ans, Math.max(cnt / 2, Math.min(preCnt, cnt)));
                preCnt = cnt;
                cnt = 0;
            }
        }
        return ans >= k;

    }

}
