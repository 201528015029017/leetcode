/*
思路：求最大子串乘积，不同于求和，乘积的阶段状态不一定是最大的，也可能是最小的，所以我们用fmin,fmax来记录到i位置最大乘积和最小乘积
    注意处理0的情况，数组也可以用滚动
 */
public class Solution {
    public int maxProduct(int[] a) {
        if (a.length == 0) return 0;
        int[] fmax = new int[a.length];
        int[] fmin = new int[a.length];
        int ans = a[0];
        fmin[0] = fmax[0] = a[0];
        for (int i = 1; i < a.length; ++i){
            if (a[i] == 0) fmax[i] = fmin[i] = 0;
            else if (a[i] < 0) {fmax[i] = Math.max(a[i],fmin[i-1]*a[i]); fmin[i] = Math.min(a[i],fmax[i-1]*a[i]); }
            else {fmin[i] = Math.min(a[i],fmin[i-1]*a[i]); fmax[i] = Math.max(a[i],fmax[i-1]*a[i]); }
            ans = Math.max(fmax[i],ans);
        }
        return ans;
    }
}