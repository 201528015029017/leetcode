/*
思路：题意是以任意两根柱子作为边界储水所能形成的储水面积是多少？
    解法1：最直观的就是枚举两根柱子，计算面积，复杂度为O（n^2）

    解法2：我们以[5,3,6,7,2]为例，假如我们枚举右边界，然后去前面找不小于右边界的左边界。当我们枚举6时，前面是5,3，那么3其实是不需要的计算的，
    因为前面有个比3大的5,这个5左边界无论如何都要比3好，所以我们可以只维护一个递增的队列Q（5,6,7）,然后左边界在这里面找就行了。
    枚举一个右边界x，就在Q中找最靠近的不小于x的值，这个值就是左边界的值，且肯定最靠左的，如果左边没有小于右边界的值，那么直接进队，
    这里可以用二分实现，这样我们求出来的就是右边界比左边界小的情况，然后我们倒着再做一次，求出右边界比左边界大的情况。总复杂度O（nlogn）

    解法3：我们看第一个数，以他作为边界，那么右边界肯定是从右边开始第一个比他大的值，所以我们用两个指针分别指向首尾,l,r
    当a[l]>a[r]时，那么r的左边界找到，r可以不用再计算了所以,r--
    当a[l]<a[r]时，那么l的右边界找到，l可以不用在计算了所以,l++
    当a[l]==a[r]时，任意移动一边
    复杂度为O（n），又是two pointer的应用
 */
public class Solution {
    public int maxArea(int[] height) {
        int n = height.length;
        int l = 0;
        int r = n-1;
        int ans = 0;
        while (l < r){
            if (height[l] <= height[r]){
                ans = Math.max(ans,(r-l)*height[l]);
                l++;
            }
            else{
                ans = Math.max(ans,(r-l)*height[r]);
                r--;
            }
        }
        return ans;
    }
}