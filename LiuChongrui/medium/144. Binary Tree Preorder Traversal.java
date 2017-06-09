/*
思路：前序遍历的非递归算法。
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> ans = new ArrayList<>();
        if (null == root) return ans;
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode p = stack.pop();
            ans.add(p.val);                             //当前节点输出
            if (p.right != null) stack.push(p.right);   //先将右子树进栈，因为栈是先进后出的
            if (p.left != null) stack.push(p.left);     //再讲左子树进栈
        }
        return ans;
    }
}