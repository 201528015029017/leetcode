# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def isSymmetric(self, root):
        """
        :type root: TreeNode
        :rtype: bool
        """
        if root == None:
            return True
        
        queue = [root.left, root.right]
        while queue:
            r = queue.pop()
            l = queue.pop()
            if r == None or l == None:
                if r != None or l != None:
                    return False
                continue
            
            if r.val != l.val:
                return False
            
            queue.append(r.left)
            queue.append(l.right)
            queue.append(r.right)
            queue.append(l.left)
        
        return True
        
            
        
