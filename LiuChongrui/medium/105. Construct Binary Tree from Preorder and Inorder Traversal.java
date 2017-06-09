/*
思路：前序中序求树，前序中的节点x在中序中的位置为k 则k左边的为k的左子树，k右边的为k的右子树，且我们也可以得出左子树个数leftlen和右子树个数rightlen，
      x右边的leftlen个为左子树，余下的为右子树，递归实现。
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
    public TreeNode dfs(int[] preorder, int[] inorder, int i, int j, int l, int r){
        for (int k = l; k <= r; k++){
            if (preorder[i] == inorder[k]){
                int leftlen = k-l;
                int rightlen = r-k;
                TreeNode p = new TreeNode(preorder[i]);
                p.left = dfs(preorder,inorder,i+1,i+leftlen,l,k-1);
                p.right = dfs(preorder,inorder,i+leftlen+1,j,k+1,r);
                return p;
            }
        }
        return null;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return dfs(preorder,inorder,0,preorder.length-1,0,inorder.length-1);
    }
}