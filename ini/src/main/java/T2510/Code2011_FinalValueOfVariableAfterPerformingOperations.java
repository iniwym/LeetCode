package T2510;

/**
 * @Description: 字符串遍历
 * @Author: iniwym
 * @Date: 2025-10-20
 * @Link: https://leetcode.cn/problems/final-value-of-variable-after-performing-operations/
 */
public class Code2011_FinalValueOfVariableAfterPerformingOperations {

    /**
     * 计算执行一系列操作后变量X的最终值
     *
     * @param operations 操作字符串数组，每个元素表示一个操作：
     *                   "--X" 或 "X--" 表示将X减1，
     *                   "++X" 或 "X++" 表示将X加1
     * @return 执行所有操作后X的最终值
     */
    public int finalValueAfterOperations(String[] operations) {
        int x = 0;

        // 遍历所有操作，根据操作类型增减X的值
        for (String str : operations) {

            if ("--X".equals(str) || "X--".equals(str)) {
                x--;
            } else {
                x++;
            }
        }
        return x;
    }
}
