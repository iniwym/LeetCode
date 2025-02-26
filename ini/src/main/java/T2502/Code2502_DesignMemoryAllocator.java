package T2502;

import java.util.*;

/**
 * @Description: 后续用线段树实现
 * @Author: iniwym
 * @Date: 2025-02-25
 * @Link: https://leetcode.cn/problems/design-memory-allocator/
 */
public class Code2502_DesignMemoryAllocator {

    public static class Allocator {
        // 内存数组
        private int[] memory;

        // 反向图 index size
        private Map<Integer, List<int[]>> allocMap;

        // 记录空闲块 begin size
        private TreeSet<int[]> freeBlocks;

        /**
         * 初始化内存分配器
         *
         * @param n 内存的总大小，以整数表示
         *          <p>
         *          内存分配器的构造函数，负责初始化内存块、分配映射和空闲块集合
         *          它创建了一个大小为n的内存数组，一个用于跟踪已分配内存块的映射，
         *          以及一个按起始地址排序的空闲内存块集合初始时，整个内存被视为一个空闲块
         */
        public Allocator(int n) {
            memory = new int[n];
            allocMap = new HashMap<>();
            freeBlocks = new TreeSet<>(Comparator.comparingInt(a -> a[0]));
            freeBlocks.add(new int[]{0, n});
        }

        /**
         * 分配内存块
         *
         * @param size 内存块的大小
         * @param mID  内存块的ID
         * @return 分配的内存块的起始位置，如果无法分配则返回-1
         */
        public int allocate(int size, int mID) {
            // 检查是否有可用的空闲块
            if (!freeBlocks.isEmpty()) {
                Iterator<int[]> iterator = freeBlocks.iterator();
                while (iterator.hasNext()) {
                    int[] block = iterator.next();
                    int blockBegin = block[0];
                    int blockLength = block[1];
                    // 当找到一个足够大的空闲块时
                    if (blockLength >= size) {
                        freeBlocks.remove(block);

                        // 更新内存数组
                        Arrays.fill(memory, blockBegin, blockBegin + size, mID);

                        // 更新反向图
                        List<int[]> list = allocMap.getOrDefault(mID, new ArrayList<>());
                        list.add(new int[]{blockBegin, size});
                        allocMap.put(mID, list);

                        // 更新空闲块
                        if (blockLength - size > 0) {
                            freeBlocks.add(new int[]{blockBegin + size, blockLength - size});
                        }

                        return blockBegin;
                    }
                }
            }

            return -1;
        }

        /**
         * 释放指定内存块的内存
         *
         * @param mID 内存块的ID，用于标识特定的内存分配
         * @return 返回释放的内存大小如果指定的mID不存在于分配映射中，则返回0
         */
        public int freeMemory(int mID) {
            // 根据反向图来确定是否包含mID
            if (!allocMap.containsKey(mID)) {
                return 0;
            }
            int count = 0;
            List<int[]> list = allocMap.get(mID);

            // 遍历mID对应的内存块列表，释放内存并合并空闲块
            for (int[] temp : list) {
                int begin = temp[0];
                int size = temp[1];
                count += size;
                mergeFreeBlock(begin, size);
            }
            // 从分配映射中移除mID对应的内存记录
            allocMap.remove(mID);
            // 返回总共释放的内存大小
            return count;
        }

        /**
         * 合并空闲内存块
         * 当释放内存或分配内存后，可能需要合并相邻的空闲内存块，以维护一个连续的空闲内存区域列表
         * 此方法的目的是将新释放的内存区域与相邻的空闲内存块合并，以优化内存使用和管理
         *
         * @param start 新释放的内存区域的起始地址
         * @param size  新释放的内存区域的大小
         */
        private void mergeFreeBlock(int start, int size) {
            // 计算新释放内存区域的结束地址
            int end = start + size;

            // 查找新释放区域前一个空闲块，如果存在，并且与新释放区域相邻，则合并
            int[] prev = freeBlocks.floor(new int[]{start, 0});
            if (prev != null && prev[0] + prev[1] != start) {
                prev = null; // 检查是否真正相邻
            }

            // 查找新释放区域后一个空闲块，如果存在，并且与新释放区域相邻，则合并
            int[] next = freeBlocks.ceiling(new int[]{end, 0});
            if (next != null && next[0] != end) {
                next = null;
            }

            // 合并逻辑
            if (prev != null) {
                // 如果有前一个相邻的空闲块，更新起始地址和大小，并移除旧的空闲块信息
                start = prev[0];
                size += prev[1];
                freeBlocks.remove(prev);
            }
            if (next != null) {
                // 如果有后一个相邻的空闲块，更新大小，并移除旧的空闲块信息
                size += next[1];
                freeBlocks.remove(next);
            }
            // 将合并后的空闲块添加到空闲块集合中
            freeBlocks.add(new int[]{start, size});
        }
    }

    public static void main(String[] args) {
        Allocator loc = new Allocator(10); // 初始化一个大小为 10 的内存数组，所有内存单元都是空闲的。
        System.out.println(loc.allocate(1, 1)); // 最左侧的块的第一个下标是 0 。内存数组变为 [1, , , , , , , , , ]。返回 0 。
        System.out.println(loc.allocate(1, 2)); // 最左侧的块的第一个下标是 1 。内存数组变为 [1,2, , , , , , , , ]。返回 1 。
        System.out.println(loc.allocate(1, 3)); // 最左侧的块的第一个下标是 2 。内存数组变为 [1,2,3, , , , , , , ]。返回 2 。
        System.out.println(loc.freeMemory(2)); //) 释放 mID 为 2 的所有内存单元。内存数组变为 [1, ,3, , , , , , , ] 。返回 1 ，因为只有 1 个 mID 为 2 的内存单元。
        System.out.println(loc.allocate(3, 4)); // 最左侧的块的第一个下标是 3 。内存数组变为 [1, ,3,4,4,4, , , , ]。返回 3 。
        System.out.println(loc.allocate(1, 1)); // 最左侧的块的第一个下标是 1 。内存数组变为 [1,1,3,4,4,4, , , , ]。返回 1 。
        System.out.println(loc.allocate(1, 1)); // 最左侧的块的第一个下标是 6 。内存数组变为 [1,1,3,4,4,4,1, , , ]。返回 6 。
        System.out.println(loc.freeMemory(1)); //) 释放 mID 为 1 的所有内存单元。内存数组变为 [ , ,3,4,4,4, , , , ] 。返回 3 ，因为有 3 个 mID 为 1 的内存单元。
        System.out.println(loc.allocate(10, 2)); // 无法找出长度为 10 个连续空闲内存单元的空闲块，所有返回 -1 。
        System.out.println(loc.freeMemory(7)); //) 释放 mID 为 7 的所有内存单元。内存数组保持原状，因为不存在 mID 为 7 的内存单元。返回 0 。
    }

}
