/*
思路：经典的LIS问题，nlogn解法。
    b[i]表示长度为i的子序列中最后一位的最小值，那么我们可知b数组是肯定是非降的，
    对于数x，二分b，找到最后一个小于x的数b[k]，那么x可以接在b[k]后面，组成一个长度为k+1的子序列，且b[k+1] = x（必定更新）
 */
public class Solution {
    static int binarySearch(int[] a,int left,int right,int target){
        while (left <= right){
            int mid = left+(right-left)/2;
            if (a[mid] >= target) right = mid - 1;
            else left = mid + 1;
        }
        return left-1 < 0 ? 0 : left-1;
    }
    public int lengthOfLIS(int[] a) {
        int[] b = new int[a.length+1];
        int len = 0; b[0] = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++){
            int k = binarySearch(b,0,len,a[i]);
            b[k+1] = a[i];
            len = Math.max(len,k+1);
        }
        return len;
    }
}