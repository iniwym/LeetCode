package T2509;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @Description: 优先级队列
 * @Author: iniwym
 * @Date: 2025-09-18
 * @Link: https://leetcode.cn/problems/design-task-manager/
 */
public class Code3408_DesignTaskManager {

    /**
     * TaskManager类用于管理任务的优先级队列
     * 支持添加、编辑、删除任务以及执行最高优先级任务的操作
     */
    class TaskManager {
        private Map<Integer, int[]> taskInfo; // 存储任务ID到[优先级,用户ID]的映射
        private PriorityQueue<int[]> heap;    // 优先级队列，按优先级和任务ID排序

        /**
         * 构造函数，初始化任务管理器
         *
         * @param tasks 任务列表，每个任务包含[用户ID,任务ID,优先级]
         */
        public TaskManager(List<List<Integer>> tasks) {
            taskInfo = new HashMap<>();
            // 创建优先级队列，优先级高的在前，优先级相同时任务ID大的在前
            heap = new PriorityQueue<>((a, b) -> {
                if (a[0] != b[0]) {
                    return b[0] - a[0];
                }
                return b[1] - a[1];
            });

            // 初始化任务信息
            for (List<Integer> task : tasks) {
                int userId = task.get(0), taskId = task.get(1), priority = task.get(2);
                taskInfo.put(taskId, new int[]{priority, userId});
                heap.offer(new int[]{priority, taskId});
            }
        }

        /**
         * 添加新任务
         *
         * @param userId   用户ID
         * @param taskId   任务ID
         * @param priority 任务优先级
         */
        public void add(int userId, int taskId, int priority) {
            taskInfo.put(taskId, new int[]{priority, userId});
            heap.offer(new int[]{priority, taskId});
        }

        /**
         * 编辑任务优先级
         *
         * @param taskId      任务ID
         * @param newPriority 新的优先级
         */
        public void edit(int taskId, int newPriority) {
            if (taskInfo.containsKey(taskId)) {
                taskInfo.get(taskId)[0] = newPriority;
                heap.offer(new int[]{newPriority, taskId});
            }
        }

        /**
         * 删除任务
         *
         * @param taskId 要删除的任务ID
         */
        public void rmv(int taskId) {
            taskInfo.remove(taskId);
        }

        /**
         * 执行优先级最高的任务
         *
         * @return 执行任务的用户ID，如果没有可执行任务则返回-1
         */
        public int execTop() {
            // 从优先级队列中查找有效的最高优先级任务
            while (!heap.isEmpty()) {
                int[] task = heap.poll();
                int priority = task[0], taskId = task[1];

                // 检查任务是否仍然有效且优先级未被修改
                if (taskInfo.containsKey(taskId) && taskInfo.get(taskId)[0] == priority) {
                    int userId = taskInfo.get(taskId)[1];
                    taskInfo.remove(taskId);
                    return userId;
                }
            }
            return -1;
        }
    }


}
