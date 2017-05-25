/*
思路：一次迭代分成往右，往下，往左，往上四步操作，然后注意处理边界情况
        也可以开一个辅助数组判断这前位置是否已经被输出了
 */
public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int n,m;
        List<Integer> ans = new ArrayList<Integer>();
        n = matrix.length;
        if (n == 0) return ans;
        m = matrix[0].length;
        if (m == 0) return ans;
        int i = 0, j = 0;
        int right = m-1, bottom = n-1, left = 0, top = 0;
        while (top <= bottom && left <= right){
            i = top; j = left;
            while (j <= right) {ans.add(matrix[i][j]); j++; step++;}
            j = right; i = top + 1;
            while (i <= bottom) {ans.add(matrix[i][j]); i++; step++;}
            i = bottom; j = right-1;
            while (i > top && j >= left) {ans.add(matrix[i][j]); j--; step++;}
            j = left; i = bottom-1;
            while (j <= right-1 && i > top){ans.add(matrix[i][j]); i--; step++;}
            top++; left++; bottom--; right--;
        }
        return ans;
    }
}