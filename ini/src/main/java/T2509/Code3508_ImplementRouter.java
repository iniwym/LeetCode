package T2509;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @Description: 哈希表 + 队列 + 二分查找
 * @Author: iniwym
 * @Date: 2025-09-20
 * @Link: https://leetcode.cn/problems/implement-router/
 */
public class Code3508_ImplementRouter {

    /**
     * 路由器类，用于管理数据包的接收、转发和统计。
     * 支持限制内存中保存的数据包数量，当超出限制时自动转发最早接收的数据包。
     * 同时支持按目标地址和时间范围统计数据包数量。
     */
    class Router {
        /**
         * 数据包记录类，包含源地址、目标地址和时间戳。
         */
        private class Packet {
            private final int source;
            private final int destination;
            private final int timestamp;

            /**
             * 构造函数，初始化数据包的源地址、目标地址和时间戳。
             *
             * @param source      数据包的源地址
             * @param destination 数据包的目标地址
             * @param timestamp   数据包的时间戳
             */
            public Packet(int source, int destination, int timestamp) {
                this.source = source;
                this.destination = destination;
                this.timestamp = timestamp;
            }

            /**
             * 获取数据包的源地址。
             *
             * @return 源地址
             */
            public int source() {
                return source;
            }

            /**
             * 获取数据包的目标地址。
             *
             * @return 目标地址
             */
            public int destination() {
                return destination;
            }

            /**
             * 获取数据包的时间戳。
             *
             * @return 时间戳
             */
            public int timestamp() {
                return timestamp;
            }

            // 需要重写 equals 和 hashCode 方法
            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                Packet packet = (Packet) obj;
                return source == packet.source &&
                        destination == packet.destination &&
                        timestamp == packet.timestamp;
            }

            @Override
            public int hashCode() {
                return java.util.Objects.hash(source, destination, timestamp);
            }
        }

        /**
         * 时间戳列表和队首指针的配对记录类。
         * 用于维护某个目标地址对应的时间戳列表及逻辑上的队首下标。
         */
        private class Pair {
            private final List<Integer> timestamps;
            private final int head;

            /**
             * 构造函数，初始化时间戳列表和队首指针。
             *
             * @param timestamps 时间戳列表
             * @param head       队首指针索引
             */
            public Pair(List<Integer> timestamps, int head) {
                this.timestamps = timestamps;
                this.head = head;
            }

            /**
             * 获取时间戳列表。
             *
             * @return 时间戳列表
             */
            public List<Integer> timestamps() {
                return timestamps;
            }

            /**
             * 获取队首指针索引。
             *
             * @return 队首指针索引
             */
            public int head() {
                return head;
            }
        }

        private final int memoryLimit; // 路由器能缓存的最大数据包数量
        private final Queue<Packet> packetQ = new ArrayDeque<>(); // 数据包队列，维护接收顺序
        private final Set<Packet> packetSet = new HashSet<>(); // 用于快速判断数据包是否已存在
        private final Map<Integer, Pair> destToTimestamps = new HashMap<>(); // 目标地址到时间戳列表的映射

        /**
         * 构造函数，初始化路由器的内存限制。
         *
         * @param memoryLimit 路由器能缓存的最大数据包数量
         */
        public Router(int memoryLimit) {
            this.memoryLimit = memoryLimit;
        }

        /**
         * 添加一个数据包到路由器中。
         * 如果数据包已存在则返回 false；
         * 如果当前缓存已满，则先转发最早的数据包再添加新数据包。
         *
         * @param source      数据包的源地址
         * @param destination 数据包的目标地址
         * @param timestamp   数据包的时间戳
         * @return 如果成功添加数据包返回 true，如果数据包已存在返回 false
         */
        public boolean addPacket(int source, int destination, int timestamp) {
            Packet packet = new Packet(source, destination, timestamp);
            if (!packetSet.add(packet)) { // packet 在 packetSet 中
                return false;
            }
            if (packetQ.size() == memoryLimit) { // 太多了
                forwardPacket();
            }
            packetQ.add(packet); // 入队
            destToTimestamps.computeIfAbsent(destination, k -> new Pair(new ArrayList<>(), 0)).timestamps.add(timestamp);
            return true;
        }

        /**
         * 转发最早接收的数据包。
         * 如果队列为空则返回空数组。
         *
         * @return 包含源地址、目标地址和时间戳的数组，如果队列为空则返回空数组
         */
        public int[] forwardPacket() {
            if (packetQ.isEmpty()) {
                return new int[]{};
            }
            Packet packet = packetQ.poll(); // 出队
            packetSet.remove(packet);
            destToTimestamps.compute(packet.destination, (k, p) -> new Pair(p.timestamps, p.head + 1)); // 队首下标加一，模拟出队
            return new int[]{packet.source, packet.destination, packet.timestamp};
        }

        /**
         * 统计指定目标地址在指定时间范围内的数据包数量。
         * 时间范围为 [startTime, endTime]（包含边界）。
         *
         * @param destination 目标地址
         * @param startTime   时间范围起始时间戳（包含）
         * @param endTime     时间范围结束时间戳（包含）
         * @return 指定条件下的数据包数量
         */
        public int getCount(int destination, int startTime, int endTime) {
            Pair p = destToTimestamps.get(destination);
            if (p == null) {
                return 0;
            }
            int left = lowerBound(p.timestamps, startTime, p.head - 1);
            int right = lowerBound(p.timestamps, endTime + 1, p.head - 1);
            return right - left;
        }

        /**
         * 在有序列表中查找第一个大于等于目标值的位置（下界）。
         * 使用二分查找实现，查找范围为 [left+1, nums.size()-1]。
         *
         * @param nums   有序整数列表
         * @param target 目标值
         * @param left   查找范围的左边界（不包含）
         * @return 第一个大于等于目标值的位置索引
         */
        private int lowerBound(List<Integer> nums, int target, int left) {
            int right = nums.size();
            while (left + 1 < right) {
                int mid = left + (right - left) / 2;
                if (nums.get(mid) >= target) {
                    right = mid;
                } else {
                    left = mid;
                }
            }
            return right;
        }
    }

}
