/*
思路：枚举每段的root节点，左边的就是左子树，右边的就是右子树，所以我们要通过乘法原理组合左子树和右子树
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
    public List<TreeNode> dfs(int l,int r){
        List<TreeNode> result = new ArrayList<>();
        if (l>r) {
            result.add(null);
            return result;
        }
        for (int k = l; k <= r; k++){
            List<TreeNode> lnode = dfs(l,k-1);
            List<TreeNode> rnode = dfs(k+1,r);
            for (TreeNode left: lnode)               //乘法原理组合左子树和右子树
                for (TreeNode right: rnode){
                    TreeNode root = new TreeNode(k);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
        }
        return result;
    }
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<TreeNode>();
        return dfs(1,n);
    }
}