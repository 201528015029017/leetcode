/*
思路：一开始想到的方法是枚举一个点(i,j)，以它为矩阵左上顶点，然后向右扩展，同时记录往下扩展的最远距离（这里可以预处理先存储下来，在O（1）的时间求得）。
        这样的复杂度是O（n^3）。
        后来发现我们可以在O（n^2）的时间求得，具体方式如下：
        先预处理down[i][j]表示（i,j）这个点往下有多少个连续的1（包括自己）；然后枚举一行，把down[i]数组取出来，于是问题转化成了求得down[i]数组的最大矩形面积
        这样我们可以将二维数组变成一维处理，而最大矩形面积可以在O（n）情况下求得。
        参考 leetcode 84. Largest Rectangle in Histogram

 */


public class Solution {
    public int maximalRectangle(char[][] matrix) {
        int n = matrix.length;
        if (n == 0) return 0;
        int m = matrix[0].length;
        if (m == 0) return 0;
        int [][] down = new int[n][m];
        for (int i = n-1; i >= 0; i--)
            for (int j = 0; j < m; j++){
                if (i < n - 1)
                    if (matrix[i][j] == '1') down[i][j] = down[i+1][j] + 1;
                    else down[i][j] = 0;
                else
                    down[i][j] = matrix[i][j]-'0';
            }
        int ans = 0;
        for (int i = 0; i < n; i++){
            int res = find(down[i]);
            ans = Math.max(res,ans);
        }
        return ans;
    }
    public int find(int[] nums){
        /*
        for (int i = 0; i < nums.length; i++)
            System.out.print(nums[i]+" ");
        System.out.println();
        */
        int top = 0;
        int [] stack = new int[nums.length+2];
        stack[0] = -1;
        int ans = 0;
        for (int i = 0; i <= nums.length; i++){
            int x = (i == nums.length? 0:nums[i]);
            if (top == 0 || x >= nums[stack[top]]){
                stack[++top] = i;
            }
            else{
                while (top > 0 && x <= nums[stack[top]]){
                    int area = (i - stack[top - 1] - 1) * nums[stack[top]];
                    ans = Math.max(ans,area);
                    top--;
                }
                stack[++top] = i;
            }
        }
        return ans;
    }
}