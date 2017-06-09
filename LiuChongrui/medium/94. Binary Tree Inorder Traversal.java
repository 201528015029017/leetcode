/*
思路：中序遍历的非递归算法。
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
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> ans = new ArrayList<>();
        if (null == root) return ans;
        TreeNode p = root;
        while (p!=null || !stack.isEmpty()){
            while (p != null){         //一直找左子树，节点进栈
                stack.push(p);
                p = p.left;
            }
            p = stack.pop();          //出栈
            ans.add(p.val);
            p = p.right;              //最后找右子树，注意这里右子树就不用进栈了，因为while循环里会把右节点进栈。
        }
        return ans;
    }
}