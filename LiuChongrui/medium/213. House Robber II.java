/*
思路:房间是环形的，所以我们判断第n个房间能不能偷是还和第一个房间被偷情况有关。
    求0..n-2的房间最大值m1，这里0和n-2不相邻，没有依赖，这是默认n-1不偷
    求1..n-1的房间最大值m2，这里1和n-1不相邻，没有依赖，这是默认0不偷
    又知道n-1和0房间不能同时偷，所以全局最大值为max{m1,m2}
 */
public class Solution {
    public int rob(int[] a) {
        int n = a.length;
        if (n == 0) return 0;
        if (n == 1) return a[0];
        return Math.max(robrange(a,0,n-2),robrange(a,1,n-1));
    }
    public int robrange(int[] a,int l,int r) {
        int preYes = 0,preNo = 0;
        for (int i = l; i <= r; ++i){
            int temp = preNo;
            preNo = Math.max(preNo,preYes);
            preYes = temp + a[i];
        }
        return Math.max(preNo,preYes);
    }
}