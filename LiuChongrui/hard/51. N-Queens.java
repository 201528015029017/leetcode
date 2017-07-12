/*
Problem:
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:

[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
*/
/*
思路：求n皇后问题的所有解方案，采用递归回溯的方式做，一行一行的尝试，用col[i] 记录第i列是否能放，
    skew1,skew2记录45°对角线和135°对角线的情况，一个是(i-j)，一个是（i+j）
    PS：函数String.valueOf(char[]) 可以将char数组转化为String
 */
public class Solution {
    static List<List<String>> ans;
    public List<List<String>> solveNQueens(int n) {
        ans = new ArrayList<>();
        if (n == 0) return ans;
        char [][] a = new char[n][n];
        boolean[] col = new boolean[n];
        boolean[] skew1 = new boolean[2*n];
        boolean[] skew2 = new boolean[2*n];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j) a[i][j] = '.';
        dfs(0,col,skew1,skew2,a,n);
        return ans;
    }
    public void dfs(int i, boolean[] col, boolean[] skew1, boolean[] skew2, char[][] a,int n){
        if (i == n){
            List<String> res = new ArrayList<>();
            for (int k = 0; k < n; ++k){
                res.add(String.valueOf(a[k]));
            }
            ans.add(res);
            return;
        }
        for (int j = 0; j < n; ++j){
            if (!col[j] && !skew1[i-j+n] && !skew2[i+j]){
                col[j] = skew1[i-j+n] = skew2[i+j] = true;
                a[i][j] = 'Q';
                dfs(i+1,col,skew1,skew2,a,n);
                a[i][j] = '.';
                col[j] = skew1[i-j+n] = skew2[i+j] = false;
            }
        }
    }
}