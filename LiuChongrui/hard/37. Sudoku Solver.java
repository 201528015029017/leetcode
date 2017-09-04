/*
思路：数独，搜索，枚举每个未知点的值。
      标记方法
        rowval[i][val]标记第i行数字val是否可填
        colval[i][val]标记第i列数字val是否可填
        squareval[i][val]标记第i个3*3方格 数字val是否可填
       预处理：
         预先把需要枚举的未知点存起来
       可能的剪枝：
         优先枚举枚举量少的点。
 */

public class Solution {

    boolean rowval[][] = new boolean[9][10];
    boolean colval[][] = new boolean[9][10];
    boolean squareval[][] = new boolean [9][10];

    private boolean find(char[][] a,int[] blankx,int[] blanky,int k,int total){
        if (k == total){
            return true;
        }
        for (int i = 1; i < 10; i++){
            int x = blankx[k];
            int y = blanky[k];
            if (rowval[x][i]&&colval[y][i]&&squareval[3*(x/3)+y/3][i]){
                rowval[x][i] = false;
                colval[y][i] = false;
                squareval[3*(x/3)+y/3][i] = false;
                a[x][y] = (char)(i+'0');

                if (find(a,blankx,blanky,k+1,total)) return true;
                else {
                    rowval[x][i] = true;
                    colval[y][i] = true;
                    squareval[3*(x/3)+y/3][i] = true;
                    a[x][y] = '.';
                }
            };
        }
        return false;
    }
    public void solveSudoku(char[][] a) {

        int [] blankx = new int[81];
        int [] blanky = new int[81];
        //intial
        int total = 0;
        for (int i = 0; i < 9; i++) {
            Arrays.fill(rowval[i], true);
            Arrays.fill(colval[i], true);
            Arrays.fill(squareval[i], true);
        }

        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (a[i][j] == '.'){

                    blankx[total] = i;
                    blanky[total] = j;
                    total++;
                }
                else{
                    int value = a[i][j]-'0';
                    rowval[i][value] = false;
                    colval[j][value] = false;
                    squareval[3*(i/3)+j/3][value] = false;
                }
        System.out.println(find(a,blankx,blanky,0,total));
    }
}