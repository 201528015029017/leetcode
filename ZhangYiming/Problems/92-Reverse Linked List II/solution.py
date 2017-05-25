# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def reverseBetween(self, head, m, n):
        """
        :type head: ListNode
        :type m: int
        :type n: int
        :rtype: ListNode
        """
        new = ListNode(0)
        new.next = head
        n = n - m
        
        now = new
        while m != 0:
            pre1 = now
            now = pre1.next
            m -= 1
            
        while n != -1:
            now = now.next
            n -= 1
        
        x = pre1.next
        pre1.next = now
        while x != now:
            p = x.next
            x.next = pre1.next
            pre1.next = x
            x = p
        
        return new.next
            
            
