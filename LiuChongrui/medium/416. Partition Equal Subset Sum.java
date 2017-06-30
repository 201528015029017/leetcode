/*
思路：类似于01背包，f[i][j]表示到第i个数，和为j能不能组成 f[i][j] = f[i-1][j] || f[i-1][j-a[i]]
    可以转为1维
 */
public class Solution {
    public boolean canPartition(int[] a) {
        int n = a.length, sum = 0;
        for (int i = 0; i < n; ++i) sum += a[i];
        if (sum % 2 == 1) return false;
        boolean [] f = new boolean[sum/2+1];
        f[0] = true;
        for (int i = 0; i < n; ++i){
            for (int j = sum/2; j >= 0; --j){
                if (j >= a[i]) f[j] = f[j] || f[j-a[i]];
                if (f[sum/2]) return true;
            }
        }
        return false;
    }
}