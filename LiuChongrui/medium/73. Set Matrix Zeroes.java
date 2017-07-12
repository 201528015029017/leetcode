/*
problem:
Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

click to show follow up.

Follow up:
Did you use extra space?
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?
 */
/*
思路：我们先考虑下O（m + n）空间复杂度的算法，用一个数组row[]记录哪些行需要赋0，一个数组col[]记录哪些列需要赋0.
    那么我们是否可以用原数组的空间来实现呢，我们把第0列和第0行拿出来干同样的事情,a[i][0]记录第i行要不要赋值0，
    a[0][j]记录第j列要不要赋值0，这样的话可以控制[1..n-1,1..m-1]的数组，第一行和第一列的值被占据丢失了，其实只要用一个
    单变量记录row0，col0记录第0行、第0列要不要赋0就行了，这样就在常量空间内完成了。
 */

public class Solution {
    public void setZeroes(int[][] a) {
        int n =  a.length;
        int m = a[0].length;
        boolean row0 = false,col0 = false;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j){
                if (a[i][j] == 0){
                    if (i == 0) row0 = true;
                    if (j == 0) col0 = true;
                    a[i][0] = 0;
                    a[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < n; ++i)
            for (int j = 1; j < m; ++j)
                if (a[i][0] == 0 || a[0][j] == 0) a[i][j] = 0;
        if (row0)
            for (int j = 0; j < m; ++j) a[0][j] = 0;
        if (col0)
            for (int i = 0; i < n; ++i) a[i][0] = 0;
    }
}