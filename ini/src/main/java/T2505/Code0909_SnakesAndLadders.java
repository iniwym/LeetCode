package T2505;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;

/**
 * @Description: 广度优先搜索（BFS）
 * @Author: iniwym
 * @Date: 2025-05-31
 * @Link: https://leetcode.cn/problems/snakes-and-ladders/
 */
public class Code0909_SnakesAndLadders {

    /**
     * 解决Snakes and Ladders游戏的最少移动次数问题
     * 该方法使用广度优先搜索（BFS）算法来找到从起点到终点的最短路径
     *
     * @param board 二维数组表示的游戏板，其中-1表示普通格子，其他正数表示蛇尾或梯子头的位置
     * @return 返回到达终点的最少移动次数，如果无法到达终点则返回-1
     */
    public int snakesAndLadders(int[][] board) {
        // 获取游戏板的边长
        int n = board.length;
        // 计算游戏板的总格数
        int total = n * n;
        // 初始化距离数组，用于记录每个格子到起点的最短距离
        int[] dist = new int[total + 1];
        // 将所有距离初始化为-1，表示尚未计算
        Arrays.fill(dist, -1);
        // 使用队列来实现广度优先搜索
        Queue<Integer> queue = new LinkedList<>();
        // 设置起点到起点的距离为0
        dist[1] = 0;
        // 将起点加入队列
        queue.offer(1);

        // 当队列不为空时，继续进行广度优先搜索
        while (!queue.isEmpty()) {
            // 从队列中取出当前格子的编号
            int curr = queue.poll();
            // 尝试移动1到6步
            for (int step = 1; step <= 6; step++) {
                // 计算移动后到达的下一个格子的编号
                int next = curr + step;
                // 如果超过游戏板，则忽略这次移动
                if (next > total) continue;
                // 将下一个格子的编号转换为游戏板上的行列坐标
                int[] pos = numToPos(next, n);
                int row = pos[0];
                int col = pos[1];
                // 获取下一个格子的值，可能是蛇尾、梯子头或普通格子
                int destination = board[row][col];
                // 如果是普通格子，则保持当前计算的下一个格子编号
                if (destination == -1) {
                    destination = next;
                } else {
                    // 如果是蛇尾或梯子头，则直接跳转到对应的位置
                    destination = destination;
                }
                // 如果到达终点，则返回从起点到终点的移动次数
                if (destination == total) {
                    return dist[curr] + 1;
                }
                // 如果下一个格子的距离尚未计算，则更新其距离并加入队列
                if (dist[destination] == -1) {
                    dist[destination] = dist[curr] + 1;
                    queue.offer(destination);
                }
            }
        }
        // 如果无法到达终点，返回-1
        return -1;
    }

    /**
     * 将一个数字转换为在n*n矩阵中的位置
     * 此方法用于计算一个数字在特定规则下的行和列位置
     * 规则是：数字按从左到右、从下到上、到达边缘后反弹的方式排列
     *
     * @param num 要转换的数字
     * @param n   矩阵的大小，即行数或列数
     * @return 一个包含两个整数的数组，表示数字在矩阵中的行和列位置
     */
    private int[] numToPos(int num, int n) {
        // 计算数字所在的行，使用数学方式确定位置
        int r = (num - 1) / n;
        // 由于数字是从下往上排列，所以行号需要反转
        int row = n - 1 - r;
        // 计算数字在当前行内的位置
        int c_in_group = (num - 1) % n;
        // 如果行号是奇数，数字的排列顺序需要反转
        if (r % 2 == 1) {
            c_in_group = n - 1 - c_in_group;
        }
        // 返回计算出的行和列位置
        return new int[]{row, c_in_group};
    }

}
