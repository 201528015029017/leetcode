/*
思路：类似于数字金字塔的题目，找一条最小的路径。注意边界条件处理
 */
public class Solution {
    public int minPathSum(int[][] a) {
        int n = a.length;
        if (n == 0) return 0;
        int m = a[0].length;
        if (m == 0) return 0;
        int [][] f  = new int[n+1][m+1];
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= m; ++j) {
                if (i == 1 && j == 1) f[i][j] = 0;
                else f[i][j] = Integer.MAX_VALUE;
                if (i > 1) f[i][j] = Math.min(f[i - 1][j], f[i][j]);
                if (j > 1) f[i][j] = Math.min(f[i][j - 1], f[i][j]);
                f[i][j] += a[i - 1][j - 1];
            }
        }
        return f[n][m];
    }
}