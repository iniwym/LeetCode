package T2602;

import java.util.Map;
import java.util.TreeMap;

/**
 * @Description: 有序集合
 * @Author: iniwym
 * @Date: 2026-02-02
 * @Link: https://leetcode.cn/problems/divide-an-array-into-subarrays-with-minimum-cost-ii/
 */
public class Code3013_DivideAnArrayIntoSubarraysWithMinimumCostIi {

    /**
     * 计算在给定数组中选择k个元素的最小成本，其中任意两个被选元素之间的距离至少为dist。
     *
     * @param nums 输入的整数数组
     * @param k    需要选择的元素数量
     * @param dist 元素之间必须满足的最小距离
     * @return 返回满足条件的最小成本
     */
    public long minimumCost(int[] nums, int k, int dist) {
        k--; // 将k减一以便后续处理
        sumL = nums[0]; // 初始化左侧集合的成本总和
        // 初始化左侧集合，将前dist+1个元素加入左侧集合
        for (int i = 1; i < dist + 2; i++) {
            sumL += nums[i];
            L.merge(nums[i], 1, Integer::sum);
        }
        sizeL = dist + 1; // 左侧集合当前大小
        // 调整左侧集合大小至k
        while (sizeL > k) {
            l2r(); // 将左侧多余的元素移动到右侧
        }

        long ans = sumL; // 当前最优解初始化为初始左侧集合的成本
        // 遍历剩余元素，维护滑动窗口内的最优解
        for (int i = dist + 2; i < nums.length; i++) {
            // 移除窗口外的元素
            int out = nums[i - dist - 1];
            if (L.containsKey(out)) {
                sumL -= out;
                sizeL--;
                removeOne(L, out); // 从左侧集合移除该元素
            } else {
                removeOne(R, out); // 从右侧集合移除该元素
            }

            // 添加新进入窗口的元素
            int in = nums[i];
            if (in < L.lastKey()) {
                sumL += in;
                sizeL++;
                L.merge(in, 1, Integer::sum); // 加入左侧集合
            } else {
                R.merge(in, 1, Integer::sum); // 加入右侧集合
            }

            // 根据当前左侧集合大小调整左右集合平衡
            if (sizeL == k - 1) {
                r2l(); // 右侧向左侧转移一个元素
            } else if (sizeL == k + 1) {
                l2r(); // 左侧向右侧转移一个元素
            }

            ans = Math.min(ans, sumL); // 更新最小成本
        }
        return ans;
    }

    private long sumL;
    private int sizeL;
    private final TreeMap<Integer, Integer> L = new TreeMap<>();
    private final TreeMap<Integer, Integer> R = new TreeMap<>();

    /**
     * 将元素从左集合L移动到右集合R的函数。
     * <p>
     * 该函数执行以下操作：
     * 1. 获取左集合L中的最大键值x；
     * 2. 从左集合L中移除该键值x；
     * 3. 更新左集合的总和sumL和大小sizeL；
     * 4. 将键值x合并到右集合R中（如果已存在则增加计数）。
     */
    private void l2r() {
        // 获取左集合L中的最大键值
        int x = L.lastKey();

        // 从左集合L中移除键值x，并更新相关统计信息
        removeOne(L, x);
        sumL -= x;
        sizeL--;

        // 将键值x合并到右集合R中
        R.merge(x, 1, Integer::sum);
    }


    /**
     * 将元素从右集合R移动到左集合L，并更新相关统计信息。
     * <p>
     * 该方法执行以下操作：
     * 1. 获取右集合R中的最小键值x；
     * 2. 从右集合R中移除该键值x；
     * 3. 将x累加到左集合的总和sumL中；
     * 4. 增加左集合的大小计数sizeL；
     * 5. 将x合并到左集合L中（如果已存在则增加计数，否则新增）。
     */
    private void r2l() {
        // 获取右集合R中的最小键值
        int x = R.firstKey();

        // 从右集合R中移除键值x
        removeOne(R, x);

        // 更新左集合的总和和大小
        sumL += x;
        sizeL++;

        // 将键值x合并到左集合L中
        L.merge(x, 1, Integer::sum);
    }


    /**
     * 从给定的Map中移除指定键的一个计数。
     * 如果该键的计数值大于1，则将其减1；否则直接从Map中移除该键。
     *
     * @param m 要操作的Map，键为Integer类型，值为对应的计数值
     * @param x 要减少计数或移除的键
     */
    private void removeOne(Map<Integer, Integer> m, int x) {
        // 获取键x当前的计数值
        int cnt = m.get(x);

        // 如果计数值大于1，将其减1并更新到Map中
        if (cnt > 1) {
            m.put(x, cnt - 1);
        } else {
            // 否则直接从Map中移除该键
            m.remove(x);
        }
    }

}
