# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def levelOrderBottom(self, root):
        """
        :type root: TreeNode
        :rtype: List[List[int]]
        """
        if root == None:
            return []
            
        temp = [root]
        stack = []
        
        while temp:
            stack.append(temp)
            temp = [kid for node in stack[-1] for kid in (node.left, node.right) if kid]
        
        result = []
        while stack:
            temp = stack.pop()
            result.append([node.val for node in temp])
        
        return result
                
