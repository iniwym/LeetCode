package T2502;

import java.util.Arrays;

/**
 * @Description: 二分查找法
 * @Author: iniwym
 * @Date: 2025-02-14
 * @Link: https://leetcode.cn/problems/magnetic-force-between-two-balls/
 */
public class Code1552_MagneticForceBetweenTwoBalls {

    /**
     * 计算在给定位置数组中，放置m个球的最大距离
     * 通过二分查找方法优化搜索最大距离的过程
     *
     * @param position 给定的位置数组
     * @param m        需要放置的球的数量
     * @return 最大距离如果无法放置m个球则返回-1
     */
    public int maxDistance(int[] position, int m) {
        // 检查数组长度是否小于球的数量，如果是则无法放置，返回-1
        if (position.length < m) {
            return -1;
        }

        // 对位置数组进行排序，以便后续处理
        Arrays.sort(position);
        // 初始化二分查找的左右边界
        int l = 1, r = position[position.length - 1] - position[0];

        // 初始化最终答案为0
        int ans = 0;
        // 通过二分查找确定最大距离
        while (l <= r) {
            // 计算中间值
            int mid = l + (r - l) / 2;
            // 检查当前距离是否可行
            boolean flag = check(position, mid, m);
            if (flag) {
                // 如果可行，更新答案并调整左边界
                ans = mid;
                l = mid + 1;
            } else {
                // 如果不可行，调整右边界
                r = mid - 1;
            }
        }

        // 返回最大距离
        return ans;
    }

    /**
     * 检查给定距离是否可以在位置数组中放置m个球
     *
     * @param position 给定的位置数组
     * @param mid      当前检查的距离
     * @param m        需要放置的球的数量
     * @return 如果可以放置m个球返回true，否则返回false
     */
    private boolean check(int[] position, int mid, int m) {
        // 初始化已放置球的计数
        int count = 1;
        // 初始化上一个球的位置
        int lastPosition = position[0];
        // 遍历位置数组，检查是否可以放置球
        for (int i = 1; i < position.length; i++) {
            // 如果当前位置与上一个球的位置的距离大于等于当前检查的距离
            if (position[i] - lastPosition >= mid) {
                // 增加已放置球的计数
                count++;
                // 更新上一个球的位置
                lastPosition = position[i];
            }
        }
        // 返回是否可以放置m个球
        return count >= m;
    }
}
