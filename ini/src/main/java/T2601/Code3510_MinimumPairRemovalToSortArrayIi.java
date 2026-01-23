package T2601;

import java.util.Comparator;
import java.util.Objects;
import java.util.TreeSet;

/**
 * @Description: 有序集合
 * @Author: iniwym
 * @Date: 2026-01-23
 * @Link: https://leetcode.cn/problems/minimum-pair-removal-to-sort-array-ii/
 */
public class Code3510_MinimumPairRemovalToSortArrayIi {

    /**
     * 表示一个包含长整型数值和整型索引的键值对类
     * 用于存储数值和对应的索引位置信息
     */
    private static class Pair {
        long s;
        int i;

        /**
         * 构造函数，创建一个新的Pair对象
         *
         * @param s 长整型数值
         * @param i 整型索引
         */
        Pair(long s, int i) {
            this.s = s;
            this.i = i;
        }

        /**
         * 判断当前对象与另一个对象是否相等
         * 基于数值s和索引i进行比较
         *
         * @param obj 待比较的对象
         * @return 如果两个Pair对象的数值和索引都相等则返回true，否则返回false
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Pair pair = (Pair) obj;
            return s == pair.s && i == pair.i;
        }

        /**
         * 计算对象的哈希码值
         * 基于数值s和索引i生成哈希码
         *
         * @return 基于s和i计算出的哈希码值
         */
        @Override
        public int hashCode() {
            return Objects.hash(s, i);
        }
    }

    /**
     * 计算使数组变为非递减序列所需的最少相邻元素合并操作次数
     *
     * @param nums 输入的整数数组
     * @return 返回需要执行的最少合并操作次数
     */
    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        // 使用自定义比较器的TreeSet，按元素和升序排列，和相同时按下标升序排列
        TreeSet<Pair> pairs = new TreeSet<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair a, Pair b) {
                if (a.s != b.s) {
                    return Long.compare(a.s, b.s);
                }
                return Integer.compare(a.i, b.i);
            }
        });

        int dec = 0; // 递减的相邻对的个数
        for (int i = 0; i < n - 1; i++) {
            int x = nums[i];
            int y = nums[i + 1];
            if (x > y) {
                dec++;
            }
            pairs.add(new Pair(x + y, i));
        }

        // 剩余下标
        TreeSet<Integer> idx = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            idx.add(i);
        }

        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = nums[i];
        }

        int ans = 0;
        while (dec > 0) {
            ans++;

            // 删除相邻元素和最小的一对
            Pair p = pairs.pollFirst();
            long s = p.s;
            int i = p.i;

            // (当前元素，下一个数)
            int nxt = idx.higher(i);
            if (a[i] > a[nxt]) { // 旧数据
                dec--;
            }

            // 更新与前一个元素相关的递减关系
            Integer pre = idx.lower(i);
            if (pre != null) {
                if (a[pre] > a[i]) { // 旧数据
                    dec--;
                }
                if (a[pre] > s) { // 新数据
                    dec++;
                }
                pairs.remove(new Pair(a[pre] + a[i], pre));
                pairs.add(new Pair(a[pre] + s, pre));
            }

            // 更新与后两个元素相关的递减关系
            Integer nxt2 = idx.higher(nxt);
            if (nxt2 != null) {
                if (a[nxt] > a[nxt2]) { // 旧数据
                    dec--;
                }
                if (s > a[nxt2]) { // 新数据（当前元素，下下一个数）
                    dec++;
                }
                pairs.remove(new Pair(a[nxt] + a[nxt2], nxt));
                pairs.add(new Pair(s + a[nxt2], i));
            }

            a[i] = s; // 把 a[nxt] 加到 a[i] 中
            idx.remove(nxt); // 删除 nxt
        }
        return ans;
    }

}
