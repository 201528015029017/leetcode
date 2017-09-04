/*
思路：这道题和374题没什么关系
    我一开想法是是否有什么数学方法可以形成一个取值策略，后来发现想多了。。。
    正解就是一个极小极大值问题。用f[i][j]代表从i到j这段数列保证猜得的最小代价，
    f[i][j] = min{max{f[i][k-1],f[k+1][j]}+k} i<=k<=j
    其实就是枚举中间值来进行dp，将大段变成两个小段，最后答案是f[1][n]
 */
public class Solution {
    public int getMoneyAmount(int n) {
        int [][] f = new int [n+2][n+2];
        for (int len = 1; len <= n; len++){
            for (int i = 1; i <= n-len+1; i++){
                int j = len+i-1;
                if (i == j) f[i][j] = 0;
                else f[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    f[i][j] = Math.min(f[i][j], k + Math.max(f[i][k - 1], f[k + 1][j]));
                }
            }
        }
        return f[1][n];
    }
}