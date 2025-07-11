package T2507;

import java.util.Arrays;

/**
 * @Description: 合并区间
 * @Author: iniwym
 * @Date: 2025-07-11
 * @Link: https://leetcode.cn/problems/count-days-without-meetings/
 */
public class Code3169_CountDaysWithoutMeetings {

    /**
     * 计算在给定天数中，未被会议占用的天数。
     *
     * @param days     总天数，从第1天开始计算
     * @param meetings 会议时间区间数组，每个区间表示为[start, end]，包含start和end当天
     * @return 未被会议占用的天数
     */
    public int countDays(int days, int[][] meetings) {
        // 将会议区间按照开始时间升序排序，便于后续合并处理
        Arrays.sort(meetings, (p, q) -> p[0] - q[0]);

        // 初始化合并区间的左右端点
        int start = 1, end = 0;

        // 遍历所有会议区间，合并重叠或相邻的区间
        for (int[] p : meetings) {
            if (p[0] > end) {
                // 当前会议区间与合并区间无重叠，先扣除合并区间占用的天数
                days -= end - start + 1;
                // 重置合并区间的起始点为当前会议的开始时间
                start = p[0];
            }
            // 更新合并区间的结束时间为最远的结束时间
            end = Math.max(end, p[1]);
        }

        // 扣除最后一个合并区间占用的天数
        days -= end - start + 1;
        return days;
    }

}
