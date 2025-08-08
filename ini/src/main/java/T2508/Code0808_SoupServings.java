package T2508;

/**
 * @Description: DP
 * @Author: iniwym
 * @Date: 2025-08-08
 * @Link: https://leetcode.cn/problems/soup-servings/
 */
public class Code0808_SoupServings {

    /**
     * 计算汤品服务概率
     *
     * @param n 初始汤A和汤B的毫升数
     * @return 汤A先分配完的概率加上汤A和汤B同时分配完概率的一半
     */
    public double soupServings(int n) {
        // 当n大于等于4451时，概率趋近于1，直接返回1
        if (n >= 4451) {
            return 1;
        }

        // 将毫升数转换为份数，每25毫升为1份，向上取整
        n = (n + 24) / 25;
        // 创建记忆化数组，用于存储已计算过的状态结果
        double[][] memo = new double[n + 1][n + 1];
        // 调用深度优先搜索计算概率
        return dfs(n, n, memo);
    }


    /**
     * 使用深度优先搜索计算概率值
     *
     * @param a    第一个状态参数，表示某种资源或计数
     * @param b    第二个状态参数，表示另一种资源或计数
     * @param memo 备忘录数组，用于存储已计算的结果避免重复计算
     * @return 返回在当前状态(a, b)下的概率值
     */
    private double dfs(int a, int b, double[][] memo) {
        // 边界条件：当a和b都小于等于0时，返回0.5
        if (a <= 0 && b <= 0) {
            return 0.5;
        }
        // 边界条件：当a小于等于0但b大于0时，返回1
        if (a <= 0) {
            return 1;
        }
        // 边界条件：当b小于等于0但a大于0时，返回0
        if (b <= 0) {
            return 0;
        }
        // 如果当前状态未计算过，则进行递归计算
        if (memo[a][b] == 0) { // 没有计算过
            memo[a][b] = (dfs(a - 4, b, memo) + dfs(a - 3, b - 1, memo) + dfs(a - 2, b - 2, memo) + dfs(a - 1, b - 3, memo)) / 4;
        }
        return memo[a][b];
    }


}
