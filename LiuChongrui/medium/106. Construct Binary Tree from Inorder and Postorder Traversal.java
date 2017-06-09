/*
思路：后序中序求树，首先把后序翻转看，后序中的节点x在中序中的位置为k 则k左边的为k的左子树，k右边的为k的右子树，且我们也可以得出左子树个数leftlen和右子树个数rightlen，
      x左边的leftlen个为右子树，余下的为左子树，递归实现。
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
    public TreeNode dfs(int[] inorder,int[] postorder, int i, int j, int l,int r){
        if (i > j || l > r)  return null;
        for (int k = i; k <= j; k++){
            if (inorder[k] == postorder[r]){
                TreeNode p = new TreeNode(inorder[k]);
                int rightlen = j-k;
                int leftlen = k-i;
                System.out.println(leftlen+" "+rightlen);
                p.left = dfs(inorder,postorder,i,k-1,l,l+leftlen-1);
                p.right = dfs(inorder, postorder,k+1,j,l+leftlen,r-1);
                return p;
            }
            else continue;
        }
        return null;
    }
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return dfs(inorder,postorder,0,inorder.length-1,0,postorder.length-1);
    }
}