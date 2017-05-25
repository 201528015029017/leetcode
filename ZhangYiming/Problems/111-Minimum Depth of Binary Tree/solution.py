# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def minDepth(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        if root == None:
            return 0
        
        queue = [root]
        height = 1
        while True:
            temp = []
            for node in queue:
                if not node.left and not node.right:
                    return height
                temp += [kid for kid in (node.left, node.right) if kid]
            queue = temp
            height += 1
                
            
