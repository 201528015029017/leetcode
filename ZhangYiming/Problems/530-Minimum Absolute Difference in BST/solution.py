# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def order(self, node, array):
        if node == None:
            return 
        if node.left != None:
            self.order(node.left, array)
        array.append(node.val)
        if node.right != None:
            self.order(node.right, array)
        
        
    def getMinimumDifference(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        # if root == None:
        #     return 0
            
        # if root.left == None:
        #     if root.right == None:
        #         return 0
        #     else:
        #         min = abs(root.val - root.right.val)
        # else:
        #     min = abs(root.val - root.left.val)
            
        array = []
        self.order(root, array)
        if len(array) <= 1:
            return 0
        min = abs(array[0] - array[1])
        for i in range(1, len(array) - 1):
            if min > abs(array[i] - array[i+1]):
                min = abs(array[i] - array[i+1])
        return min
        
