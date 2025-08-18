package T2508;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 回溯
 * @Author: iniwym
 * @Date: 2025-08-18
 * @Link: https://leetcode.cn/problems/24-game/
 */
public class Code0679_24Game {

    /**
     * 判断给定的4张牌是否能通过四则运算得到24点
     *
     * @param cards 长度为4的整数数组，表示4张牌的点数，每张牌点数在1-9之间
     * @return 如果能通过四则运算得到24点则返回true，否则返回false
     */
    public boolean judgePoint24(int[] cards) {
        List<Double> list = new ArrayList<>();
        for (int card : cards) {
            list.add((double) card);
        }
        return backtrack(list);
    }

    /**
     * 使用回溯法尝试所有可能的运算组合，判断是否能得到24点
     *
     * @param list 当前待运算的数字列表
     * @return 如果能通过运算得到24点则返回true，否则返回false
     */
    private boolean backtrack(List<Double> list) {
        // 递归终止条件：当列表中只剩一个数字时，判断该数字是否接近24
        if (list.size() == 1) {
            return Math.abs(list.get(0) - 24) < 1e-6;
        }

        // 从列表中任选两个不同的数字进行运算
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                // 构造下一轮递归的数字列表（移除已选的两个数字）
                List<Double> nextList = new ArrayList<>();
                for (int k = 0; k < list.size(); k++) {
                    if (k != i && k != j) {
                        nextList.add(list.get(k));
                    }
                }

                double a = list.get(i);
                double b = list.get(j);

                // 获取两个数字进行四则运算的所有可能结果
                List<Double> computed = compute(a, b);
                for (double res : computed) {
                    nextList.add(res);
                    if (backtrack(nextList)) {
                        return true;
                    }
                    nextList.remove(nextList.size() - 1);
                }
            }
        }
        return false;
    }

    /**
     * 计算两个数字进行四则运算的所有可能结果
     *
     * @param a 第一个数字
     * @param b 第二个数字
     * @return 包含所有可能运算结果的列表
     */
    private List<Double> compute(double a, double b) {
        List<Double> resList = new ArrayList<>();
        resList.add(a + b);  // 加法
        resList.add(a * b);  // 乘法
        resList.add(a - b);  // 减法（a-b）
        resList.add(b - a);  // 减法（b-a）
        // 除法运算需要避免除数为0的情况
        if (Math.abs(b) > 1e-6) {
            resList.add(a / b);
        }
        if (Math.abs(a) > 1e-6) {
            resList.add(b / a);
        }
        return resList;
    }

}
