package T2507;

import java.util.Arrays;

/**
 * @Description: 双指针
 * @Author: iniwym
 * @Date: 2025-07-13
 * @Link: https://leetcode.cn/problems/maximum-matching-of-players-with-trainers/
 */
public class Code2410_MaximumMatchingOfPlayersWithTrainers {

    /**
     * 匹配玩家和教练，计算最多可以匹配的玩家数量。
     * 玩家和教练的匹配规则：玩家的能力值必须小于等于教练的能力值，每个玩家和教练只能匹配一次。
     *
     * @param players  玩家的能力值数组，每个元素代表一个玩家的能力值
     * @param trainers 教练的能力值数组，每个元素代表一个教练的能力值
     * @return 最多可以匹配的玩家数量
     */
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        // 对玩家和教练的能力值数组进行升序排序
        // 排序后可以使用贪心算法进行最优匹配
        Arrays.sort(players);
        Arrays.sort(trainers);

        int ans = 0;

        /**
         * 使用双指针策略进行匹配：
         * i指针遍历玩家数组，j指针遍历教练数组
         * 当找到满足条件的教练时，两个指针都前进
         * 否则只前进教练指针寻找能力更强的教练
         */
        for (int i = 0, j = 0; i < players.length && j < trainers.length; j++) {
            if (players[i] <= trainers[j]) {
                // 成功匹配一对玩家和教练
                ans++;
                i++;
            }
        }

        return ans;
    }

}
