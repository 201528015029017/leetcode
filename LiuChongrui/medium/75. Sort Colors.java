/*
problem：
Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.

click to show follow up.

Follow up:
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

Could you come up with an one-pass algorithm using only constant space?
 */
/*
思路：通过一次遍历完成，类似于快速排序的交换思想，0,1,2关键字为1，比1小的放在第一个区间，和1相等的放在第二区间，比1大的放在第3区间
       代码风格参考jdk1.8中的快排
 */
/**
 * Created by Chongrui on 2017/7/10 0010.
 */
public class Solution {
    public void sortColors(int[] a) {
        int less = 0,great = a.length-1, pivot = 1;
        for (int k = less; k <= great; k++){
            int ak = a[k];
            if (a[k] < pivot){
                a[k] = a[less];
                a[less] = ak;
                ++less;
            }
            else if (a[k] > pivot){
                while (a[great] > pivot){
                    if (great-- == k) return;
                }
                if (a[great] < pivot) {
                    a[k] = a[less];
                    a[less] = a[great];
                    ++less;
                }
                else {
                    a[k] = a[great];
                }
                a[great] = ak;
                --great;
            }
        }
    }
}
//其他的算法
// 这个很巧妙，2指针速度>1指针>0指针，所以慢的指针会覆盖掉原来填入的值
    void sortColors(int A[], int n) {
        int n0 = -1, n1 = -1, n2 = -1;
        for (int i = 0; i < n; ++i) {
            if (A[i] == 0)
            {
                A[++n2] = 2; A[++n1] = 1; A[++n0] = 0;
            }
            else if (A[i] == 1)
            {
                A[++n2] = 2; A[++n1] = 1;
            }
            else if (A[i] == 2)
            {
                A[++n2] = 2;
            }
        }
    }

    // one pass in place solution
    void sortColors(int A[], int n) {
        int j = 0, k = n - 1;
        for (int i = 0; i <= k; ++i){
            if (A[i] == 0 && i != j)
                swap(A[i--], A[j++]);
            else if (A[i] == 2 && i != k)
                swap(A[i--], A[k--]);
        }
    }

    // one pass in place solution
    void sortColors(int A[], int n) {
        int j = 0, k = n-1;
        for (int i=0; i <= k; i++) {
            if (A[i] == 0)
                swap(A[i], A[j++]);
            else if (A[i] == 2)
                swap(A[i--], A[k--]);
        }
    }