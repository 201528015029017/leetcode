/*
思路：数字三角形，典型的dp题
    f[i][j] = min{f[i-1][j-1],f[i-1][j]}
    这里变为滚动数组实现
 */
public class Solution {
    public int minimumTotal(List<List<Integer>> a) {
        int [][] f = new int[2][a.size()];
        Arrays.fill(f[0],Integer.MAX_VALUE);
        Arrays.fill(f[1],Integer.MAX_VALUE);
        f[0][0] = a.get(0).get(0);
        for (int i = 1; i < a.size(); i++)
            for (int j = 0; j < a.get(i).size(); j++){
                f[i%2][j] = f[(i+1)%2][j];
                if (j > 0) f[i%2][j] = Math.min(f[(i+1)%2][j-1],f[i%2][j]);
                f[i%2][j] += a.get(i).get(j);

            }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < a.size(); i++){
            ans = Math.min(ans,f[(a.size()-1)%2][i]);
        }
        return ans;
    }
}