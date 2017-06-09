/*
思路：后序遍历的非递归算法。
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
/*
我们将前序遍历结果翻转可得右-左-根的顺序，所以在前序的遍历时候调整下左右顺序，然后把结果翻转
    就可以得到后序遍历的结果了。
 */
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> ans = new ArrayList<>();
        if (null == root) return ans;
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode p = stack.pop();
            ans.add(p.val);
            if (p.left != null) stack.push(p.left);           //和前序遍历不同的左右子树顺序
            if (p.right != null) stack.push(p.right);       //

        }
        Collections.reverse(ans);
        return ans;
    }
}
//第二种方法：直观求
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> ans = new ArrayList<>();
        if (null == root) return ans;
        TreeNode cur = root,previsit = null;
        while (cur != null || !stack.isEmpty()){
            while (cur != null){                  //一直找到最下的左子树，全部进栈
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.peek();
            if (cur.right == null || previsit == cur.right){  //判断栈顶节点是否有右子树或右子树已经被处理过了
                cur = stack.pop();
                previsit = cur;
                ans.add(cur.val);
                cur = null;      //注意这里要把cur情况，否则的话会下次循环会重复进栈
            }
            else cur = cur.right;   //右子树没有被处理过，处理右子树
        }
        return ans;
    }
}