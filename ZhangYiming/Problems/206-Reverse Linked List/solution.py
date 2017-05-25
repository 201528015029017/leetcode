# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def reverseList(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        new = ListNode(0)
        p = head
        while p != None:
            q = p.next
            p.next = new.next
            new.next = p
            p = q
        return new.next
        
