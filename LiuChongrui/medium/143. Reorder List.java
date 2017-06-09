/*
思路： 0->1->2->3->...->n 重排序后变为 0->n->1->n-1...
       一开始想用递归来实现，发现不是很好写。关键点在于能够以一定方法顺序得到n,n-1,n-2...
       所以先找中间的节点pivot，然后将之后的节点全部反向，pivot->null，如，1->2->3->4->5->6 => 1->2->3->4<-5<-6。4->null 4就是pvot节点
       这里一些solution是将1->2->3->4->5->6 变成 1->2->3->6->5->4 其实目的一样，都是为了得到n,n-1,n-2...的顺序
       然后双指针依次连就行了。
       ps：得到中间节点的方法，添加一个虚头节点，快慢双指针遍历链表。
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
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode fake = new ListNode(0);
        fake.next = head;
        ListNode p1 = fake,p2 = fake,pivot;
        while (p2.next != null && p2.next.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
        }
        pivot = p1.next;        // pivot is the middle of list
        ListNode p,pre,pp,qq,q;
        p = pivot.next;
        pre = pivot;
        pivot.next = null;
        while (p != null){    // 1->2->3->4->5->6 => 1->2->3->4<-5<-6,3->null
            pp = p.next;
            p.next = pre;
            pre = p;
            p = pp;
        }
        p = head; q = pre;
        //System.out.println(p.next.next.val);
        while (p != pivot && q != pivot){
            pp = p.next; qq = q.next;
            p.next = q; q.next = pp;
            p = pp; q = qq;
        }
    }
}