/*
思路：题意是将数组顺时针旋转90，即（i,j）->(j,n-i-1),不能一步到位，分成两步计算。
    1.先做y=x的对称 (i,j)->(n-j-1,n-i-1)
    2.将每列翻转
    或者
    1.对称（i,j）->(j,i)
    2.每行翻转
 */
public class Solution {
    private void swap(int[][] a, int i,int j, int x, int y){
        int t = a[i][j];
        a[i][j] = a[x][y];
        a[x][y] = t;
    }
    public void rotate(int[][] a) {
        int n = a.length;
        if (n <= 1) return;
        //step 1
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n-i; j++)
                swap(a,i,j,n-j-1,n-i-1);
        //step 2

        for (int j = 0; j < n; j++)
            for (int i = 0; i < n/2; i++)
                swap(a,i,j,n-i-1,j);

    }
}