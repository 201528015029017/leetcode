# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def zigzagLevelOrder(self, root):
        """
        :type root: TreeNode
        :rtype: List[List[int]]
        """
        if root == None:
            return []
            
        level, ans, flag = [root], [], 1
        while level:
            ans.append([level[i].val for i in range(len(level))[::flag]])
            level = [kid for n in level for kid in (n.left, n.right) if kid]
            flag *= -1
        return ans
        
        
