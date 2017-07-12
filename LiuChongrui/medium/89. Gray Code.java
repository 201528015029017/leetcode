/*
Problem：求n位的格雷码顺序
 */
/*
思路：格雷码的生成方法：
1.初始从0开始
2.改变最右边的位元值
3.改变右起第一个为1的位元的左边位元
4.重复2,3步骤，直到所有的格雷码生成
 */
public class Solution {
    public List<Integer> grayCode(int n) {
        int x = 0;
        boolean flag = true;
        List<Integer> ans = new ArrayList<Integer>();
        ans.add(0);
        for (int i = 1; i < 1<<n; i++){
            if (flag) x = x ^ 1;  //最右位取反
            else {
                int pos = (x & (~x + 1))<<1; //找到右起第一个为1的位元的左边位置pos
                x = x ^ pos;    //pos位取反
            }
            flag = !flag;
            ans.add(x);
        }
        return ans;
    }
}