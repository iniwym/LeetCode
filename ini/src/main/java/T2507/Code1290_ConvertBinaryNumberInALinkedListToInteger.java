package T2507;

/**
 * @Description: 遍历链表
 * @Author: iniwym
 * @Date: 2025-07-14
 * @Link: https://leetcode.cn/problems/convert-binary-number-in-a-linked-list-to-integer/
 */
public class Code1290_ConvertBinaryNumberInALinkedListToInteger {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
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
         * 将链表表示的二进制数转换为十进制整数
         *
         * @param head 链表的头节点，每个节点的val值为0或1，表示二进制位
         * @return 转换后的十进制整数
         * <p>
         * 算法说明：
         * 1. 遍历链表，每次将当前结果左移1位（相当于乘以2）
         * 2. 使用位或操作将当前节点的值（0或1）添加到最低位
         * 3. 最终得到二进制链表对应的十进制值
         */
        public int getDecimalValue(ListNode head) {
            int ans = 0;
            ListNode current = head;

            // 遍历链表，逐位计算十进制值
            while (current != null) {
                ans = (ans << 1) | current.val;
                current = current.next;
            }

            return ans;
        }
    }

}
