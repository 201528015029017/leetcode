/*
思路：题意是不能偷相邻的两间房子，所以我们f[i][0]表示i房子不偷，f[i][1]表示i房子偷
 */
public class Solution {
    public int rob(int[] a) {
        int n = a.length;
        if (n == 0) return 0;
        int [][] f = new int[n+1][2];
        for (int i = 1; i <= n; ++i){
            f[i][0] = Math.max(f[i-1][0],f[i-1][1]);
            f[i][1] = f[i-1][0]+a[i-1];
        }
        return Math.max(f[n][0],f[n][1]);
    }
}