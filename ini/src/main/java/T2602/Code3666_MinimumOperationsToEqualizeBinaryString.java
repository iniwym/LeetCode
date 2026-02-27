package T2602;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/**
 * @Description: BFS
 * @Author: iniwym
 * @Date: 2026-02-27
 * @Link: https://leetcode.cn/problems/minimum-operations-to-equalize-binary-string/
 */
public class Code3666_MinimumOperationsToEqualizeBinaryString {

    /**
     * 计算将字符串 s 转换为目标状态所需的最小操作次数。
     *
     * @param s 输入的二进制字符串，仅包含字符 '0' 和 '1'
     * @param k 每次操作可以翻转的连续子串长度
     * @return 返回最小操作次数；如果无法完成转换，则返回 -1
     */
    public int minOperations(String s, int k) {
        int n = s.length();
        // 初始化两个 TreeSet，分别存储偶数位置和奇数位置未访问的索引
        TreeSet<Integer>[] notVis = new TreeSet[2];
        for (int m = 0; m < 2; m++) {
            notVis[m] = new TreeSet<>();
            for (int i = m; i <= n; i += 2) {
                notVis[m].add(i);
            }
        }

        // 计算初始状态下 '0' 的数量作为起点
        int start = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                start++;
            }
        }

        // 移除起点对应的索引，并初始化队列
        notVis[start % 2].remove(start);
        List<Integer> q = new ArrayList<>();
        q.add(start);

        // BFS 遍历所有可能的状态
        for (int ans = 0; !q.isEmpty(); ans++) {
            List<Integer> tmp = q;
            q = new ArrayList<>();

            // 处理当前层的所有状态
            for (int z : tmp) {
                if (z == 0) { // 当前状态没有 '0'，表示已完成转换
                    return ans;
                }

                // 计算从当前状态 z 可以翻转到的目标范围 [mn, mx]
                int mn = z + k - 2 * Math.min(k, z);
                int mx = z + k - 2 * Math.max(0, k - n + z);

                // 在对应集合中查找可到达的新状态
                TreeSet<Integer> set = notVis[mn % 2];
                for (Iterator<Integer> it = set.tailSet(mn).iterator(); it.hasNext(); it.remove()) {
                    int j = it.next();
                    if (j > mx) {
                        break;
                    }
                    q.add(j);
                }
            }
        }

        // 如果无法达到目标状态，返回 -1
        return -1;
    }

}
