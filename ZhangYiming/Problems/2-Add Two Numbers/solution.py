# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    
    def val(self, l1, l2, c):
        if l1 != None:
            c += l1.val
        if l2 != None:
            c += l2.val
        if c >= 10:
            return (1, c-10)
        else:
            return (0, c)
            
    def next(self, l1, l2, c):
        Flag = False
        if l1 != None:
            l1 = l1.next
        if l2 != None:
            l2 = l2.next
        if l1 != None or l2 != None or c != 0:
            Flag = True
        return (Flag, l1, l2)
        
    def addTwoNumbers(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        l = ListNode(0)
        c = 0
        c, l.val = self.val(l1, l2, c)
        head = l
        before = l
        Flag, l1, l2 = self.next(l1, l2, c)
        while(Flag):
            l = ListNode(0)
            before.next = l
            before = l
            c, l.val = self.val(l1, l2, c)
            Flag, l1, l2 = self.next(l1, l2, c)
            
        return head
        
        
