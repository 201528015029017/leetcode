/*
思路：注意这里的k可能会大于链表长度，所以我们要先求链表长度，然后取模。
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        ListNode p = head, tail = head;
        if (head == null) return head;
        int count = 0;
        while (p != null){
            count++;
            tail = p;
            p = p.next;
        }
        k = k % count;
        p = head;
        int step = count-k-1;
        while (step>0){
            step--;
            p = p.next;
        }
        tail.next = head;
        head = p.next;
        p.next = null;
        return head;
    }
}