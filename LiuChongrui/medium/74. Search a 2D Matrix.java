/*
problem:
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.
 */
/*
思路：不要看成矩阵，看成一个有序数列就行了
 */

public class Solution {
    public boolean searchMatrix(int[][] a, int target) {
        int n = a.length;
        if (n == 0) return false;
        int m = a[0].length;
        if (m == 0) return false;
        int left = 0, right = n*m-1;
        int mid = 0;
        while (left <= right){
            mid = left + (right - left)/2;
            int i = mid / m, j = mid % m;
            if (a[i][j] > target) right = mid - 1;
            else if (a[i][j] < target) left = mid + 1;
            else return true;
        }
        return false;
    }
}