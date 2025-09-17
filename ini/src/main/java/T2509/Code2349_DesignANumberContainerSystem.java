package T2509;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @Description: 哈希表
 * @Author: iniwym
 * @Date: 2025-09-17
 * @Link: https://leetcode.cn/problems/design-a-number-container-system/
 */
public class Code2349_DesignANumberContainerSystem {

    /**
     * NumberContainers类用于维护索引与数字之间的映射关系
     * 支持在指定索引处插入/更新数字，以及查找包含指定数字的最小索引
     */
    class NumberContainers {
        private final Map<Integer, Integer> indexToNumber = new HashMap<>();
        private final Map<Integer, TreeSet<Integer>> numberToIndices = new HashMap<>();

        /**
         * 在指定索引处插入或更新数字
         * 如果该索引已存在数字，则先移除旧的映射关系，再建立新的映射关系
         *
         * @param index  要插入或更新的索引位置
         * @param number 要插入或更新的数字
         */
        public void change(int index, int number) {
            // 移除旧数据
            Integer oldNumber = indexToNumber.get(index);
            if (oldNumber != null) {
                numberToIndices.get(oldNumber).remove(index);
            }

            // 添加新数据
            indexToNumber.put(index, number);
            numberToIndices.computeIfAbsent(number, x -> new TreeSet<>()).add(index);
        }

        /**
         * 查找包含指定数字的最小索引
         *
         * @param number 要查找的数字
         * @return 包含该数字的最小索引，如果不存在则返回-1
         */
        public int find(int number) {
            TreeSet<Integer> indices = numberToIndices.get(number);
            return indices == null || indices.isEmpty() ? -1 : indices.first();
        }
    }

}
