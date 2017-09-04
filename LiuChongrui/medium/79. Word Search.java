/*
思路：深搜（广搜的话状态不好记录，标记数组不好搞）
1.写得有点繁琐
  1）定义了常量数组，但其实可以直接写成4个dfs（。。）或运算，然后将边界条件的判断写在最前面
  2）用flag数组判断重复步骤，但其实可以直接将board[x][y] 赋值一个非字母值，因为有word参与迭代，所以回溯时可以将其还原
  3）看到有大神用board[x][y] ^=256 来打标记，然后再异或256又可以变回来，学习一下（因为字母最大的ascll码是127，所以异或取得值要较大，避免异或后还是字母值）
2.出现了很多小问题
  1）数组下标写错
  2）标记数组进入时忘记置false
  3) 函数调用时参数顺序写错
3.学了数组赋值函数，Arrays.fill(arrays,value),要注意这里的arrays只能是一维数组
*/





public class Solution {
    private static final int[] c1 = {0,1,0,-1};
    private static final int[] c2 = {1,0,-1,0};

    private boolean boardLegal(char[][] board ,int xx,int yy){
        if (xx >= 0 && xx < board.length && yy >= 0 && yy <board[xx].length)
            return true;
        else return false;
    }

    private boolean dfs(char[][] board,String word, int index,int x,int y,boolean[][] flag){

        if (index == word.length()){
            return true;
        }
        for (int i = 0; i < 4; i++){
            int xx = x + c1[i];
            int yy = y + c2[i];
            if (boardLegal(board,xx,yy) && flag[xx][yy] && board[xx][yy] == word.charAt(index)){
                flag[xx][yy] = false;
                boolean ok = dfs(board,word,index+1,xx,yy,flag);
                flag[xx][yy] = true;
                if (ok) return true;
            }
        }
        return false;
    }

    public boolean exist(char[][] board, String word) {
        int n = board.length;
        int m = board[0].length;

        boolean[][] flag = new boolean[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++){
                if (board[i][j] == word.charAt(0)){
                    for (int k = 0; k < n; k++)
                        Arrays.fill(flag[k],true);
                    flag[i][j] = false;
                    boolean ok = dfs(board,word,1,i,j,flag);
                    if (ok ) return true;
                }
            }
        return false;
    }

}