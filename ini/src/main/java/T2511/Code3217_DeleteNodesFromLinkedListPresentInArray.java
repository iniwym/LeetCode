package T2511;


import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 哨兵节点
 * @Author: iniwym
 * @Date: 2025-11-01
 * @Link: https://leetcode.cn/problems/delete-nodes-from-linked-list-present-in-array/
 */
public class Code3217_DeleteNodesFromLinkedListPresentInArray {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 从链表中删除指定值的节点
     *
     * @param nums 要删除的节点值数组
     * @param head 链表的头节点
     * @return 删除指定值节点后的链表头节点
     */
    public ListNode modifiedList(int[] nums, ListNode head) {
        // 将要删除的值存入HashSet，便于快速查找
        Set<Integer> set = new HashSet<>(nums.length, 1); // 预分配空间
        for (int x : nums) {
            set.add(x);
        }

        // 使用虚拟头节点简化链表操作
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        // 遍历链表，删除包含指定值的节点
        while (cur.next != null) {
            ListNode nxt = cur.next;
            if (set.contains(nxt.val)) {
                cur.next = nxt.next; // 从链表中删除 nxt 节点
            } else {
                cur = nxt; // 不删除 nxt，继续向后遍历链表
            }
        }
        return dummy.next;
    }

}

