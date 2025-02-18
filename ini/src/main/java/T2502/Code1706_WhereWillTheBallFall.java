package T2502;

/**
 * @Description: 二维数组
 * @Author: iniwym
 * @Date: 2025-02-15
 * @Link: https://leetcode.cn/problems/where-will-the-ball-fall/
 */
public class Code1706_WhereWillTheBallFall {

    /**
     * 根据给定的网格模拟球的移动，找出每个球最后的位置
     * 网格中的1表示斜坡向右，-1表示斜坡向左球会根据斜坡的方向移动，如果移动受阻，则停止移动
     *
     * @param grid 二维数组，表示网格的状况，1表示斜坡向右，-1表示斜坡向左
     * @return 返回一个数组，表示每个球最后的位置如果球无法移动，则位置为-1
     */
    public int[] findBall(int[][] grid) {
    
        // 检查网格是否为空，如果为空则返回空数组
        if (grid == null || grid.length == 0) {
            return new int[]{};
        }
    
        // 获取网格的行数和列数
        int m = grid.length;
        int n = grid[0].length;
    
        // 初始化答案数组，用于记录每个球的最后位置
        int[] ans = new int[n];
    
        // 将每个球的初始位置设置为自己的列索引
        for (int i = 0; i < n; i++) {
            ans[i] = i;
        }
    
        // 遍历网格的每一行，模拟球的移动
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 如果球已经无法移动，则跳过
                if (ans[j] == -1) {
                    continue;
                }
                // 获取当前球的位置
                int cnt = ans[j];
                // 根据斜坡的方向更新球的位置
                if (grid[i][cnt] == 1) {
                    // 如果斜坡向右，且右侧有空间，球向右移动
                    if (cnt + 1 < n && grid[i][cnt + 1] == 1) {
                        ans[j] = cnt + 1;
                    } else {
                        // 否则，球无法移动
                        ans[j] = -1;
                    }
                } else {
                    // 如果斜坡向左，且左侧有空间，球向左移动
                    if (cnt - 1 >= 0 && grid[i][cnt - 1] == -1) {
                        ans[j] = cnt - 1;
                    } else {
                        // 否则，球无法移动
                        ans[j] = -1;
                    }
                }
            }
        }
    
        // 返回每个球的最后位置
        return ans;
    
    }

}
