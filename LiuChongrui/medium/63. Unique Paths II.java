/*
思路：和第一问差不多，多了障碍物，把障碍物处的f设为0即可
 */
public class Solution {
    public int uniquePathsWithObstacles(int[][] a) {
        int n = a.length;
        if (n == 0) return 0;
        int m = a[0].length;
        if (m == 0) return 0;
        int [][] f  = new int[n+1][m+1];
        f[1][1] = (a[0][0] == 1? 0 : 1);
        for (int i = 1; i <= n; ++i)
            for (int j = 1; j <= m; ++j) {
                if (i == 1 && j == 1) continue;
                if (a[i - 1][j - 1] == 0)
                    f[i][j] = f[i - 1][j] + f[i][j - 1];
                else f[i][j] = 0;

            }
        return f[n][m];
    }
}