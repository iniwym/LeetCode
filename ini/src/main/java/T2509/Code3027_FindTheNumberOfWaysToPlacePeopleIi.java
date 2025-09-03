package T2509;

import java.util.Arrays;

/**
 * @Description: 排序
 * @Author: iniwym
 * @Date: 2025-09-03
 * @Link: https://leetcode.cn/problems/find-the-number-of-ways-to-place-people-ii/
 */
public class Code3027_FindTheNumberOfWaysToPlacePeopleIi {

    /**
     * 计算点对的数量，使得两个点可以形成一个有效的矩形区域（左上角和右下角）
     *
     * @param points 二维数组，每个元素是一个包含两个整数的数组，表示点的坐标 [x, y]
     * @return 返回满足条件的点对数量
     */
    public int numberOfPairs(int[][] points) {
        // 按照x坐标升序排列，如果x坐标相同则按照y坐标降序排列
        Arrays.sort(points, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        int ans = 0;

        // 遍历每个点作为可能的左上角点
        for (int i = 0; i < points.length; i++) {
            int y1 = points[i][1];
            int maxY = Integer.MIN_VALUE;

            // 查找可以作为右下角的点
            for (int j = i + 1; j < points.length && maxY < y1; j++) {
                int y2 = points[j][1];
                // 如果当前点的y坐标小于等于左上角点且大于已找到的最大y坐标
                if (y2 <= y1 && y2 > maxY) {
                    maxY = y2;
                    ans++;
                }
            }
        }
        return ans;
    }

}
