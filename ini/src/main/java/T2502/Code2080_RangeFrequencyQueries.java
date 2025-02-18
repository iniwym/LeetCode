package T2502;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 线段树
 * @Author: iniwym
 * @Date: 2025-02-18
 * @Link: https://leetcode.cn/problems/range-frequency-queries/
 */
public class Code2080_RangeFrequencyQueries {

    /**
     * RangeFreqQuery类用于查询数组中某个值在指定区间内出现的频率
     */
    class RangeFreqQuery {

        RangeFreqQueryNode root;

        /**
         * 构造函数，初始化RangeFreqQuery对象
         *
         * @param arr 输入的整数数组
         */
        public RangeFreqQuery(int[] arr) {
            root = buildTree(arr, 0, arr.length - 1);
        }

        /**
         * 递归构建频次查询树
         *
         * @param arr   输入的整数数组
         * @param start 当前节点的起始索引
         * @param end   当前节点的结束索引
         * @return 构建完成的节点
         */
        private RangeFreqQueryNode buildTree(int[] arr, int start, int end) {
            if (start > end) {
                return null;
            }
            RangeFreqQueryNode node = new RangeFreqQueryNode(start, end);
            if (start == end) {
                node.hashMap.put(arr[start], 1);
            } else {
                int mid = start + (end - start) / 2;
                node.left = buildTree(arr, start, mid);
                node.right = buildTree(arr, mid + 1, end);
                // 合并左右子树的频次信息
                mergeTree(node.hashMap, node.left.hashMap);
                mergeTree(node.hashMap, node.right.hashMap);
            }
            return node;
        }

        /**
         * 将source哈希表中的元素合并到target哈希表中
         *
         * @param target 目标哈希表
         * @param source 源哈希表
         */
        private void mergeTree(HashMap<Integer, Integer> target, HashMap<Integer, Integer> source) {
            for (Map.Entry<Integer, Integer> entry : source.entrySet()) {
                Integer key = entry.getKey();
                Integer value = entry.getValue();
                target.put(key, target.getOrDefault(key, 0) + value);
            }
        }

        /**
         * 查询指定区间内某个值出现的频率
         *
         * @param left  查询区间的起始索引
         * @param right 查询区间的结束索引
         * @param value 要查询的值
         * @return 指定值在区间内出现的频率
         */
        public int query(int left, int right, int value) {
            return query(root, left, right, value);
        }

        /**
         * 递归查询指定区间内某个值出现的频率
         *
         * @param node  当前查询的节点
         * @param left  查询区间的起始索引
         * @param right 查询区间的结束索引
         * @param value 要查询的值
         * @return 指定值在区间内出现的频率
         */
        public int query(RangeFreqQueryNode node, int left, int right, int value) {
            if (node == null || left > node.end || right < node.start) {
                return 0;
            }
            if (left <= node.start && right >= node.end) {
                return node.hashMap.getOrDefault(value, 0);
            }
            int leftCount = query(node.left, left, right, value);
            int rightCount = query(node.right, left, right, value);

            return leftCount + rightCount;
        }

        /**
         * RangeFreqQueryNode类表示频次查询树中的一个节点
         */
        class RangeFreqQueryNode {

            int start, end;

            HashMap<Integer, Integer> hashMap;

            RangeFreqQueryNode left, right;

            /**
             * 构造函数，初始化RangeFreqQueryNode对象
             *
             * @param start 当前节点的起始索引
             * @param end   当前节点的结束索引
             */
            public RangeFreqQueryNode(int start, int end) {
                this.start = start;
                this.end = end;
                hashMap = new HashMap<>();
            }
        }
    }

}
