/*
思路：螺旋数组，最简单实现的方法是分成向左，下，右，上4种走法，然后判断当前需不需要转变走法
 */
public class Solution {
    public int[][] generateMatrix(int n) {
        int[][] a = new int[n][n];
        int step = 1, flag = 1, i = 0, j = -1;
        while(step <= n*n){
            if (flag == 1){
                ++j;
                if (j >= n || a[i][j] > 0) {flag = 2; --j; continue;}
                else a[i][j] = step;
            }
            else if (flag == 2){
                ++i;
                if (i >= n || a[i][j] > 0) {flag = 3; --i; continue;}
                else a[i][j] = step;
            }
            else if (flag == 3){
                --j;
                if (j < 0 || a[i][j] > 0) {flag = 4; ++j; continue;}
                else a[i][j] = step;
            }
            else {
                --i;
                if (i < 0 || a[i][j] > 0) {flag = 1; ++i; continue;}
                else a[i][j] = step;
            }
            ++step;
        }
        return a;
    }
}