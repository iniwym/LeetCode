package T2505;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author: iniwym
 * @Date: 2025-05-18
 * @Link: https://leetcode.cn/problems/painting-a-grid-with-three-different-colors/
 */
public class Code1931_PaintingAGridWithThreeDifferentColors {

    private static final int MOD = (int) 1e9 + 7;

    /**
     * 给网格图着色的方案数计算方法
     *
     * @param m 网格的行数
     * @param n 网格的列数
     * @return 返回给网格图着色的方案数
     * <p>
     * 本方法通过动态规划算法计算给定尺寸的网格图可以着色的方案数
     * 网格的每一行可以被视为一个状态，我们预处理所有合法的状态以及它们之间的转移关系
     * 使用动态规划数组记录每一列在不同状态下的方案数，最终累加得到总方案数
     */
    public int colorTheGrid(int m, int n) {
        // 生成所有合法的状态列表
        List<int[]> validStates = generateValidStates(m);
        int stateCount = validStates.size();
        if (stateCount == 0) return 0;

        // 预处理状态转移关系：每个状态可转移到哪些后续状态
        List<List<Integer>> transitions = buildTransitions(validStates, m);

        // 动态规划数组，prevDp[i]表示上一行状态i的方案数
        long[] prevDp = new long[stateCount];
        Arrays.fill(prevDp, 1); // 第一行每个合法状态初始为1种

        // 逐列计算动态规划数组
        for (int col = 1; col < n; col++) {
            long[] currDp = new long[stateCount];
            for (int i = 0; i < stateCount; i++) {
                for (int j : transitions.get(i)) {
                    currDp[j] = (currDp[j] + prevDp[i]) % MOD;
                }
            }
            prevDp = currDp;
        }

        // 累加最后一列所有状态的方案数
        long result = 0;
        for (long count : prevDp) {
            result = (result + count) % MOD;
        }
        return (int) result;
    }

    /**
     * 生成所有合法的单行状态（相邻颜色不同）
     *
     * @param m 行中颜色的数量
     * @return 返回一个列表，包含所有合法的状态
     * <p>
     * 该方法通过遍历所有可能的颜色组合来生成合法的单行状态
     * 每个状态表示为一个整数数组，其中每个元素代表一个颜色
     * 相邻颜色不能相同，以确保状态的合法性
     */
    private List<int[]> generateValidStates(int m) {
        List<int[]> validStates = new ArrayList<>();
        // 遍历所有可能的颜色组合
        for (int num = 0; num < (int) Math.pow(3, m); num++) {
            int[] colors = new int[m];
            int temp = num;
            boolean valid = true;
            // 将当前数字转换为三进制表示，并检查相邻颜色是否相同
            for (int i = 0; i < m; i++) {
                colors[i] = temp % 3;
                temp /= 3;
                // 如果相邻颜色相同，则该状态无效
                if (i > 0 && colors[i] == colors[i - 1]) {
                    valid = false;
                    break;
                }
            }
            // 如果状态有效，则添加到结果列表中
            if (valid) {
                validStates.add(colors);
            }
        }
        // 返回所有合法的状态
        return validStates;
    }


    /**
     * 构建状态转移表：每个状态i可转移到哪些状态j
     * 此方法用于构建一个列表，表示每个状态可以转移到的其他状态的索引
     * 它通过比较状态之间的差异来确定哪些状态是彼此兼容的，从而可以相互转移
     *
     * @param validStates 有效状态列表，每个状态是一个int数组
     * @param m           每个状态数组的长度，用于比较状态之间的差异
     * @return 返回一个列表的列表，其中每个元素是一个整数列表，表示可以转移到的状态的索引
     */
    private List<List<Integer>> buildTransitions(List<int[]> validStates, int m) {
        // 初始化状态转移表
        List<List<Integer>> transitions = new ArrayList<>();
        // 遍历所有状态，构建转移表
        for (int i = 0; i < validStates.size(); i++) {
            // 获取当前状态s1
            int[] s1 = validStates.get(i);
            // 初始化一个列表，用于存储与当前状态s1兼容的状态s2的索引
            List<Integer> list = new ArrayList<>();
            // 再次遍历所有状态，寻找与s1兼容的状态s2
            for (int j = 0; j < validStates.size(); j++) {
                // 获取比较状态s2
                int[] s2 = validStates.get(j);
                // 初始化一个标志，用于判断s1和s2是否兼容
                boolean compatible = true;
                // 比较s1和s2的每个元素，判断它们是否兼容
                for (int k = 0; k < m; k++) {
                    // 如果s1和s2的第k个元素相同，则它们不兼容
                    if (s1[k] == s2[k]) {
                        compatible = false;
                        break;
                    }
                }
                // 如果s1和s2兼容，则将s2的索引添加到列表中
                if (compatible) {
                    list.add(j);
                }
            }
            // 将与当前状态s1兼容的所有状态的索引列表添加到转移表中
            transitions.add(list);
        }
        // 返回构建完成的状态转移表
        return transitions;
    }

}
