package T2504;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 哈希表
 * @Author: iniwym
 * @Date: 2025-04-20
 * @Link: https://leetcode.cn/problems/rabbits-in-forest/
 */
public class Code0781_RabbitsInForest {
    /**
     * 计算满足所有兔子回答的最小兔子数量。
     *
     * @param answers 每个元素表示某只兔子回答的“与自己颜色相同的其他兔子数量”
     * @return 满足所有回答条件的最少兔子总数
     */
    public int numRabbits(int[] answers) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();

        // 遍历每个回答，维护计数并计算最小兔子数量
        for (int i = 0; i < answers.length; i++) {
            map.put(answers[i], map.getOrDefault(answers[i], 0) + 1);

            // 当计数超过允许的最大值时，重置计数以开始新组
            if (map.get(answers[i]) > answers[i] + 1) {
                map.put(answers[i], 1);
            }

            // 如果当前计数为1，则需要新增一组，将该组的总人数加到结果中
            if (map.get(answers[i]) == 1) {
                ans += answers[i] + 1;
            }
        }
        return ans;
    }
}
