/*
思路:f[i][j][0,1,2]表示0..i-1天买j次的最大值， 0,1,2表示不操作(不持有股票)，买入（或持有股票状态），卖出
    f[i][j][0] = max(f[i-1][j][0,2])
    f[i][j][1] = max(f[i-1][j][1]，max(f[i-1][j-1][0,2]-a[i-1]))
    f[i][j][2] = f[i-1][j][1]+a[i-1]
    这里有个trick，k的值可能很大，当k>=n/2时，那么题目就变成了贪心解，直接贪心求值。
 */
public class Solution {
    public int maxProfit(int k, int[] a) {
        int n = a.length;
        if (k >= n/2){
            return quicksolve(a);
        }
        int [][][] f = new int[2][n+1][3]; // rest,buy,sell
        for (int i = 1; i <= n; ++i){
            for (int j = 0; j <= k; j++){
                if (i == 1){
                    f[1][j][0] = 0;
                    f[1][j][1] = -a[i-1];
                    f[1][j][2] = 0;
                }
                else{
                    f[i%2][j][0] = Math.max(f[(i-1)%2][j][0],f[(i-1)%2][j][2]); // rest
                    if (j > 0){
                        f[i%2][j][1] = Math.max(f[(i-1)%2][j][1],Math.max(f[(i-1)%2][j-1][0],f[(i-1)%2][j-1][2]) - a[i-1]);
                        f[i%2][j][2] = f[(i-1)%2][j][1] + a[i-1];
                    }
                }
            }
        }
        return Math.max(f[n%2][k][0],f[n%2][k][2]);
    }
    public int quicksolve(int [] a){
        int sum = 0;
        for (int i = 1; i < a.length; ++i){
            if (a[i] > a[i-1]) sum+=a[i]-a[i-1];
        }
        return sum;
    }
}