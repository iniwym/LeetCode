package T2505;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 哈希表/二元组+计数
 * @Author: iniwym
 * @Date: 2025-05-04
 * @Link: https://leetcode.cn/problems/number-of-equivalent-domino-pairs/
 */
public class Code1128_NumberOfEquivalentDominoPairs {

    /**
     * 计算等价多米诺对的数量
     * 通过哈希表记录每种多米诺对出现的次数，然后计算每种多米诺对可以形成的等价对数量
     *
     * @param dominoes 二维数组，每个元素为一个长度为2的数组，表示一个多米诺牌
     * @return 返回等价多米诺对的总数
     */
    public static int numEquivDominoPairs1(int[][] dominoes) {
        // 使用哈希表记录每种多米诺对出现的次数
        Map<String, Integer> map = new HashMap();
        // 初始化答案为0
        int ans = 0;
        // 遍历多米诺数组，统计每种多米诺对出现的次数
        for (int[] item : dominoes) {
            // 计算多米诺对的最小值和最大值，确保哈希表中的键是有序的
            int min = Math.min(item[0], item[1]);
            int max = Math.max(item[0], item[1]);
            // 构造哈希表的键
            String str = min + "_" + max;
            // 更新哈希表中对应键的值
            map.put(str, map.getOrDefault(str, 0) + 1);
        }

        // 遍历哈希表，计算每种多米诺对可以形成的等价对数量
        for (Map.Entry<String, Integer> item : map.entrySet()) {
            // 根据组合数公式计算等价对数量，并累加到答案中
            ans += (item.getValue() * (item.getValue() - 1)) / 2;
        }

        // 返回等价多米诺对的总数
        return ans;
    }

    /**
     * 计算等价多米诺对的数量
     * 该方法用于计算在一个多米诺骨牌数组中，有多少对骨牌是等价的，即可以旋转后变得相同
     *
     * @param dominoes 二维数组，表示多米诺骨牌的列表，每个元素是一个包含两个数字的数组
     * @return 返回等价多米诺对的数量
     */
    public static int numEquivDominoPairs(int[][] dominoes) {
        // 初始化一个长度为100的数组，用于存储每个多米诺骨牌数字对出现的次数
        // 之所以长度为100，是因为多米诺骨牌的数字范围是0到9，两位数的最大值是99
        int[] nums = new int[100];
        // 初始化答案变量为0，用于累加等价多米诺对的数量
        int ans = 0;

        // 遍历多米诺骨牌数组，对于每个多米诺骨牌，计算其等价对的数量
        for (int[] item : dominoes) {
            // 计算多米诺骨牌数字对中的较小值和较大值
            // 这样做是为了确保数字对的顺序不会影响到结果，即[1,2]和[2,1]被视为同一对
            int min = Math.min(item[0], item[1]);
            int max = Math.max(item[0], item[1]);

            // 将较小值和较大值组合成一个两位数，较大值在十位，较小值在个位
            // 这样做是为了将数字对映射到nums数组的索引上，以便于统计出现次数
            int temp = max * 10 + min;

            // 将当前数字对出现的次数累加到答案中
            // 这里利用了nums数组中存储的数字对出现次数，直接累加即可
            ans += nums[temp];

            // 将当前数字对的出现次数加一
            // 这样做是为了记录当前数字对已经出现过多少次，为后续的累加做准备
            nums[temp]++;
        }

        // 返回等价多米诺对的总数量
        return ans;
    }

    public static void main(String[] args) {
        int[][] dominoes = {{1, 2}, {2, 1}, {3, 4}, {5, 6}};
        System.out.println(numEquivDominoPairs(dominoes));
    }
}
