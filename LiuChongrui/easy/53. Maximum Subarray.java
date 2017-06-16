/*
思路：求子串最大和，可以很快写出转移方程 f[i]表示以第i个数结尾的子串和最大值 f[i] = Max{a[i],f[i-1]+a[i]}
    稍微分析可以发现，i状态只和i-1的状态有关，数组可以省略
 */
public class Solution {
    public int maxSubArray(int[] nums) {
        int ans = Integer.MIN_VALUE,m = 0;
        for (int i = 0; i < nums.length; i++){
            m += nums[i];
            ans = Math.max(m,ans);
            if (m < 0) m = 0;
        }
        return ans;
    }
}