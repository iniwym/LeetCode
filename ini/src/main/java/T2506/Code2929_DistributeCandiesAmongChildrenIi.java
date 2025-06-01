package T2506;

/**
 * @Description: 枚举
 * @Author: iniwym
 * @Date: 2025-06-01
 * @Link: https://leetcode.cn/problems/distribute-candies-among-children-ii/
 */
public class Code2929_DistributeCandiesAmongChildrenIi {

    /**
     * 分发糖果的方法，旨在计算将n个糖果分给至少一个人，且每个人不超过limit个糖果的分配方案数
     *
     * @param n     总糖果数
     * @param limit 每人糖果数的上限
     * @return 返回所有可能的分配方案数
     */
    public long distributeCandies(int n, int limit) {
        // 初始化分配方案数为0
        long ans = 0;

        // 遍历第一人可能获得的糖果数，从最多可能的糖果数开始，直到至少分一个糖果
        for (int i = Math.max(0, n - 2 * limit); i <= Math.min(n, limit); i++) {
            // 计算在第一人获得i个糖果的情况下，第二人和第三人分配糖果的可能方案数
            // 这里使用了数学方法来计算在当前第一人糖果数固定时，剩余糖果分配的可能范围
            ans += Math.min(n - i, limit) - Math.max(0, n - i - limit) + 1;
        }

        // 返回总的分配方案数
        return ans;
    }

}
