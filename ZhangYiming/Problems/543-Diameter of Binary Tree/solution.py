# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def diameterOfBinaryTree(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        if not root:
            return 0
            
        def height(root):
            if root == None:
                return (0, 0)
            lh, ll = height(root.left)
            rh, rl = height(root.right)
            return (max(lh, rh) + 1, max(lh + rh + 1, rl, ll))
        
        return height(root)[1] - 1
        
