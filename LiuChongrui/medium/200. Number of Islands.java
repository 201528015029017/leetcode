/*
思路：dfs,bfs都可以，从一个未扩展的1扩展，直到所有1都搜完。
 */
public class Solution {
    int n,m;
    public void dfs(char[][] a,int i,int j){
        if (i < 0 || i >=n || j <0 || j >= m) return;
        if (a[i][j] == '0') return;
        a[i][j] = '0';
        dfs(a,i+1,j);
        dfs(a,i,j+1);
        dfs(a,i-1,j);
        dfs(a,i,j-1);
    }
    public int numIslands(char[][] a) {
        n = a.length;
        if (n == 0) return 0;
        m = a[0].length;
        if (m == 0) return 0;
        //
        int count = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (a[i][j] == '1'){
                    dfs(a,i,j);
                    count++;
                }
        return count;
    }
}