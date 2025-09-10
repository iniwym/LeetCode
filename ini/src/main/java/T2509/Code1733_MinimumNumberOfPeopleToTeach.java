package T2509;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 贪心
 * @Author: iniwym
 * @Date: 2025-09-10
 * @Link: https://leetcode.cn/problems/minimum-number-of-people-to-teach/
 */
public class Code1733_MinimumNumberOfPeopleToTeach {

    /**
     * 计算最少需要教学的语言数量，使得所有无法交流的朋友对都能通过学习同一种语言进行交流
     *
     * @param n           语言总数，编号从1到n
     * @param languages   每个用户掌握的语言数组，languages[i]表示第i+1个用户掌握的语言列表
     * @param friendships 朋友关系数组，friendships[i] = [u, v]表示用户u和用户v是朋友
     * @return 最少需要教学的语言数量
     */
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {

        // 找出所有无法交流的朋友对，将无法交流的用户加入集合
        Set<Integer> cncon = new HashSet<>();
        for (int[] friendship : friendships) {
            Map<Integer, Integer> mp = new HashMap<>();
            boolean conm = false;
            // 将第一个朋友掌握的语言存入map
            for (int lan : languages[friendship[0] - 1]) {
                mp.put(lan, 1);
            }
            // 检查第二个朋友是否掌握第一个朋友会的语言
            for (int lan : languages[friendship[1] - 1]) {
                if (mp.containsKey(lan)) {
                    conm = true;
                    break;
                }
            }
            // 如果两个朋友无法交流，将他们加入无法交流用户集合
            if (!conm) {
                cncon.add(friendship[0] - 1);
                cncon.add(friendship[1] - 1);
            }
        }

        // 统计无法交流用户中各种语言的掌握人数，找出掌握人数最多的语言
        int max_cnt = 0;
        int[] cnt = new int[n + 1];
        for (int friendship : cncon) {
            for (int lan : languages[friendship]) {
                cnt[lan]++;
                max_cnt = Math.max(max_cnt, cnt[lan]);
            }
        }

        // 返回需要教学的人数减去掌握最流行语言的人数
        return cncon.size() - max_cnt;
    }

}
