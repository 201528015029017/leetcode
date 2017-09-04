/*
思路：dfs，搜到叶子结点就返回当前值是多少 否则为左子树值+右子树值
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
    private int leafSum(TreeNode root,int x){
        if (root == null) return 0;
        if (root.left == null && root.right == null) return x*10+root.val;
        return leafSum(root.left,x*10+root.val)+
                leafSum(root.right,x*10+root.val);
    }
    public int sumNumbers(TreeNode root) {
        return leafSum(root,0);
    }
}