/*
思路：f[i][j]表示第i行 0..j的和是多少 求row1,col1到row2,col2就是segma(f[i][col2]-f[i][col1-1]) i = row1..row2
 */
public class NumMatrix {
    static int [][] f;
    public NumMatrix(int[][] a) {
        int n = a.length;
        if (n == 0) return;
        int m = a[0].length;
        if (m == 0) return;
        f = new int [n][m];
        for (int i = 0; i < n; ++i){
            f[i][0] = a[i][0];
            for (int j = 1; j < m; ++j)
                f[i][j] = f[i][j-1] + a[i][j];
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int i = row1; i <= row2; i++){
            if (col1 > 0) sum += f[i][col2] - f[i][col1-1];
            else sum += f[i][col2];
        }
        return sum;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */