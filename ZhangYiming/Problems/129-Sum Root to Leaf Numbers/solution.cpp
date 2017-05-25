/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    int sumNumbers(TreeNode* root) {
        if(root == NULL) return 0;
        int sum = 0;
        int value = 0;
        cal(root, sum, value);
        return sum;
    }
private:
    int cal(TreeNode* root, int & sum, int & value) {
        value =  value * 10 + root->val;
        if(root->left == NULL && root->right == NULL){
            sum += value;
        }
        else{
            if(root->left != NULL) cal(root->left, sum, value);
            if(root->right != NULL)  cal(root->right, sum, value);
        }
        value = value / 10;
        return 0;
    }
};
