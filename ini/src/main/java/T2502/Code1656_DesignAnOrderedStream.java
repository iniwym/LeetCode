package T2502;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 数组
 * @Author: iniwym
 * @Date: 2025-02-24
 * @Link: https://leetcode.cn/problems/design-an-ordered-stream/
 */
public class Code1656_DesignAnOrderedStream {

    /**
     * OrderedStream 类用于实现一个有序流的数据结构
     * 有序流是一种特殊的数据结构，它按照特定的顺序存储字符串，并通过插入操作来构建和访问
     */
    public static class OrderedStream {

        // 存储有序流中的字符串数组
        String[] stream;
        // 指向当前应该插入或访问的数组索引
        int ptr;
        // 数组的长度，即有序流中能容纳的字符串数量
        int sum;

        /**
         * 构造函数，初始化有序流
         *
         * @param n 有序流中能容纳的字符串数量
         */
        public OrderedStream(int n) {
            stream = new String[n];
            ptr = 0;
            sum = n;
        }

        /**
         * 插入函数，将字符串插入到指定的位置，并返回从当前指针开始到第一个未插入位置的所有字符串
         *
         * @param idKey 插入字符串的位置编号
         * @param value 要插入的字符串
         * @return 从当前指针开始到第一个未插入位置的所有字符串组成的列表
         */
        public List<String> insert(int idKey, String value) {
            // 将字符串插入到指定的位置
            stream[idKey - 1] = value;
            // 如果插入位置在当前指针之后，则返回空列表，因为当前指针之前的位置还未被填充
            if (idKey - 1 > ptr) {
                return new ArrayList<>();
            }
            // 初始化结果列表
            List<String> ans = new ArrayList<>();
            // 从当前指针开始，将所有已插入的字符串添加到结果列表中，并移动指针
            while (ptr < sum && stream[ptr] != null) {
                ans.add(stream[ptr]);
                ptr++;
            }

            // 返回结果列表
            return ans;
        }
    }

    public static void main(String[] args) {
        int n = 5;
        OrderedStream obj = new OrderedStream(n);
        // [3, "ccccc"], [1, "aaaaa"], [2, "bbbbb"], [5, "eeeee"], [4, "ddddd"]
        List<String> param_1 = obj.insert(3, "cccc");
        List<String> param_2 = obj.insert(1, "aaaa");
    }
}
