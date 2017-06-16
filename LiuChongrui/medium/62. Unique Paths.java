/*
思路：当前位置只能由上方或左方移动过来，所以f[i][j] = f[i - 1][j] + f[i][j - 1] f[1][1] = 1;
    题目不考虑溢出。。。
 */
public class Solution {
    public int uniquePaths(int n, int m) {
        int [][] f  = new int[n+1][m+1];
        f[1][1] = 1;
        for (int i = 1; i <= n; ++i)
            for (int j = 1; j <= m; ++j) {
                if (i == 1 && j == 1) continue;
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        return f[n][m];
    }
}