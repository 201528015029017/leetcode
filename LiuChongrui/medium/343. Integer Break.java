/*
思路：f[i]表示i能分成的最大乘积 f[i] = max(max(f[i-j],i-j) * j)  因为在小数时可能i-j > f[i-j]
    数学方法：尽量拆成3或2 https://discuss.leetcode.com/topic/43055/why-factor-2-or-3-the-math-behind-this-problem
 */
public class Solution {
    public int integerBreak(int n) {
        int [] f = new int[n+1];
        f[1] = 0;
        f[2] = 1;
        for (int i = 3; i <= n; ++i){
            f[i] = 0;
            for (int j = 1; j < i; ++j)
                f[i] = Math.max(f[i],Math.max(i-j,f[i-j])*j);
        }
        return f[n];
    }
}