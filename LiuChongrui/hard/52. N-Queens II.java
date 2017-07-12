/*
Problem:
Follow up for N-Queens problem.

Now, instead outputting board configurations, return the total number of distinct solutions.
 */
/*
思路：求n皇后问题的解个数，方法1直接用51题的方法做。
    方法2，由于不要求方案，我们可以简化，用位运算的方法来高效计算。
    row, skew1, skew2分别代表列和两个对角线方向上的禁止位， 并起来row|skew1|skew2就是这一行的禁止位，
    把这个取反~(row|skew1|skew2) 这一行就是可以放的位置。 用t来表示bound & (~(row | skew1 | skew2)) 其实中bound = 111...1(n个1)
    t&(~t+1)这个是取得最右边的1，也就是从右往左取能够放置的位置
    递归下一行
    dfs(bound, row | pos, (skew1|pos) << 1, (skew2|pos) >> 1);
    row|pos好理解，
    （skew1|pos）<<1 画图可以看到45°对角线到下一行就是所有禁止位往左移了1位。（这里移位可能会超过bound，所以之前要用bound & 来去掉超过的部分）
    （skew2|pos）>>2 135°对角线到下一行就是所有禁止位往右移了一位。
 */

public class Solution {
    static int ans = 0;
    public int totalNQueens(int n) {
        ans = 0;
        dfs((1<<n)-1,0,0,0);
        return ans;
    }
    public void dfs(int bound,int row,int skew1,int skew2){
        if (row != bound){
            int t = bound & (~(row | skew1 | skew2));
            while (t > 0){
                int pos = t & (~t + 1);
                t = t - pos;
                dfs(bound, row | pos, (skew1|pos) << 1, (skew2|pos) >> 1);
            }
        }
        else ans++;
    }
}