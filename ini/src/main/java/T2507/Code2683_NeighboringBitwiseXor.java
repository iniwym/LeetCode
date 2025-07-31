package T2507;

/**
 * @Description: 异或
 * @Author: iniwym
 * @Date: 2025-07-31
 * @Link: https://leetcode.cn/problems/neighboring-bitwise-xor/
 */
public class Code2683_NeighboringBitwiseXor {

    /**
     * 判断是否存在有效的原始数组，使得根据该数组计算出的派生数组等于给定的derived数组
     *
     * @param derived 给定的派生数组，其中derived[i] = original[i] XOR original[i+1] (i < n-1),
     *                derived[n-1] = original[n-1] XOR original[0]
     * @return 如果存在满足条件的原始数组则返回true，否则返回false
     */
    public boolean doesValidArrayExist(int[] derived) {
        boolean ans = false;
        int n = derived.length;

        // 处理特殊情况
        if (n == 1) {
            return derived[0] == 0;
        }
        if (n == 2) {
            return derived[0] == derived[n - 1];
        }

        // 尝试第一种情况：假设original[0] = 1
        // 根据异或运算的性质，从derived[0]到derived[n-2]可以推导出original[1]到original[n-1]
        // 然后验证derived[n-1]是否等于original[n-1] XOR original[0]
        int temp = 1;
        for (int i = 0; i < n - 1; i++) {
            temp ^= derived[i];
        }
        ans = derived[n - 1] == (1 ^ temp);

        // 如果第一种情况不成立，尝试第二种情况：假设original[0] = 0
        if (!ans) {
            temp = 0;
            for (int i = 0; i < n - 1; i++) {
                temp ^= derived[i];
            }
            ans = derived[n - 1] == (0 ^ temp);
        }

        return ans;
    }

}
