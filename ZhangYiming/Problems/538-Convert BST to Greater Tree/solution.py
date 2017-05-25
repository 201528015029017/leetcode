# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def convert(self, root):
        if root == None:
            return
        self.convert(root.right)
        self.count += root.val
        root.val = self.count
        self.convert(root.left)
        
    def convertBST(self, root):
        """
        :type root: TreeNode
        :rtype: TreeNode
        """
        self.count = 0
        self.convert(root)
        return root
        
