/*
思路：我们把1..n的数用n位二进制来表示状态，dfs()表示当前状态下是先手必胜true还是后手必胜false,当当前状态能转移到的状态
    存在false时，就是先手必胜true（因为我可以转移到我的必胜状态），否则是后手必胜false。
    用记忆化搜索减少搜索空间
    注意一些特殊情况的判断。
 */
public class Solution {
    boolean[] flag,f;
    public boolean dfs(int digit,int len,int sum, int desiredTotal){
        if (sum >= desiredTotal) return false;
        if (flag[digit]) return f[digit];
        boolean ok = true;
        int i = 0;
        while (i < len){
            int t = 1 << i;
            if ((t & digit) > 0) ok = ok && dfs(digit-t,len,sum+i+1,desiredTotal);
            ++i;
        }
        flag[digit] = true;
        f[digit] = !ok;
        //System.out.println(digit+" "+!ok);
        return !ok;
    }
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int digit = (1<<maxChoosableInteger)-1;
        flag = new boolean[digit+1];
        f = new boolean[digit+1];
        if (maxChoosableInteger*(1+maxChoosableInteger)/2 < desiredTotal) return false;  //特殊情况
        if (desiredTotal <= 0) return true;               //特殊情况
        return dfs(digit,maxChoosableInteger,0,desiredTotal);
    }
}