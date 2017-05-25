# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def levelOrder(self, root):
        """
        :type root: TreeNode
        :rtype: List[List[int]]
        """
        queue = [root]
        result = []
        
        while queue:
            temp = []
            tempval = []
            for node in queue:
                if node == None:
                    continue
                temp.append(node.left)
                temp.append(node.right)
                tempval.append(node.val)
            if len(tempval) > 0:
                result.append(tempval)
            queue = temp
            
        return result
            
            
            
            
